package io.arasitensei.sunriseexpress.content.decoraction.trainDoor;

import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TrainDoorBlockEntity extends SlidingDoorBlockEntity {

    public TrainDoorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected boolean shouldRenderSpecial(BlockState state) {
        return super.shouldRenderSpecial(state);
    }
}
