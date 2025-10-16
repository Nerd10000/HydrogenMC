package dragon.me.HydrogenServer.utils;

import java.util.ArrayList;
import java.util.List;

import net.minestom.server.coordinate.Vec;
import net.minestom.server.instance.Chunk;

public class PathFindingUtils {

    public static final List<Vec> findPath(Vec start, Chunk chunk, Vec end) {
        List<Vec> positionList = new ArrayList<>();

        Vec delta = new Vec(
                end.blockX() - start.blockX(),
                end.blockY() - start.blockY(),
                end.blockZ() - start.blockZ());

        while (delta.x() != 0 || delta.y() != 0 || delta.z() != 0){
            //Calculate the distance
            int lengthSquared = (int) ( Math.pow(delta.x(), 2)  + Math.pow(delta.y(), 2) + Math.pow(delta.z(), 2));
            
            if(lengthSquared != 0){
                
                int sqrt = (int) Math.sqrt(lengthSquared);
                // Implement / block check for avoiding lava d
            
            }


        }
        

        return positionList;

    }

}
