package com.nullptrexc.texturerepacker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("pack")
public class Pack {
    @JsonProperty
    private PackFormat pack_format;
    @JsonProperty
    private String description;

    @Override
    public String toString() {
        return "Pack{" +
                "pack_format=" + pack_format +
                ", description='" + description + '\'' +
                '}';
    }
}
