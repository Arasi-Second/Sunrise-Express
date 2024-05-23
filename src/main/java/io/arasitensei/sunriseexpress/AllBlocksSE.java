package io.arasitensei.sunriseexpress;

import com.tterrag.registrate.util.entry.BlockEntry;
import io.arasitensei.sunriseexpress.content.decoraction.trainDoor.TrainDoorBlock;
import io.arasitensei.sunriseexpress.foundation.data.BuilderTransformersSE;
import net.minecraft.world.level.material.MaterialColor;

import static io.arasitensei.sunriseexpress.SunriseExpress.REGISTRATE;

public class AllBlocksSE {

    static {
        REGISTRATE.creativeModeTab(() -> AllCreativeModeTabsSE.TAB_SUNRISE_EXPRESS_BASE);
    }

    public static final BlockEntry<TrainDoorBlock> JR_EAST_YAMANOTE_LINE_E235_0_DOUBLE_SLIDING_DOOR =
            REGISTRATE.block("jr_east_yamanote_line_e235_0_double_sliding_door", TrainDoorBlock::new)
                    .transform(BuilderTransformersSE.slidingDoor(
                            "jr_east_yamanote_line_e235_0_double_sliding_door"))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GREEN)
                            .noOcclusion())
                    .register();

    // TODO 2024/05/22 : Add new Train Side Panel Blocks here

    public static void register() {
    }

}
