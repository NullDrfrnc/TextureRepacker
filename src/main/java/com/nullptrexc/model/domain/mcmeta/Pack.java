package com.nullptrexc.model.domain.mcmeta;

import java.util.ArrayList;

public class Pack {
    private int pack_format;
    private ArrayList<Integer> supported_formats;
    private String description;

    public Pack(int pack_format,ArrayList<Integer> supported_formats , String description) {
        this.pack_format = pack_format;
        this.supported_formats = supported_formats;
        this.description = description;
    }

    public int getPackFormat() {
        return pack_format;
    }

    public Pack setPackFormat(int pack_format) {
        this.pack_format = pack_format;
        return this;
    }

    public ArrayList<Integer> getSupportedFormats() {
        return supported_formats;
    }

    public Pack setSupportedFormats(ArrayList<Integer> supported_formats) {
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
