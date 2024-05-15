package io.arasitensei.sunriseexpress.content.decoraction.sidePanel;

import io.arasitensei.sunriseexpress.foundation.block.HingeSideHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class DoubleHingedSidePanelBlock extends DoubleSidePanelBlock {

    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;

    public DoubleHingedSidePanelBlock(Properties properties) {
        super(properties);
        this.stateDefinition.any().setValue(HINGE, DoorHingeSide.LEFT);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        if (blockpos.getY() < level.getMaxBuildHeight() - 1 &&
                level.getBlockState(blockpos.above()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection())
                    .setValue(HINGE, HingeSideHandler.getHinge(context)).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HINGE);
        super.createBlockStateDefinition(builder);
    }
}
