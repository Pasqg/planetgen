# Planet Gen

Planet rendering automation for Blender.

![america render](results/america.png)

This repository is a convenient wrapper around a [python rendering
script](defaults/src/main/resources/planet_render.py) using Blender python API. It offers an easy to use programmatic
interface (fluent) to set the render or to use presets.

## Requirements

Rendering is done with a local copy of [Blender](https://www.blender.org), not included with this repo. Any Blender
version whose api is compatible with 4.0 version should work. The path to Blender on the local machine can be
changed in [Defaults.BLENDER](defaults/src/main/java/org/pasqg/planetgenerator/defaults/Defaults.java).

## Starting a render

Run the main in [PlanetRender.java](renderer/src/main/java/org/pasqg/planetgenerator/renderer/PlanetRenderer.java), or
if needed the [python rendering script](defaults/src/main/resources/planet_render.py) directly.

The result will be saved in the folder `results/` with name `{outputFilePrefix}-{camera}.png` (i.e. europe-closeup.png)

Example render from [RenderPresets.EUROPE_FULL](renderer/src/main/java/org/pasqg/planetgenerator/renderer/RenderPresets.java) preset:
![europe render](results/europe_small.png)

## Customizing a render

The [RenderArguments](renderer/src/main/java/org/pasqg/planetgenerator/renderer/RenderArguments.java) class can be used
to specify render parameters such as resolution, textures (color, clouds and height map), camera angle and so on.

The [RenderPresets](renderer/src/main/java/org/pasqg/planetgenerator/renderer/RenderPresets.java) contains ready-to-use
presets for various geographical places.

## Textures

Default textures are taken from https://visibleearth.nasa.gov. 