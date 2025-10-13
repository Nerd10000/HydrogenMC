package dragon.me.configuration;

import java.io.File;
import com.typesafe.config.*;

import dragon.me.exceptions.ServerException;

public class ServerConfiguration {

    private static final String CONFIG_NAME = "hydrogen.conf";

    /**
     * Loads the external hydrogen.conf (from the working directory).
     * If missing, tries to copy the default one from resources.
     */
    public static Config loadConfig() {
        File file = new File(CONFIG_NAME);

        if (!file.exists()) {
            // Try to copy default config from JAR resources
            ResourceLoader.copyToDisk(CONFIG_NAME);

            if (!file.exists()) {
                // Still missing → throw and log properly
                ServerException exception = new ServerException(
                        "Ooops! File not found",
                        "HydrogenMC could not create a " + CONFIG_NAME + ". " +
                        "Please restart the server or redownload the jar.",
                        2
                );
                exception.log(ServerConfiguration.class, "Config missing");
                throw exception; // Don't return null → fail fast
            }
        }

        return ConfigFactory.parseFile(file).resolve();
    }
}