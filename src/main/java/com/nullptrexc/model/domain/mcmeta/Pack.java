package com.nullptrexc.model.domain.mcmeta;

import com.nullptrexc.model.domain.PackFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Pack {
    @NotNull
    private PackFormat pack_format;
    @Nullable
    private ArrayList<PackFormat> supported_formats;
    @NotNull
    private String description;

    public Pack(@NotNull PackFormat pack_format, @Nullable ArrayList<PackFormat> supported_formats, @NotNull String description) {
        this.pack_format = pack_format;
        this.supported_formats = supported_formats;
        this.description = description;
    }

    public Pack(@NotNull PackFormat pack_format, @NotNull String description) {
        this.pack_format = pack_format;
        this.description = description;
    }

    public PackFormat getPackFormat() {
        return pack_format;
    }

    public Pack setPackFormat(PackFormat pack_format) {
        this.pack_format = pack_format;
        return this;
    }

    public ArrayList<PackFormat> getSupportedFormats() {
        return supported_formats;
    }

    public Pack setSupportedFormats(ArrayList<PackFormat> supported_formats) {
        this.supported_formats = supported_formats;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Pack setDescription(String description) {
        this.description = description;
        return this;
    }
}
