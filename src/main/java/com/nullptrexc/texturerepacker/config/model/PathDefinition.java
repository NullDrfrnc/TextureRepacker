package com.nullptrexc.texturerepacker.config.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PathDefinition {
    private String path;
    private List<String> categories;

    public String resolvePath(@NotNull String namespace, @NotNull String category) {
        return path
                .replace("{namespace}", namespace)
                .replace("{category}", category);
    }
    
    public String resolvePath(@NotNull String namespace) {
        return path.replace("{namespace}", namespace);
    }

    public String getPath() {
        return path;
    }

    public PathDefinition setPath(String path) {
        this.path = path;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public PathDefinition setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }
}
