package io.arasitensei.sunriseexpress.mixin.create;

import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlockEntity;
import com.simibubi.create.foundation.utility.animation.LerpedFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = SlidingDoorBlockEntity.class, remap = false)
public interface ISlidingDoorBlockEntityMixin {

    @Accessor
    LerpedFloat getAnimation();
}
