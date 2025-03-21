package com.nullptrexc.model.domain.mcmeta.overlays;

import java.util.ArrayList;

public class Overlays {
    private ArrayList<OverlayEntry> entries;

    public Overlays(ArrayList<OverlayEntry> entries) {
        this.entries = entries;
    }

    public ArrayList<OverlayEntry> getEntries() {
        return entries;
    }

    public Overlays setEntries(ArrayList<OverlayEntry> entries) {
        this.entries = entries;
        return this;
    }
}
