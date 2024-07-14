package Data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatabaseUtils {
    public static String extractDatabase(String resourcePath, String destinationFileName) throws IOException {
        InputStream inputStream = DatabaseUtils.class.getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new FileNotFoundException("Database resource not found: " + resourcePath);
        }
        Path tempFile = Files.createTempFile(destinationFileName, ".sqlite");
        try (OutputStream outputStream = Files.newOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        inputStream.close();
        return tempFile.toAbsolutePath().toString();
    }
}
