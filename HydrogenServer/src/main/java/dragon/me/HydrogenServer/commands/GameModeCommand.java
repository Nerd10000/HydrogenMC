package dragon.me.HydrogenServer.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.Argument;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class GameModeCommand extends Command {

    public GameModeCommand() {
        super("gamemode","gm");
        

        var argument = ArgumentType.String("gamemode");
        addSyntax((sender,context) -> {
            if (sender instanceof Player p) {
                final String gamemode = context.get("gamemode");
                switch (gamemode) {
                    case "survival":
                        p.sendMessage("You have switched your game mode to " + gamemode + "!");
                        p.setGameMode(GameMode.SURVIVAL);
                        
                        break;
                    case "creative":
                        p.sendMessage("You have switched your game mode to " + gamemode + "!");
                        p.setGameMode(GameMode.CREATIVE);
                        break;
                    case "adventure":
                        p.sendMessage("You have switched your game mode to " + gamemode + "!");
                        p.setGameMode(GameMode.ADVENTURE);
                        break;
                    default:
                        p.sendMessage("Unknown game mode: " + gamemode);
                        break;
                }
            }
        }, argument);
    }

    
}
