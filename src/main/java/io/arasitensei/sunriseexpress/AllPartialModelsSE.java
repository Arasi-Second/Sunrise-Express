package io.arasitensei.sunriseexpress;

import com.jozufozu.flywheel.core.PartialModel;
import io.arasitensei.sunriseexpress.foundation.utility.Triple;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class AllPartialModelsSE {

    public static final Map<ResourceLocation, Triple<PartialModel>> TRAIN_SLIDING_DOORS = new HashMap<>();

    static {
        putSlidingDoor("e235_train_double_sliding_door");
    }

    private static void putSlidingDoor(String path) {
        TRAIN_SLIDING_DOORS.put(SunriseExpress.asResource(path),
                Triple.create(block(path + "/sliding_left"), block(path + "/sliding_right"),
                        block(path + "/frame")));
    }

    private static PartialModel block(String path) {
        return new PartialModel(SunriseExpress.asResource("block/" + path));
    }

    public static void init() {
    }
}
