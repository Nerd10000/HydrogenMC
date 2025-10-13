package dragon.me;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dragon.me.commands.GameModeCommand;
import dragon.me.configuration.ServerConfiguration;
import dragon.me.core.events.PlayerBlockBreak;
import dragon.me.core.events.PlayerInventoryPickupEvent;
import dragon.me.core.events.PlayerLoginEvent;
import dragon.me.core.world.generators.FlatChunkGenerator;
import dragon.me.utils.PerformanceLogger;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.MinestomAdventure;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static MinecraftServer server;
    private static InstanceManager instanceManager;
    private static GlobalEventHandler eventHandler;

    
    public static void main(String[] args) {

        logger.info("Loading the 'hydrogen.conf' configuration file.See console for errors if happened.");
        try {
            PerformanceLogger.writeLog();
        } catch (Exception e) {
            logger.error("Failed to write the PERFORMANCE REPORT... Error:", e);
        }
        String ipString = ServerConfiguration.loadConfig().getString("settings.server-ip");
        int port = ServerConfiguration.loadConfig().getInt("settings.server-port");

        logger.info("Trying to start up the server with IP of '" + ipString + "' and with port of '" + port + "'.");

        if (ServerConfiguration.loadConfig().getBoolean("settings.allow-untrusted-connections")) {
            logger.warn(
                    "We have seen you enabled 'allow-untrusted-connections' or to allow offline / cracked clients to join.");
            logger.warn(
                    "THIS IS NOT RECOMMENDED due to bad actors can pick ANY username they want and even with administrator accounts and cause chaos.");
            logger.warn(
                    "We recommend you to turn of 'allow-untrusted-connections' or get an authentication plugin like AuthMe.");
            server = MinecraftServer.init();
        } else if (!ServerConfiguration.loadConfig().getBoolean("settings.allow-untrusted-connections")) {
            logger.info("Setting authentication to Mojang's secure servers (premium accounts only)...");
            server = MinecraftServer.init(new Auth.Online());
        }
        eventHandler = MinecraftServer.getGlobalEventHandler();
        instanceManager = MinecraftServer.getInstanceManager();
        logger.info("Preparing to load / creating chunks... Wait");
        if (ServerConfiguration.loadConfig().getString("settings.world-type").equalsIgnoreCase("flat")) {
            logger.info("Using predefined FlatChunkGenerator for FLAT type worlds...");
            InstanceContainer container = instanceManager.createInstanceContainer();
            FlatChunkGenerator generator = new FlatChunkGenerator();
            container.setGenerator(generator::generate);
            container.setChunkSupplier(LightingChunk::new);
            
            PlayerLoginEvent loginEvent = new PlayerLoginEvent();
            loginEvent.onHandleEvent(eventHandler, container);
        }

        PlayerBlockBreak breakEvent = new PlayerBlockBreak();
        breakEvent.onHandleEvent(eventHandler);

        PlayerInventoryPickupEvent inventoryPickupEvent = new PlayerInventoryPickupEvent();
        inventoryPickupEvent.onHandleEvent(eventHandler);
        
        logger.info("Preparing the commands...");
        MinecraftServer.getCommandManager().register(new GameModeCommand());
        logger.info("The preparation of commands has been finished.");
        
        server.start(ipString, port);
        logger.info("Server setup has been finished! Now enjoy the game!");
    }
}