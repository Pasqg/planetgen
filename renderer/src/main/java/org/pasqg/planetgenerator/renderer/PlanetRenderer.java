package org.pasqg.planetgenerator.renderer;

import org.pasqg.planetgenerator.defaults.Defaults;

import java.io.IOException;

public enum PlanetRenderer {
    ;

    public static void main(String[] args) throws IOException, InterruptedException {
        PlanetRenderer.render(new RenderArguments()
                .setSizeX(1024)
                .setSizeY(1024)
                .setSamples(64)
                .setClouds(true)
                .setBlendFile(Defaults.PLANETS_BLEND)
                .setRenderScript(Defaults.BLENDER_SCRIPT)
                .setCameras("full")
                .setColorMap(Defaults.COLOR_MAP)
                .setHeightMap(Defaults.HEIGHT_MAP)
        );
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
