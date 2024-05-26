package org.pasqg.planetgenerator.renderer;

import org.pasqg.planetgenerator.defaults.Defaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public enum PlanetRenderer {
    ;
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanetRenderer.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        PlanetRenderer.render(RenderPresets.WESTERN_ATLANTIC_CLOSEUP.getArguments(6144)
                .setSamples(256));
    }

    public static void render(RenderArguments aArgs) throws IOException, InterruptedException {
        ProcessBuilder processBuilder =
                new ProcessBuilder(aArgs.toArgumentBuilder().toList(Defaults.BLENDER));
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

        LOGGER.info(String.join(" ", processBuilder.command()));
        Process process = processBuilder.start();
        process.waitFor();
    }
}
