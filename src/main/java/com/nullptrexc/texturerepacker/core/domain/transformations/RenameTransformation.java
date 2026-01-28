package com.nullptrexc.texturerepacker.core.domain.transformations;

public class RenameTransformation extends Transformation {
    public RenameTransformation(String from, String to) {
        super(from, to);
    }

    @Override
    public void Apply() {
        
    }

    @Override
    public Transformation reversed() {
        return new RenameTransformation(to, from);
    }
}
