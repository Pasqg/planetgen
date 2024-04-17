package org.pasqg.planetgenerator.renderer;

import org.pasqg.planetgenerator.utils.ArgumentsBuilder;

public class RenderArguments {
    private String mBlendFile = "<blend file>";
    private String mRenderScript = "<render script>";
    private int mSizeX = 256;
    private int mSizeY = 256;
    private int mSamples = 32;
    private boolean mClouds = true;
    private String mCameras = "full";
    private String mColorMap = null;
    private String mHeightMap = null;

    public int getSizeX() {
        return mSizeX;
    }

    public RenderArguments setSizeX(int aSizeX) {
        mSizeX = aSizeX;
        return this;
    }

    public int getSizeY() {
        return mSizeY;
    }

    public RenderArguments setSizeY(int aSizeY) {
        mSizeY = aSizeY;
        return this;
    }

    public int getSamples() {
        return mSamples;
    }

    public RenderArguments setSamples(int aSamples) {
        mSamples = aSamples;
        return this;
    }

    public boolean isClouds() {
        return mClouds;
    }

    public RenderArguments setClouds(boolean aClouds) {
        mClouds = aClouds;
        return this;
    }

    public String getBlendFile() {
        return mBlendFile;
    }

    public RenderArguments setBlendFile(String aBlendFile) {
        mBlendFile = aBlendFile;
        return this;
    }

    public String getRenderScript() {
        return mRenderScript;
    }

    public RenderArguments setRenderScript(String aRenderScript) {
        mRenderScript = aRenderScript;
        return this;
    }

    public String getCameras() {
        return mCameras;
    }

    public RenderArguments setCameras(String aCameras) {
        mCameras = aCameras;
        return this;
    }

    public String getColorMap() {
        return mColorMap;
    }

    public RenderArguments setColorMap(String aColorMap) {
        mColorMap = aColorMap;
        return this;
    }

    public String getHeightMap() {
        return mHeightMap;
    }

    public RenderArguments setHeightMap(String aHeightMap) {
        mHeightMap = aHeightMap;
        return this;
    }

    public ArgumentsBuilder toArgumentBuilder() {
        ArgumentsBuilder builder = new ArgumentsBuilder()
                .addArgument("-b", mBlendFile)
                .addArgument("-P", mRenderScript)
                .addArgument("--", "")
                .addArgument("-x", mSizeX)
                .addArgument("-y", mSizeY)
                .addArgument("-samples", mSamples)
                .addArgument("-clouds", mClouds)
                .addArgument("-cameras", mCameras);
        if (mColorMap != null) {
            builder.addArgument("-colorMap", mColorMap);
        }
        if (mHeightMap != null) {
            builder.addArgument("-heightMap", mHeightMap);
        }
        return builder;
    }
}