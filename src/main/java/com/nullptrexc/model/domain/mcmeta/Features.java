package com.nullptrexc.model.domain.mcmeta;

import java.util.ArrayList;

public class Features {
    private ArrayList<String> enabled;

    public Features(ArrayList<String> enabled) {
        this.enabled = enabled;
    }

    public ArrayList<String> getEnabled() {
        return enabled;
    }

    public Features setEnabled(ArrayList<String> enabled) {
        this.enabled = enabled;
        return this;
    }
}
