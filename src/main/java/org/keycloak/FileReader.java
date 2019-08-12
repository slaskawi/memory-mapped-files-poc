package org.keycloak;

import java.io.IOException;
import java.nio.file.Path;

public interface FileReader {
    String readFile(Path file) throws IOException;
}
