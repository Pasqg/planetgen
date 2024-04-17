import bpy
import os
import sys

def render(filename, withPreview):
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
colorMapPath = argv[argv.index("-colorMap") + 1] if "-colorMap" in argv else None
atmosphereScale = 1.015

print("Selected cameras: " + str(cameras))


#set material parameters
#right click on parameter and then "copy data path" to get the python 
bpy.data.materials["cloudsmaterial2"].node_tree.nodes["Value"].outputs[0].default_value = 1.0
bpy.data.objects["atmosphere2"].scale[0] = atmosphereScale
bpy.data.objects["atmosphere2"].scale[1] = atmosphereScale
bpy.data.objects["atmosphere2"].scale[2] = atmosphereScale
bpy.data.objects["clouds3"].scale[0] = 1.01
bpy.data.objects["clouds3"].scale[1] = 1.01
bpy.data.objects["clouds3"].scale[2] = 1.01
bpy.data.objects["clouds3"].hide_render = not isRenderClouds
bpy.data.objects["planet2"].rotation_euler[2] = 180

if colorMapPath is not None:
    colorMap = bpy.data.images.load(colorMapPath)
    colorMapNode = bpy.data.materials["land"].node_tree.nodes.new('ShaderNodeTexImage')
    colorMapNode.image = colorMap
    links = bpy.data.materials["land"].node_tree.links
    links.new(colorMapNode.outputs["Color"], bpy.data.materials["land"].node_tree.nodes["Principled BSDF"].inputs["Base Color"])

#setup render
rndr = bpy.context.scene.render
rndr.pixel_aspect_x = 1.0
rndr.pixel_aspect_y = 1.0
rndr.resolution_percentage = 100


#Saves low resolution preview for debugging
rndr.resolution_x = x
rndr.resolution_y = y
bpy.data.scenes["Scene"].cycles.samples = samples

[renderPlanet(camera) for camera in cameras]