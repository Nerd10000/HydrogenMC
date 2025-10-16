package dragon.me.HydrogenServer.core.world.generators;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;

public class FlatChunkGenerator implements Generator {

    @Override
    public void generate(GenerationUnit unit) {
        // TODO Auto-generated method stub
        unit.modifier().fillHeight(0, 1, Block.BEDROCK);
        unit.modifier().fillHeight(1, 3, Block.DIRT);
        unit.modifier().fillHeight(3, 4, Block.GRASS_BLOCK);
        
    }
    


}
