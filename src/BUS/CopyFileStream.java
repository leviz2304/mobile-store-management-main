package BUS;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyFileStream {
    String currentDirectory;

    public CopyFileStream() {
        currentDirectory = System.getProperty("user.dir");
    }

    public static void main(String[] args) throws IOException {

        var source = new File("src/resources/bugs.txt");
        var dest = new File("src/resources/bugs2.txt");

        try (var fis = new FileInputStream(source);
             var fos = new FileOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {

                fos.write(buffer, 0, length);
            }
        }
    }
    public void copyAnhSP(File source, String idTarget) throws IOException {

        String path = currentDirectory + "/src/GUI/SP/" + idTarget + ".png";
        Path pathToFile = Paths.get(path);
        System.out.println(path);
        Files.copy(source.toPath(), pathToFile.toAbsolutePath(), StandardCopyOption.REPLACE_EXISTING);
    }
    public void copyAnhNV(File source, String idTarget) throws IOException {

        String path = currentDirectory + "/src/GUI/NV/" + idTarget + ".png";
        Path pathToFile = Paths.get(path);
        System.out.println(path);
        Files.copy(source.toPath(), pathToFile.toAbsolutePath(), StandardCopyOption.REPLACE_EXISTING);
    }
}