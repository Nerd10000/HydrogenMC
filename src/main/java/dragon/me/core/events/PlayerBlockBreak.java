package dragon.me.core.events;


import java.time.Duration;

import net.minestom.server.coordinate.BlockVec;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.dialog.DialogBody.Item;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class PlayerBlockBreak implements BaseEventInterface {
    
    public void onHandleEvent(GlobalEventHandler eventHandler) {

        eventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            
            BlockVec blockPos = event.getBlockPosition();
            
            Material material = event.getBlock().registry().material();
            ItemStack stack = ItemStack.of(event.getBlock().registry().material());
            /* 
            switch (material) {
                case Material.GRASS_BLOCK -> material = Material.DIRT;
                    
                
                default -> material = material;
            }
            */

            //Creating itemEntity
            ItemEntity entity = new ItemEntity(stack);
            entity.setInstance(event.getInstance(),event.getBlockPosition().add(0.5));
            entity.setPickupDelay(Duration.ofMillis(500));

        });

    }


}
