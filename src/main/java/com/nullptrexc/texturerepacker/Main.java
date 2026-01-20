package com.nullptrexc.texturerepacker;

import com.nullptrexc.texturerepacker.config.loader.PackFormatConfigLoader;
import com.nullptrexc.texturerepacker.config.model.PackFormatConfig;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PackFormatConfigLoader loader = new PackFormatConfigLoader();
        PackFormatConfig packFormatConfig = null;

        try {
            packFormatConfig = loader.load("formats/pack-format-1.yaml");
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(packFormatConfig));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
