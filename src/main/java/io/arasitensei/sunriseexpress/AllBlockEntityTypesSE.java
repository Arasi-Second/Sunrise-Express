package io.arasitensei.sunriseexpress;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import io.arasitensei.sunriseexpress.content.decoraction.trainDoor.TrainDoorBlockEntity;
import io.arasitensei.sunriseexpress.content.decoraction.trainDoor.TrainDoorRenderer;

import static io.arasitensei.sunriseexpress.SunriseExpress.REGISTRATE;

public class AllBlockEntityTypesSE {

    public static final BlockEntityEntry<TrainDoorBlockEntity> TRAIN_DOOR =
            REGISTRATE.blockEntity("train_door",
                            TrainDoorBlockEntity::new)
                    .renderer(() -> TrainDoorRenderer::new)
                    .validBlocks(AllBlocksSE.E235_TRAIN_DOUBLE_SLIDING_DOOR)
                    .register();

    public static void register() {
    }
}
