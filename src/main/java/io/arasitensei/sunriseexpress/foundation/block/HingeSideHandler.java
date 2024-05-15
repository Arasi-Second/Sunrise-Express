package io.arasitensei.sunriseexpress.foundation.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.Vec3;

public class HingeSideHandler {

    public static DoorHingeSide getHinge(BlockPlaceContext context) {
        BlockPos blockPos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection();

        int stepX = direction.getStepX();
        int stepZ = direction.getStepZ();

        Vec3 vec3 = context.getClickLocation();

        double x = vec3.x - (double) blockPos.getX();
        double z = vec3.z - (double) blockPos.getZ();

        return (stepX >= 0 || !(x < 0.5D)) && (stepX <= 0 || !(x > 0.5D)) &&
                (stepZ >= 0 || !(z < 0.5D)) && (stepZ <= 0 || !(z > 0.5D)) ?
                DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
    }
}
