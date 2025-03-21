package com.nullptrexc.model.domain.mcmeta.filter;

public class BlockEntry {
    private String namespace;
    private String path;

    public BlockEntry(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    public String getNamespace() {
        return namespace;
    }

    public BlockEntry setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public String getPath() {
        return path;
    }

    public BlockEntry setPath(String path) {
        this.path = path;
        return this;
    }
}
