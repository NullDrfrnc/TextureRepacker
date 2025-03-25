package com.nullptrexc;

import com.nullptrexc.model.domain.TexturePack;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextureRepackerApp {
    private static TextureRepackerApp instance;

    private final File currentDir;
    private TexturePack texturePack;

    private TextureRepackerApp() {
        currentDir = new File(Paths.get("").toAbsolutePath().toString());
    }

    public static void main(String[] args) {
        TextureRepackerApp app = TextureRepackerApp.getInstance();
        app.run(args);
    }

    public void run(String[] args) {
        convertMcMetaToJson(findMcmeta());
    }

    private void convertMcMetaToJson(File mcMeta) {
        try {
            String mcMetaContent = new String(Files.readAllBytes(mcMeta.toPath()));
            assert !mcMetaContent.isEmpty() : "pack.mcmeta is an empty file!";
            JSONObject json = new JSONObject(mcMetaContent);
            System.out.println(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static TextureRepackerApp getInstance() {
        if (instance == null) {
            instance = new TextureRepackerApp();
        }
        return instance;
    }

    public File findMcmeta() {
        File packMc = new File(currentDir, "pack.mcmeta");
        assert !packMc.exists() : "Could not find pack.mcmeta!";
        return packMc;
    }
}