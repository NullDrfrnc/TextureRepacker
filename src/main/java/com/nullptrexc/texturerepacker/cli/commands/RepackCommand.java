package com.nullptrexc.texturerepacker.cli.commands;

import com.nullptrexc.texturerepacker.TextureRepacker;
import com.nullptrexc.texturerepacker.TextureRepackerFactory;
import com.nullptrexc.texturerepacker.cli.commands.converters.TargetConverter;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(RepackCommand.class);

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

    @Override
    public Integer call() throws Exception {
        if (!checkIsResourcePack()) {
            LOGGER.error("This is not a valid Minecraft texture pack. Please provide a valid resourcepack.zip file with a pack.mcmeta in it's root directory.");
            return 2;
        }

        textureRepacker = TextureRepackerFactory.getInstance().create(
                targetedVersion.getPackFormat()
        );

        try (StandardFileSystemManager manager = new StandardFileSystemManager()) {
            manager.init();

            FileObject resourcePack = manager.resolveFile((checkIsZipFile() ? "zip:" : "") + "file:///" + input.getPath());

            String name = input.getName();

            if (name.toLowerCase().endsWith(".zip")) {
                name = name.substring(0, name.length() - 4);
            }

            name += " | Repacked " + targetedVersion.getPackFormat();

            if (output == null) {
                output = input.getParentFile();
            }

            FileObject zipFile = manager.resolveFile(
                    "file:///" + output.getPath() + "/" + name + ".zip"
            );
            
            zipFile.copyFrom(resourcePack, Selectors.SELECT_ALL);

            if (zipFile.exists()) {
                LOGGER.error("The resource pack {} already exists.", output.getPath());
                return 2;
            }

            zipFile.createFile();
            
            crawlFolderStructure(resourcePack);
        }

        return 0;
    }

    private void crawlFolderStructure(FileObject root) throws IOException {
        for (FileObject file : root.getChildren()) {
            if (file.getType() == FileType.FOLDER) {
                crawlFolderStructure(file);
            }
//            System.out.println(file.getName().getPath());
        }
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
            String resourcepack = "/mnt/data/Documents/repositories/Java/TextureRepacker/src/main/resources/debugpacks/Classic Faithful 32x - ";
            args = new String[]{"-t", "1.20.1", "-i", resourcepack + resourcepackVersion + ".zip"};
        }
        int exitCode = new CommandLine(new RepackCommand()).execute(args);
        System.exit(exitCode);
    }
}
