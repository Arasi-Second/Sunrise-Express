package io.arasitensei.sunriseexpress;

import net.minecraftforge.eventbus.api.IEventBus;

public class SunriseExpressClient {

    public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        AllPartialModelsSE.init();
    }
}
