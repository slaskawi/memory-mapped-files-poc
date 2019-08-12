package org.keycloak;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainClass {

    public static void main(String[] args) throws IOException {
        System.out.println("==== This is a little helper program to get a experiment with vaults and heap dumps");

        Path secret = Paths.get("./src/test/resources/short-secret");
        FileReader fileReader = new MemoryMappedFileReader();
        WeakReference<String> reference = new WeakReference<>(fileReader.readFile(secret));

        System.out.println("==== Now obtain a heapdump and hit enter");
        System.out.println("reference.get() = " + reference.get());
        System.in.read();
        System.gc();

        System.out.println("==== Now obtain a second heapdump and hit enter to exit");
        System.out.println("reference.get() = " + reference.get());
        System.in.read();
    }

}
