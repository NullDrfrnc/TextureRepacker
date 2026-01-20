package com.nullptrexc.texturerepacker.config.loader;


import com.nullptrexc.texturerepacker.config.model.PackFormatConfig;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

public class PackFormatConfigLoader {

    private final ObjectMapper mapper;

    public PackFormatConfigLoader() {
        mapper = new ObjectMapper(new YAMLFactory());
    }

    public PackFormatConfig load(String path) throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(path)) {
            if (input == null)
                throw new IOException("Could not load config file: " + path);

            return mapper.readValue(input, PackFormatConfig.class);
        }
    }
}
