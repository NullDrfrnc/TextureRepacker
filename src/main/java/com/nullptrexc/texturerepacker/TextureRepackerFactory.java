package com.nullptrexc.texturerepacker;

import com.nullptrexc.texturerepacker.core.domain.pack.PackFormat;

public class TextureRepackerFactory {
    private static final TextureRepackerFactory INSTANCE = new TextureRepackerFactory();
    
    public static TextureRepackerFactory getInstance() {
        return INSTANCE;
    }
    
    public TextureRepacker create(PackFormat targetedPackFormat) {
        return new TextureRepacker(targetedPackFormat);
    }
}
