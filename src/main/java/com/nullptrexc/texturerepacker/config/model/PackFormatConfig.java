package com.nullptrexc.texturerepacker.config.model;

import com.nullptrexc.texturerepacker.core.domain.pack.PackFormat;

public class PackFormatConfig {
    private PackFormat format;
    private PathsConfig paths;

    public PackFormat getFormat() {
        return format;
    }

    public PackFormatConfig setFormat(PackFormat format) {
        this.format = format;
        return this;
    }

    public PathsConfig getPaths() {
        return paths;
    }

    public PackFormatConfig setPaths(PathsConfig paths) {
        this.paths = paths;
        return this;
    }
}
