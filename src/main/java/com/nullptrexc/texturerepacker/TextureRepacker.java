package com.nullptrexc.texturerepacker;

import com.nullptrexc.texturerepacker.cli.commands.RepackCommand;
import com.nullptrexc.texturerepacker.core.domain.pack.Pack;
import com.nullptrexc.texturerepacker.core.domain.pack.PackFormat;

public class TextureRepacker {
    private final PackFormat targetedPackFormat;

    TextureRepacker(PackFormat targetedPackFormat) {
        this.targetedPackFormat = targetedPackFormat;
    }

    public void repack() {
        Pack pack = new Pack().setPack_format(targetedPackFormat);

        RepackCommand.LOGGER.info(pack.toString());
    }

    public PackFormat getTargetedPackFormat() {
        return this.targetedPackFormat;
    }
}
