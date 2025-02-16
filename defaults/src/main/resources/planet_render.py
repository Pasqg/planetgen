import bpy
import os
import sys

def render(filename, withPreview=False):
    resX = rndr.resolution_x
    resY = rndr.resolution_y
    if withPreview:
        rndr.resolution_x = 256
        rndr.resolution_y = 256
        rndr.filepath = os.path.join(output_folder, "preview" + filename)
        bpy.ops.render.render(write_still=True)

    rndr.resolution_x = resX
    rndr.resolution_y = resY
    rndr.filepath = os.path.join(output_folder, filename)
    bpy.ops.render.render(write_still=True)

def renderPlanet(camera, outputFilePrefix):
    bpy.context.scene.camera = bpy.context.scene.objects["camera-" + camera]
    render(f"{outputFilePrefix}-{camera}.png", False)

def parseCameras(cameras):
    if cameras == "all":
        return ["full", "half", "closeup"]
    else:
        return cameras.split(",")

def parseArgument(argv, arg, defaultValue):
    return argv[argv.index(arg) + 1] if arg in argv else defaultValue

argv = sys.argv
print(argv)

output_folder = 'results'

x = int(parseArgument(argv, "-x", 256))
y = int(parseArgument(argv, "-y", 256))
samples = int(parseArgument(argv, "-samples", 32))
cameras = parseCameras(parseArgument(argv, "-cameras", "full"))
outputFilePrefix = parseArgument(argv, "-outputFilePrefix", "output")
isRenderClouds = parseArgument(argv, "-clouds", False)
isGPU = bool(parseArgument(argv, "-gpu", False))
colorMapPath = parseArgument(argv, "-colorMap", None)
heightMapPath = parseArgument(argv, "-heightMap", None)
cloudsMapPath = parseArgument(argv, "-cloudsMap", None)

planetRotationX = float(parseArgument(argv, "-planetRotationX", 0.0))
planetRotationY = float(parseArgument(argv, "-planetRotationY", 0.0))
planetRotationZ = float(parseArgument(argv, "-planetRotationZ", 0.0))
cloudsRotationX = float(parseArgument(argv, "-cloudsRotationX", 0.0))
cloudsRotationY = float(parseArgument(argv, "-cloudsRotationY", 0.0))
cloudsRotationZ = float(parseArgument(argv, "-cloudsRotationZ", 0.0))

atmosphereScale = 1.008
cloudsScale = 1 + (atmosphereScale - 1) * 0.5

print("Selected cameras: " + str(cameras))


#set material parameters
#right click on parameter and then "copy data path" to get the python equivalent
cloudsmaterial = bpy.data.materials["cloudsmaterial2"]
cloudsmaterial.node_tree.nodes["Value"].outputs[0].default_value = 1.0
bpy.data.objects["atmosphere2"].scale[0] = atmosphereScale
bpy.data.objects["atmosphere2"].scale[1] = atmosphereScale
bpy.data.objects["atmosphere2"].scale[2] = atmosphereScale
bpy.data.objects["clouds3"].scale[0] = cloudsScale
bpy.data.objects["clouds3"].scale[1] = cloudsScale
bpy.data.objects["clouds3"].scale[2] = cloudsScale
bpy.data.objects["clouds3"].hide_render = not isRenderClouds

to_rad = 3.14 / 180.0
bpy.data.objects["planet2"].rotation_euler[0] = planetRotationX * to_rad
bpy.data.objects["planet2"].rotation_euler[1] = planetRotationY * to_rad
bpy.data.objects["planet2"].rotation_euler[2] = planetRotationZ * to_rad
bpy.data.objects["clouds3"].rotation_euler[0] = cloudsRotationX * to_rad
bpy.data.objects["clouds3"].rotation_euler[1] = cloudsRotationY * to_rad
bpy.data.objects["clouds3"].rotation_euler[2] = cloudsRotationZ * to_rad

if colorMapPath is not None:
    colorMap = bpy.data.images.load(colorMapPath)
    colorMapNode = bpy.data.materials["land"].node_tree.nodes.new('ShaderNodeTexImage')
    colorMapNode.image = colorMap
    links = bpy.data.materials["land"].node_tree.links
    links.new(colorMapNode.outputs["Color"], bpy.data.materials["land"].node_tree.nodes["Principled BSDF"].inputs["Base Color"])

if heightMapPath is not None:
    heightMap = bpy.data.images.load(heightMapPath)
    heightMapNode = bpy.data.materials["land"].node_tree.nodes.new('ShaderNodeTexImage')
    heightMapNode.image = heightMap
    links = bpy.data.materials["land"].node_tree.links
    links.new(heightMapNode.outputs["Color"], bpy.data.materials["land"].node_tree.nodes["Displacement"].inputs["Height"])

if cloudsMapPath is not None:
    cloudsMap = bpy.data.images.load(cloudsMapPath)
    cloudsmaterial.node_tree.nodes['cloudsTexture1'].image = cloudsMap

#setup render
rndr = bpy.context.scene.render
rndr.pixel_aspect_x = 1.0
rndr.pixel_aspect_y = 1.0
rndr.resolution_percentage = 100

rndr.resolution_x = x
rndr.resolution_y = y
bpy.data.scenes["Scene"].cycles.samples = samples

if isGPU:
    bpy.context.preferences.addons[
        "cycles"
    ].preferences.compute_device_type = "METAL"
    bpy.context.scene.cycles.device = "GPU"
    bpy.context.preferences.addons["cycles"].preferences.get_devices()
    for d in bpy.context.preferences.addons["cycles"].preferences.devices:
        if d["name"] == "AMD Radeon Pro 5500M":
            d["use"] = 1
            print(f"Using device: {d['name']}")

[renderPlanet(camera, outputFilePrefix) for camera in cameras]