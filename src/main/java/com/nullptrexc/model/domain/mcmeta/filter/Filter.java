package com.nullptrexc.model.domain.mcmeta.filter;

import java.util.ArrayList;

public class Filter {
    private ArrayList<BlockEntry> blocks;

    public Filter(ArrayList<BlockEntry> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<BlockEntry> getBlocks() {
        return blocks;
    }

    public Filter setBlocks(ArrayList<BlockEntry> blocks) {
        this.blocks = blocks;
        return this;
    }
}
