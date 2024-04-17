package org.pasqg.planetgenerator.defaults;

public enum Defaults {
    ;

    public static final String BLENDER = "/Applications/Blender 4.0.2.app/Contents/MacOS/Blender";
    public static final String PLANETS_BLEND = Defaults.class.getResource("/planets.blend").getPath();
    public static final String BLENDER_SCRIPT = Defaults.class.getResource("/planet_render.py").getPath();
    public static final String HEIGHT_MAP = Defaults.class.getResource("/default_height_map.png").getPath();
    public static final String COLOR_MAP = Defaults.class.getResource("/default_color_map.tif").getPath();
}
