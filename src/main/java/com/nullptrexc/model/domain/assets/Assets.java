package com.nullptrexc.model.domain.assets;

public class Assets {
    private String namespace;

    public String getNamespace() {
        return namespace;
    }

    public Assets setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public Assets(String namespace) {
        this.namespace = namespace;
    }
}
