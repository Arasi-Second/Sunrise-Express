package io.arasitensei.sunriseexpress.content.decoraction.panel.doublePanel;

import io.arasitensei.sunriseexpress.content.decoraction.panel.singlePanel.SinglePanelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class DoublePanelBlock extends SinglePanelBlock {

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public DoublePanelBlock(Properties properties) {
        super(properties);
        this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockPos = context.getClickedPos();
        Level level = context.getLevel();

        if (blockPos.getY() < level.getMaxBuildHeight() - 1 &&
                level.getBlockState(blockPos.above()).canBeReplaced(context)) {
            return this.defaultBlockState()
                    .setValue(FACING, context.getHorizontalDirection()).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState otherState, LevelAccessor level,
                                  BlockPos pos, BlockPos otherPos) {
        DoubleBlockHalf doubleBlockHalf = state.getValue(HALF);
        BlockState airBlockState = Blocks.AIR.defaultBlockState();

        boolean isAxisY = facing.getAxis() == Direction.Axis.Y;
        boolean isLowerHalf = doubleBlockHalf == DoubleBlockHalf.LOWER;

        if (isAxisY && isLowerHalf == (facing == Direction.UP)) {
            return otherState.is(this) && otherState.getValue(HALF) != doubleBlockHalf ?
                    state.setValue(FACING, otherState.getValue(FACING)) : airBlockState;
        } else {
            return isLowerHalf && facing == Direction.DOWN && !state.canSurvive(level, pos) ?
                    airBlockState : super.updateShape(state, facing, otherState, level, pos, otherPos);
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && player.isCreative()) {
            DoublePlantBlock.preventCreativeDropFromBottomPart(level, pos, state, player);
        } else {
            super.playerWillDestroy(level, pos, state, player);
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state,
                            @Nullable LivingEntity placer, ItemStack stack) {
        level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF);
        super.createBlockStateDefinition(builder);
    }
}
