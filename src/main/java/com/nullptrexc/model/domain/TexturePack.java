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
}
