package com.nullptrexc.texturerepacker.core.domain.pack;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("pack")
public class Pack {
    @JsonProperty
    private PackFormat pack_format;
    @JsonProperty
    private String description;

    public PackFormat getPack_format() {
        return pack_format;
    }

    public Pack setPack_format(PackFormat pack_format) {
        this.pack_format = pack_format;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Pack setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "pack_format=" + pack_format +
                ", description='" + description + '\'' +
                '}';
    }
}
