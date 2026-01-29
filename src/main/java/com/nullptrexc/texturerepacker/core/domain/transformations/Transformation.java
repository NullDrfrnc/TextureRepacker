package com.nullptrexc.texturerepacker.core.domain.transformations;

public abstract class Transformation {
    protected final String from;
    protected final String to;
    
    public Transformation(String from, String to) {
        this.from = from;
        this.to = to;
    }
    
    public abstract void Apply();
    public abstract Transformation reversed();
}
