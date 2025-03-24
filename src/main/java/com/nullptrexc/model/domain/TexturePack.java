package com.nullptrexc.model.domain;

import com.nullptrexc.model.domain.mcmeta.Features;
import com.nullptrexc.model.domain.mcmeta.Pack;
import com.nullptrexc.model.domain.mcmeta.filter.Filter;
import com.nullptrexc.model.domain.mcmeta.overlays.Overlays;

/**
 * A class representative of the currently loaded texturepack
 */
public class TexturePack {
    private Pack pack;
    private Filter filter;
    private Features features;
    private Overlays overlays;

//    TexturePack() {
//        this.pack = new Pack();
//    }

    public Pack getPack() {
        return pack;
    }

    public TexturePack setPack(Pack pack) {
        this.pack = pack;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public TexturePack setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Features getFeatures() {
        return features;
    }

    public TexturePack setFeatures(Features features) {
        this.features = features;
        return this;
    }

    public Overlays getOverlays() {
        return overlays;
    }

    public TexturePack setOverlays(Overlays overlays) {
        this.overlays = overlays;
        return this;
    }
}
