package org.pasqg.planetgenerator.renderer;

import org.pasqg.planetgenerator.defaults.Defaults;

import java.io.IOException;

public enum PlanetRenderer {
    ;

    public static void main(String[] args) throws IOException, InterruptedException {
        PlanetRenderer.render(args(4096, 256,"closeup")
                .setSizeY(2560));
    }

    private static RenderArguments args(int aResolution, int aSamples, String aCamera) {
        int resolutionY = switch (aCamera) {
            case "full" -> aResolution;
            case "closeup", "half" -> aResolution / 2;
            default -> throw new IllegalStateException("Unexpected value: " + aCamera);
        };
        return new RenderArguments()
                .setSizeX(aResolution)
                .setSizeY(resolutionY)
                .setSamples(aSamples)
                .setClouds(true)
                .setGPU(true)
                .setBlendFile(Defaults.PLANETS_BLEND)
                .setRenderScript(Defaults.BLENDER_SCRIPT)
                .setCameras(aCamera)
                .setColorMap(Defaults.COLOR_MAP)
                .setHeightMap(Defaults.HEIGHT_MAP)
                .setCloudsMap(Defaults.CLOUDS_MAP);
    }

    public static void render(RenderArguments aArgs) throws IOException, InterruptedException {
        ProcessBuilder processBuilder =
                new ProcessBuilder(aArgs.toArgumentBuilder().toList(Defaults.BLENDER));
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

        //todo: proper logging
        System.out.println(String.join(" ", processBuilder.command()));
        Process process = processBuilder.start();
        process.waitFor();
    }
}
