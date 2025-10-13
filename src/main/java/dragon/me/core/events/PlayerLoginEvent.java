package dragon.me.core.events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.Instance;

public class PlayerLoginEvent {

    private static Logger logger = LoggerFactory.getLogger(PlayerLoginEvent.class);
    public void onHandleEvent(GlobalEventHandler eventHandler, Instance world) {
        eventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            if (world.equals(null)) {
                logger.error("The instance is null.");
            }
            event.setSpawningInstance(world); // MUST set the instance here
            player.setRespawnPoint(new Pos(0, 4, 0));
            
        
        });
    }
}