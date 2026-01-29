package com.nullptrexc.texturerepacker.cli.commands;

import com.nullptrexc.texturerepacker.TextureRepacker;
import com.nullptrexc.texturerepacker.TextureRepackerFactory;
import com.nullptrexc.texturerepacker.cli.commands.converters.TargetConverter;
import com.nullptrexc.texturerepacker.core.domain.pack.Pack;
import com.nullptrexc.texturerepacker.core.domain.pack.TexturePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@CommandLine.Command(
        name = "repack",
        description = "Repack a Minecraft texture pack to a different version",
        mixinStandardHelpOptions = true,
        version = "1.0"
)
public class RepackCommand implements Callable<Integer> {
    public static final Logger LOGGER = LoggerFactory.getLogger(RepackCommand.class);

    @CommandLine.Option(
            names = {"-t", "--target"},
            required = true,
            description = "The targeted Minecraft version or pack format to repack to (e.g. 1.20.1 or 46)",
            converter = TargetConverter.class
    )
    private TargetConverter.Target targetedVersion;

    @CommandLine.Option(
            names = {"-i", "--input"},
            required = true,
            description = "File path of the resourcepack.zip to repack"
    )
    private File input;

    @CommandLine.Option(
            names = {"-o", "--output"},
            description = "Folder where the repacked resourcepack should be placed (uses the same directory as the input when not given)"
    )
    private File output;

    private TextureRepacker textureRepacker;

    private final ObjectMapper MAPPER = JsonMapper.builder()
            .enable(DeserializationFeature.UNWRAP_ROOT_VALUE)
            .build();

    @Override
    public Integer call() throws Exception {
        if (!checkIsResourcePack()) {
            LOGGER.error("This is not a valid Minecraft texture pack. Please provide a valid resourcepack.zip file with a pack.mcmeta in its root directory.");
            return 65;
        }

        textureRepacker = TextureRepackerFactory.getInstance().create(
                targetedVersion.getPackFormat()
        );

        try (FileSystem zipFs = FileSystems.newFileSystem(input.toPath())) {
            LOGGER.info("Repacking: {}", zipFs.toString());
            Path mcMeta = zipFs.getPath("pack.mcmeta");
            Pack pack = MAPPER.readValue(Files.newInputStream(mcMeta), Pack.class);

            String name = input.getName();

            if (name.toLowerCase().endsWith(".zip")) {
                name = name.substring(0, name.length() - 4);
            }

            TexturePack oldPack = new TexturePack().setName(name).setMcmeta(pack);

            LOGGER.info(oldPack.toString());

            name += " | Repacked " + targetedVersion.getPackFormat();

            if (output == null) {
                output = input.getParentFile();
            }
        }

        textureRepacker.repack();

        return 0;
    }

    private boolean checkIsResourcePack() throws IOException {
        if (checkIsZipFile()) {
            try (ZipFile zipFile = new ZipFile(input)) {
                ZipEntry mcMeta = zipFile.getEntry("pack.mcmeta");
                return mcMeta != null;
            }
        }
        return false;
    }

    private boolean checkIsZipFile() throws IOException {
        return "application/zip".equals(Files.probeContentType(input.toPath()));
    }

    public static void main(String[] args) {
        if (args.length == 0) {
//            String resourcepackVersion = "1.21.x";
//            String resourcepack = "VanillaTweaks_r993402_MC";
            String resourcepackVersion = "1.8.9";
//            String resourcepackVersion = "1.16.5";
            // DO NOT COMMIT!!!!!!
            String resourcepack = "/home/null/Repos/TextureRepacker/src/main/resources/debugpacks/Classic Faithful 32x - ";
            args = new String[]{"-t", "1.20.1", "-i", resourcepack + resourcepackVersion + ".zip"};
        }
        int exitCode = new CommandLine(new RepackCommand()).execute(args);
        System.exit(exitCode);
    }
}
