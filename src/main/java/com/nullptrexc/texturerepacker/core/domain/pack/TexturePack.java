package com.nullptrexc.texturerepacker.core.domain.pack;

import java.nio.file.Path;
import java.util.List;

public class TexturePack {
    private String name;
    private Pack mcmeta;
    private List<Path> assets = List.of();

    @Override
    public String toString() {
        return "TexturePack{" +
                "name=" + name +
                ", mcmeta=" + mcmeta +
                ", textures=" + assets +
                '}';
    }
    
    public String getName() {
        return name;
    }
    
    public TexturePack setName(String name) {
        this.name = name;
        return this;
    }

    public Pack getMcmeta() {
        return mcmeta;
    }
    
    public TexturePack setMcmeta(Pack mcmeta) {
        this.mcmeta = mcmeta;
        return this;
    }

    public List<Path> getAssets() {
        return assets;
    }

    public TexturePack setAssets(List<Path> assets) {
        this.assets = assets;
        return this;
    }
}
