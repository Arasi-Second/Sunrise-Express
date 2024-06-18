package io.arasitensei.sunriseexpress;

import com.tterrag.registrate.util.entry.BlockEntry;
import io.arasitensei.sunriseexpress.content.decoraction.panel.doublePanel.SidedDoublePanelBlock;
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
                    .transform(BuilderTransformersSE.trainDoor(
                            "jr_east_yamanote_line_e235_0_double_sliding_door"))
                    .properties(p -> p.color(MaterialColor.COLOR_LIGHT_GREEN)
                            .noOcclusion())
                    .register();

    public static final BlockEntry<SidedDoublePanelBlock> JR_EAST_YAMANOTE_LINE_E235_0_SIDED_WINDOW_PANEL =
            REGISTRATE.block("jr_east_yamanote_line_e235_0_window_panel", SidedDoublePanelBlock::new)
                    .transform(BuilderTransformersSE.doubleWindowPanel(
                            "jr_east_yamanote_line_e235_0_window_panel"))
                    .properties(p -> p.color(MaterialColor.COLOR_LIGHT_GRAY)
                            .noOcclusion())
                    .register();

    public static void register() {
    }

}
