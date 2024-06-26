package io.arasitensei.sunriseexpress;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.foundation.utility.Couple;
import io.arasitensei.sunriseexpress.foundation.utility.Triple;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class AllPartialModelsSE {

    public static final Map<ResourceLocation, Couple<PartialModel>> TRAIN_SLIDING_DOORS = new HashMap<>();

    static {
        putSlidingDoor("jr_east_yamanote_line_e235_0_double_sliding_door");
    }

    private static void putSlidingDoor(String path) {
        TRAIN_SLIDING_DOORS.put(SunriseExpress.asResource(path),
                Couple.create(block(path + "/sliding_left"), block(path + "/sliding_right")));
    }

    private static PartialModel block(String path) {
        return new PartialModel(SunriseExpress.asResource("block/" + path));
    }

    public static void init() {
    }
}
