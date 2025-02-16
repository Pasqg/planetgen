package org.pasqg.planetgenerator.renderer;

import org.pasqg.planetgenerator.defaults.Defaults;

public enum RenderPresets {
    AMERICA_FULL(CameraAngle.FULL,
            latLon(90, -220+180),
            latLon(0, -220+180),
            1.6),
    AFRICA_FULL(CameraAngle.FULL,
            RotationCoordinates.of(0, 0, -160),
            RotationCoordinates.of(4, -20, 73),
            1.6),
    AFRICA_FULL_SOUTH(CameraAngle.FULL,
            RotationCoordinates.of(0, -22, -167),
            RotationCoordinates.of(4, -20, 73),
            1.6),
    EUROPE_FULL(CameraAngle.FULL,
            RotationCoordinates.of(0, 30, -160),
            RotationCoordinates.of(4, -20, 73),
            1.6),
    AUSTRALIA_FULL(CameraAngle.FULL,
            RotationCoordinates.of(0, 50, 70),
            RotationCoordinates.of(0, 50, 73),
            1.6),
    SOUTH_EAST_ASIA_FULL(CameraAngle.FULL,
            RotationCoordinates.of(0, 22, 100),
            RotationCoordinates.of(4, -20, 73),
            1.6),
    AUSTRALIA_CLOSEUP(CameraAngle.FULL,
            RotationCoordinates.of(0, 50, -160),
            RotationCoordinates.of(0, 50, 85),
            1.6),
    EUROPE_NORTH_POLE_FULL(CameraAngle.FULL,
            RotationCoordinates.of(0, 35, -160),
            RotationCoordinates.of(0, 35, -160),
            1.6),
    WESTERN_ATLANTIC_CLOSEUP(CameraAngle.CLOSEUP,
            RotationCoordinates.of(0, 35, -160),
            RotationCoordinates.of(0, 35, -160),
            1.6),
    NEW_ZEALAND_FULL(CameraAngle.FULL,
            latLon(-40, -174),
            latLon(-40, -174),
            1.6),
    ;

    private final CameraAngle mCameraAngle;
    private final RotationCoordinates mWorldRotation;
    private final RotationCoordinates mCloudsRotation;
    private final double mAspectRatio;

    RenderPresets(CameraAngle aCameraAngle, RotationCoordinates aWorldRotation, RotationCoordinates aCloudsRotation) {
        mCameraAngle = aCameraAngle;
        mWorldRotation = aWorldRotation;
        mCloudsRotation = aCloudsRotation;
        mAspectRatio = 1.0;
    }

    RenderPresets(CameraAngle aCameraAngle,
                  RotationCoordinates aWorldRotation,
                  RotationCoordinates aCloudsRotation,
                  double aAspectRatio) {
        mCameraAngle = aCameraAngle;
        mWorldRotation = aWorldRotation;
        mCloudsRotation = aCloudsRotation;
        mAspectRatio = aAspectRatio;
    }

    public RenderArguments getArguments(int aResolution) {
        return new RenderArguments()
                .setSizeX(aResolution)
                .setSizeY((int) (aResolution / mAspectRatio))
                .setClouds(true)
                .setGPU(true)
                .setBlendFile(Defaults.PLANETS_BLEND)
                .setRenderScript(Defaults.BLENDER_SCRIPT)
                .setCameras(mCameraAngle.name().toLowerCase())
                .setColorMap(Defaults.COLOR_MAP)
                .setHeightMap(Defaults.HEIGHT_MAP)
                .setCloudsMap(Defaults.CLOUDS_MAP)
                .setPlanetRotationX(mWorldRotation.degreesX())
                .setPlanetRotationY(mWorldRotation.degreesY())
                .setPlanetRotationZ(mWorldRotation.degreesZ())
                .setCloudsRotationX(mCloudsRotation.degreesX())
                .setCloudsRotationY(mCloudsRotation.degreesY())
                .setCloudsRotationZ(mCloudsRotation.degreesZ());
    }

    /**
     * Coordinates from latitude and longitude
     */
    public static RotationCoordinates latLon(double aDegreesNorth, double aDegreesWest) {
        return RotationCoordinates.of(aDegreesNorth, 0, 220 + aDegreesWest);
    }
}
