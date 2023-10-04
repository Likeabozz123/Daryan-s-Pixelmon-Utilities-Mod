package xyz.gamars.daryanspixelmonutilities.listeners;

import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.util.helpers.CommandHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SuccessfulCaptureListener {

    @SubscribeEvent
    public void onSuccessfulCapture(CaptureEvent.SuccessfulCapture event) {
        PlayerEntity player = event.getPlayer();
        Pokemon pokemon = event.getPokemon().getPokemon();

        player.sendMessage(new StringTextComponent("Last Caught Pokemon: ").append(CommandHelper.getHoverTextPokemon(pokemon)), player.getUUID());
    }


}
