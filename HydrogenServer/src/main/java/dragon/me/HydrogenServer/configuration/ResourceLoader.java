package dragon.me.HydrogenServer.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

import dragon.me.HydrogenServer.exceptions.ServerConfigurationError;

public class ResourceLoader {

    /**
     * Copies a resource from inside the JAR to the working directory if missing.
     * If the file already exists, it is left untouched.
     */
    public static void copyToDisk(String resourceName) {
        Path dest = Paths.get(resourceName);

        // Do not overwrite existing configs (server owner might have edited it)
        if (Files.exists(dest)) {
            return;
        }

        try (InputStream inputStream = ResourceLoader.class.getClassLoader()
                                                           .getResourceAsStream(resourceName)) {

            if (inputStream == null) {
                ServerConfigurationError error = new ServerConfigurationError(
                        "Failed to get the " + resourceName + " file!",
                        "You may be using an external / damaged version of HydrogenMC. " +
                        "Please redownload the server JAR from the official site.",
                        1
                );
                error.log(ResourceLoader.class, "Resource not found inside JAR.");
                return;
            }

            Files.copy(inputStream, dest, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            ServerConfigurationError error = new ServerConfigurationError(
                    "An error occurred while copying " + resourceName + " from JAR",
                    "Try downloading an undamaged copy of HydrogenMC from official sources.",
                    1
            );
            error.log(ResourceLoader.class, e.getMessage());
        }
    }
}