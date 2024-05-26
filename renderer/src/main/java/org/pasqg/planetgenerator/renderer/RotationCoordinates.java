package org.pasqg.planetgenerator.renderer;

public record RotationCoordinates(double degreesX, double degreesY, double degreesZ) {
    public static RotationCoordinates of(double aDegreesX, double aDegreesY, double aDegreesZ) {
        return new RotationCoordinates(aDegreesX, aDegreesY, aDegreesZ);
    }
}
