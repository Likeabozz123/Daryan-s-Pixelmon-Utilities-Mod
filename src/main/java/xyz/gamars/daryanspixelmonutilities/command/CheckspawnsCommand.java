package xyz.gamars.daryanspixelmonutilities.command;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.pixelmonmod.pixelmon.api.command.PixelmonCommandUtils;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.command.PixelCommand;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckspawnsCommand extends PixelCommand {
    public CheckspawnsCommand(CommandDispatcher<CommandSource> dispatcher) {
        super(dispatcher, "daryancheckspawns", "/daryancheckspawns [specific] [pokemon...]", 0);
    }


    public void execute(CommandSource sender, String[] args) throws CommandException {
        List<String> arguments = new ArrayList<>();
        AbstractSpawner spawner = null;
        for (String arg : args) {
            arguments.add(arg);
            if (spawner == null)
                spawner = PixelmonSpawning.coordinator.getSpawner(arg);
        }
        if (spawner == null)
            if (sender.getEntity() instanceof net.minecraft.entity.player.ServerPlayerEntity) {
                spawner = PixelmonSpawning.coordinator.getSpawner(sender.getTextName());
            } else {
                PixelmonCommandUtils.endCommand("spawning.error.mustbeplayer", new Object[0]);
            }
        AbstractSpawner fSpawner = (AbstractSpawner)PixelmonCommandUtils.require(spawner, "pixelmon.general.error", new Object[0]);
        fSpawner.checkSpawns.checkSpawns(fSpawner, sender, arguments);
    }

    public List<String> getTabCompletions(MinecraftServer server, CommandSource sender, String[] args, BlockPos pos) {
        if (PixelmonSpawning.coordinator != null) {
            List<String> list = Lists.newArrayList();
            PixelmonSpawning.coordinator.spawners.forEach(s -> list.add(s.name));
            return (args.length == 1) ? list : Collections.<String>emptyList();
        }
        return Collections.emptyList();
    }

}
