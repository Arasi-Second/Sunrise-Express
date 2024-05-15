package io.arasitensei.sunriseexpress;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static io.arasitensei.sunriseexpress.SunriseExpress.REGISTRATE;

public class AllItemsSE {

    static {
        REGISTRATE.creativeModeTab(() -> AllCreativeModeTabsSE.TAB_SUNRISE_EXPRESS_BASE);
    }

    public static final ItemEntry<Item> BLUE_TRAIN_TICKET =
            REGISTRATE.item("blue_train_ticket", Item::new)
                    .properties(p -> p.stacksTo(1))
                    .register();

    public static void register() {
    }
}
