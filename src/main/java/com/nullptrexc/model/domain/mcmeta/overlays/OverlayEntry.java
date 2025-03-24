package com.nullptrexc.model.domain.mcmeta.overlays;

import java.util.ArrayList;

public class OverlayEntry {
    private String directory;
    private ArrayList<Integer> formats;

    public OverlayEntry(String directory, ArrayList<Integer> formats) {
        this.directory = directory;
        this.formats = formats;
    }

    public String getDirectory() {
        return directory;
    }

    public OverlayEntry setDirectory(String directory) {
        this.directory = directory;
        return this;
    }

    public ArrayList<Integer> getFormats() {
        return formats;
    }

    public OverlayEntry setFormats(ArrayList<Integer> formats) {
        this.formats = formats;
        return this;
    }
}
