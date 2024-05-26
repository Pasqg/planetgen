package org.pasqg.planetgenerator.renderer;

import org.pasqg.planetgenerator.utils.ArgumentsBuilder;

public class RenderArguments {
    private String mBlendFile = "<blend file>";
    private String mRenderScript = "<render script>";
    private int mSizeX = 256;
    private int mSizeY = 256;
    private int mSamples = 32;
    private boolean mClouds = true;
    private boolean mGPU = true;
    private String mCameras = "full";
    private String mOutputFilePrefix = "output";
    private String mColorMap = null;
    private String mHeightMap = null;
    private String mCloudsMap = null;
    private double mPlanetRotationX = 0.0;
    private double mPlanetRotationY = 0.0;
    private double mPlanetRotationZ = 0.0;
    private double mCloudsRotationX = 0.0;
    private double mCloudsRotationY = 0.0;
    private double mCloudsRotationZ = 0.0;

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

    public String getOutputFilePrefix() {
        return mOutputFilePrefix;
    }

    public RenderArguments setOutputFilePrefix(String aOutputFilePrefix) {
        mOutputFilePrefix = aOutputFilePrefix;
        return this;
    }

    public boolean isGPU() {
        return mGPU;
    }

    public RenderArguments setGPU(boolean aGPU) {
        mGPU = aGPU;
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

    public String getCloudsMap() {
        return mCloudsMap;
    }

    public RenderArguments setCloudsMap(String aCloudsMap) {
        mCloudsMap = aCloudsMap;
        return this;
    }

    public double getPlanetRotationX() {
        return mPlanetRotationX;
    }

    public RenderArguments setPlanetRotationX(double aPlanetRotationX) {
        mPlanetRotationX = aPlanetRotationX;
        return this;
    }

    public double getPlanetRotationY() {
        return mPlanetRotationY;
    }

    public RenderArguments setPlanetRotationY(double aPlanetRotationY) {
        mPlanetRotationY = aPlanetRotationY;
        return this;
    }

    public double getPlanetRotationZ() {
        return mPlanetRotationZ;
    }

    public RenderArguments setPlanetRotationZ(double aPlanetRotationZ) {
        mPlanetRotationZ = aPlanetRotationZ;
        return this;
    }

    public double getCloudsRotationX() {
        return mCloudsRotationX;
    }

    public RenderArguments setCloudsRotationX(double aCloudsRotationX) {
        mCloudsRotationX = aCloudsRotationX;
        return this;
    }

    public double getCloudsRotationY() {
        return mCloudsRotationY;
    }

    public RenderArguments setCloudsRotationY(double aCloudsRotationY) {
        mCloudsRotationY = aCloudsRotationY;
        return this;
    }

    public double getCloudsRotationZ() {
        return mCloudsRotationZ;
    }

    public RenderArguments setCloudsRotationZ(double aCloudsRotationZ) {
        mCloudsRotationZ = aCloudsRotationZ;
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
                .addArgument("-cameras", mCameras)
                .addArgument("-outputFilePrefix", mOutputFilePrefix)
                .addArgument("-planetRotationX", mPlanetRotationX)
                .addArgument("-planetRotationY", mPlanetRotationY)
                .addArgument("-planetRotationZ", mPlanetRotationZ)
                .addArgument("-cloudsRotationX", mCloudsRotationX)
                .addArgument("-cloudsRotationY", mCloudsRotationY)
                .addArgument("-cloudsRotationZ", mCloudsRotationZ)
                .addArgument("-gpu", mGPU);
        if (mColorMap != null) {
            builder.addArgument("-colorMap", mColorMap);
        }
        if (mHeightMap != null) {
            builder.addArgument("-heightMap", mHeightMap);
        }
        if (mCloudsMap != null) {
            builder.addArgument("-cloudsMap", mCloudsMap);
        }
        return builder;
    }
}