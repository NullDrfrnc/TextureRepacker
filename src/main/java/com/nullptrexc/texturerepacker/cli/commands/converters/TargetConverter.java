package com.nullptrexc.texturerepacker.cli.commands.converters;

import com.nullptrexc.texturerepacker.core.domain.pack.MCVersion;
import com.nullptrexc.texturerepacker.core.domain.pack.PackFormat;
import picocli.CommandLine;

public class TargetConverter implements CommandLine .ITypeConverter<TargetConverter.Target> {
    @Override
    public Target convert(String value) {
        if(value.contains(".")) {
            return new Target(new MCVersion(value));
        }
        else {
            PackFormat packFormat = PackFormat.findPackFormat(Integer.parseInt(value));
            if(packFormat != null) {
                return new Target(packFormat);
            }
        }
        throw new CommandLine.TypeConversionException(
                "Invalid targer: " + value + " is not a valid Minecraft version or pack format"
        );
    }

    public static class Target {
        private final MCVersion version;
        private final PackFormat packFormat;

        public Target(MCVersion version) {
            this.version = version;
            this.packFormat = null;
        }

        public Target(PackFormat packFormat) {
            this.version = null;
            this.packFormat = packFormat;
        }

        @Override
        public String toString() {
            return isVersion() ? "version: " + version : "pack format: " + packFormat;
        }

        public PackFormat getPackFormat() {
            return isVersion() ? PackFormat.findPackFormat(version) : packFormat;
        }

        public boolean isVersion() {
            return version != null;
        }
    }
}
