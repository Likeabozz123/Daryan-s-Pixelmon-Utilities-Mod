package xyz.gamars.daryanspixelmonutilities.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pixelmonmod.pixelmon.api.command.PixelmonCommandUtils;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.command.PixelCommand;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;

public class CheckLastCaughtIVCommand extends PixelCommand {

    public CheckLastCaughtIVCommand(CommandDispatcher<CommandSource> dispatcher) {
        super(dispatcher, "checklastcaughtivs", "/checklastcaughtivs", 0);
    }

    @Override
    public void execute(CommandSource sender, String[] args) throws CommandException, CommandSyntaxException {
        AbstractSpawner spawner = null;
        if (sender.getEntity() instanceof net.minecraft.entity.player.ServerPlayerEntity) {
            spawner = PixelmonSpawning.coordinator.getSpawner(sender.getTextName());
        } else {
            PixelmonCommandUtils.endCommand("spawning.error.mustbeplayer", new Object[0]);
        }


    }
}
