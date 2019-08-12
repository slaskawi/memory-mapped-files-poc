package org.keycloak;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileInputStreamReader implements FileReader {

    @Override
    public String readFile(Path file) throws IOException {
        try (InputStream inputStream = Files.newInputStream(file)) {
            byte[] buf = new byte[inputStream.available()];
            inputStream.read(buf);

            //prints out
//            byte[0] = 84(54)
//            byte[1] = 104(68)
//            byte[2] = 105(69)
//            byte[3] = 115(73)
//            byte[4] = 32(20)
//            byte[5] = 105(69)
//            byte[6] = 115(73)
            System.out.println("byte[0] = " + buf[0] + "(" + Integer.toHexString(buf[0]) + ")");
            System.out.println("byte[1] = " + buf[1] + "(" + Integer.toHexString(buf[1]) + ")");
            System.out.println("byte[2] = " + buf[2] + "(" + Integer.toHexString(buf[2]) + ")");
            System.out.println("byte[3] = " + buf[3] + "(" + Integer.toHexString(buf[3]) + ")");
            System.out.println("byte[4] = " + buf[4] + "(" + Integer.toHexString(buf[4]) + ")");
            System.out.println("byte[5] = " + buf[5] + "(" + Integer.toHexString(buf[5]) + ")");
            System.out.println("byte[6] = " + buf[6] + "(" + Integer.toHexString(buf[6]) + ")");
            return new String(buf);
        }
    }
}
