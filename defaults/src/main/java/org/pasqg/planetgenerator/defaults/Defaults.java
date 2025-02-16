package org.pasqg.planetgenerator.defaults;

public enum Defaults {
    ;

    public static final String BLENDER = "/Applications/Blender.app/Contents/MacOS/Blender";
    public static final String PLANETS_BLEND = Defaults.class.getResource("/planets.blend").getPath();
    public static final String BLENDER_SCRIPT = Defaults.class.getResource("/planet_render.py").getPath();
    public static final String HEIGHT_MAP = Defaults.class.getResource("/height_map_16K.png").getPath();
    public static final String COLOR_MAP = Defaults.class.getResource("/worldmap_16K.png").getPath();
    public static final String CLOUDS_MAP = Defaults.class.getResource("/default_clouds_map.tif").getPath();
}
