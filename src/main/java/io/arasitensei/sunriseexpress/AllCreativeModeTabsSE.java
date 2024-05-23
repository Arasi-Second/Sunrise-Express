package io.arasitensei.sunriseexpress;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class AllCreativeModeTabsSE {

    public static final CreativeModeTab TAB_SUNRISE_EXPRESS_BASE
            = new CreativeModeTab("tab_sunrise_express_base") {
        @Override
        public ItemStack makeIcon() {
            return AllItemsSE.BLUE_TRAIN_TICKET.asStack();
        }
    };

    public static final CreativeModeTab TAB_SUNRISE_EXPRESS_BUILDING_BLOCKS
            = new CreativeModeTab("tab_sunrise_express_building_blocks") {
        @Override
        public ItemStack makeIcon() {
            return AllBlocksSE.JR_EAST_YAMANOTE_LINE_E235_0_DOUBLE_SLIDING_DOOR.asStack();
        }
    };

    public static void init() {

    }
}
