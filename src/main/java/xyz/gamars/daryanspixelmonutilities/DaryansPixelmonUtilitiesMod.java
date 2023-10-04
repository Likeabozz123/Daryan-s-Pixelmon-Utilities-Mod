package xyz.gamars.daryanspixelmonutilities;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.gamars.daryanspixelmonutilities.command.CheckspawnsCommand;
import xyz.gamars.daryanspixelmonutilities.listeners.SuccessfulCaptureListener;

@Mod(DaryansPixelmonUtilitiesMod.MOD_ID)
@Mod.EventBusSubscriber(modid = DaryansPixelmonUtilitiesMod.MOD_ID)
public class DaryansPixelmonUtilitiesMod {

    public static final String MOD_ID = "daryanspixelmonutilitiesmod";

    public DaryansPixelmonUtilitiesMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        //Register command logic here
        // Commands don't have to be registered here
        // However, not registering them here can lead to some hybrids/server software not recognising the commands
        new CheckspawnsCommand(event.getDispatcher());

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        Pixelmon.EVENT_BUS.register(new SuccessfulCaptureListener());
    }


}
