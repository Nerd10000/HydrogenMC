package dragon.me.HydrogenServer.core.world.filesystem;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;

public class ChunkDataWriter {

    public static final void write(int x, int z, Instance world) {
        List<Block> blockSet = new ArrayList<>();
        Chunk chunk = world.getChunkAt(x, z);

        int chunkX = chunk.getChunkX() * 16;
        int chunkZ = chunk.getChunkZ() * 16;

        for (int xPos = 0; xPos < 15; xPos++) {
            for (int yPos = world.getCachedDimensionType().minY(); yPos < world.getCachedDimensionType()
                    .maxY(); yPos++) {
                for (int zPos = 0; zPos < 15; zPos++) {
                    Block block = chunk.getBlock(xPos, yPos, zPos);
                    blockSet.add(block);

                }
            }
        }
        Set<Integer> ids = new LinkedHashSet<>();

        for (Block block : blockSet) {
            ids.add(block.id());
        }
        List<Integer> palette = new ArrayList<>(ids);

        int[] indices = new int[blockSet.size()];
        for (int i = 0; i < blockSet.size(); i++){
            int id = blockSet.get(i).id();
            indices[i] = palette.indexOf(id);
        }

    }


    

}
