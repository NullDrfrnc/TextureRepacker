package com.nullptrexc;

import com.nullptrexc.model.domain.PackFormat;
import com.nullptrexc.model.domain.TexturePack;
import com.nullptrexc.model.domain.mcmeta.Pack;

import java.io.File;
import java.nio.file.Paths;

public class TextureRepackerApp {
    private static TextureRepackerApp instance;

    private TexturePack texturePack;
    private final File currentDir;
    private TextureRepackerApp() {
        currentDir = new File(Paths.get("").toAbsolutePath().toString());
    }

    public void run(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println(currentDir);
        System.out.println(texturePack.getPack().getPackFormat());
    }

    public static void main(String[] args) {
        TextureRepackerApp app = TextureRepackerApp.getInstance();
        app.run(args);
    }

    public static TextureRepackerApp getInstance() {
        if (instance == null) {
            instance = new TextureRepackerApp();
        }
        return instance;
    }
}