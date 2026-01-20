package com.nullptrexc.texturerepacker;

import com.nullptrexc.texturerepacker.core.domain.PackFormat;

public class TextureRepacker {
    private final PackFormat targetedPackFormat;

    TextureRepacker(PackFormat targetedPackFormat) {
        this.targetedPackFormat = targetedPackFormat;
    }

    public PackFormat getTargetedPackFormat() {
        return this.targetedPackFormat;
    }
}
