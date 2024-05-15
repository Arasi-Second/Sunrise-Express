package io.arasitensei.sunriseexpress;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SunriseExpress.ID)
public class SunriseExpress {

    public static final String ID = "sunriseexpress";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID);

    public SunriseExpress() {
        onCtor();
    }

    public static void onCtor() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        REGISTRATE.registerEventListeners(modEventBus);

        AllItemsSE.register();
        AllBlocksSE.register();
        AllBlockEntityTypesSE.register();

        AllCreativeModeTabsSE.init();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SunriseExpressClient.onCtorClient(modEventBus, forgeEventBus));
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(ID, path);
    }
}
