package dragon.me.core.events;

import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.PickupItemEvent;

public class PlayerInventoryPickupEvent implements BaseEventInterface{

    @Override
    public void onHandleEvent(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PickupItemEvent.class, event -> {

            if (event.getEntity() instanceof Player p) {
                
                if (p.canPickupItem()) {
                    
                    p.getInventory().addItemStack(event.getItemStack());
                }
            }
        });
    }

    
}
