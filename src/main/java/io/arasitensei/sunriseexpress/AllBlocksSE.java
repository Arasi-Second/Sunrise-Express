package io.arasitensei.sunriseexpress;

import com.tterrag.registrate.util.entry.BlockEntry;
import io.arasitensei.sunriseexpress.content.decoraction.sidePanel.DoubleHingedSidePanelBlock;
import io.arasitensei.sunriseexpress.content.decoraction.trainDoor.TrainDoorBlock;
import io.arasitensei.sunriseexpress.foundation.data.BuilderTransformersSE;
import net.minecraft.world.level.material.MaterialColor;

import static io.arasitensei.sunriseexpress.SunriseExpress.REGISTRATE;

public class AllBlocksSE {

    static {
        REGISTRATE.creativeModeTab(() -> AllCreativeModeTabsSE.TAB_SUNRISE_EXPRESS_BASE);
    }

    public static final BlockEntry<TrainDoorBlock> E235_TRAIN_DOUBLE_SLIDING_DOOR =
            REGISTRATE.block("e235_train_double_sliding_door", p -> new TrainDoorBlock(p))
                    .transform(BuilderTransformersSE.slidingDoor("e235_train_double_sliding_door"))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GREEN)
                            .noOcclusion())
                    .register();

    public static final BlockEntry<DoubleHingedSidePanelBlock> TEST =
            REGISTRATE.block("test", p -> new DoubleHingedSidePanelBlock(p))
                    .transform(BuilderTransformersSE.doubleHingedSidePanelBlock("test"))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GRAY)
                            .noOcclusion())
                    .register();

    public static void register() {
    }

}
