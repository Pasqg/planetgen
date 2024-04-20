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

def renderPlanet(camera):
    bpy.context.scene.camera = bpy.context.scene.objects["camera-" + camera]
    render(camera + ".png", False)

def parseCameras(cameras):
    if cameras == "all":
        return ["full", "half", "closeup"]
    else:
        return cameras.split(",")

argv = sys.argv
print(argv)

# location of folder to save images to
output_folder = ''

x = int(argv[argv.index("-x") + 1]) if "-x" in argv else 256
y = int(argv[argv.index("-y") + 1]) if "-y" in argv else 256
samples = int(argv[argv.index("-samples") + 1]) if "-samples" in argv else 32
cameras = parseCameras(argv[argv.index("-cameras") + 1]) if "-cameras" in argv else ["full"]
isRenderClouds = bool(argv[argv.index("-clouds") + 1]) if "-clouds" in argv else True
isGPU = bool(argv[argv.index("-gpu") + 1]) if "-gpu" in argv else False
colorMapPath = argv[argv.index("-colorMap") + 1] if "-colorMap" in argv else None
heightMapPath = argv[argv.index("-heightMap") + 1] if "-heightMap" in argv else None
cloudsMapPath = argv[argv.index("-cloudsMap") + 1] if "-cloudsMap" in argv else None
atmosphereScale = 1.008
cloudsScale = 1 + (atmosphereScale - 1) * 0.3

print("Selected cameras: " + str(cameras))


#set material parameters
#right click on parameter and then "copy data path" to get the python 
cloudsmaterial = bpy.data.materials["cloudsmaterial2"]
cloudsmaterial.node_tree.nodes["Value"].outputs[0].default_value = 1.0
bpy.data.objects["atmosphere2"].scale[0] = atmosphereScale
bpy.data.objects["atmosphere2"].scale[1] = atmosphereScale
bpy.data.objects["atmosphere2"].scale[2] = atmosphereScale
bpy.data.objects["clouds3"].scale[0] = cloudsScale
bpy.data.objects["clouds3"].scale[1] = cloudsScale
bpy.data.objects["clouds3"].scale[2] = cloudsScale
bpy.data.objects["clouds3"].hide_render = not isRenderClouds

bpy.data.objects["planet2"].rotation_euler[0] = 33 / 180 * 3.14
bpy.data.objects["planet2"].rotation_euler[1] = 22 / 180 * 3.14
bpy.data.objects["planet2"].rotation_euler[2] = 0 / 180 * 3.14
bpy.data.objects["clouds3"].rotation_euler[2] = 43 / 180 * 3.14
bpy.data.objects["clouds3"].rotation_euler[0] = -60 / 180 * 3.14

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
    cloudsmaterial.node_tree.nodes['Image Texture'].image = cloudsMap
    cloudsmaterial.node_tree.nodes['Image Texture.001'].image = cloudsMap

#setup render
rndr = bpy.context.scene.render
rndr.pixel_aspect_x = 1.0
rndr.pixel_aspect_y = 1.0
rndr.resolution_percentage = 100

#Saves low resolution preview for debugging
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

[renderPlanet(camera) for camera in cameras]