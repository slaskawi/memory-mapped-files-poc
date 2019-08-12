package org.keycloak;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class MemoryMappedFileReader implements FileReader {

    @Override
    public String readFile(Path file) throws IOException {
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(file, EnumSet.of(StandardOpenOption.READ))) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            return Charset.forName("UTF-8").decode(mappedByteBuffer).toString();
        }
    }
}
