package io.arasitensei.sunriseexpress.content.decoraction.trainDoor;

import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlockEntity;

import io.arasitensei.sunriseexpress.AllBlockEntityTypesSE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TrainDoorBlock extends SlidingDoorBlock {

    public TrainDoorBlock(Properties properties) {
        super(properties, false);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        Boolean open = state.getValue(OPEN);
        return TrainDoorShapes.get(facing, open);
    }

    @Override
    public BlockEntityType<? extends SlidingDoorBlockEntity> getBlockEntityType() {
        return AllBlockEntityTypesSE.TRAIN_DOOR.get();
    }
}
