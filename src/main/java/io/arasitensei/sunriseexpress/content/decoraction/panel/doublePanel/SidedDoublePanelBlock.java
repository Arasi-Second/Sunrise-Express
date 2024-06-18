package io.arasitensei.sunriseexpress.content.decoraction.panel.doublePanel;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class SidedDoublePanelBlock extends DoublePanelBlock {

    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;

    public SidedDoublePanelBlock(Properties properties) {
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
                    .setValue(HINGE, this.getHinge(context)).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    // NOTE 2024/05/22 : Make myself understand how does this method getHinge run
    public DoorHingeSide getHinge(BlockPlaceContext context) {

        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection();

        int stepX = direction.getStepX();
        int stepZ = direction.getStepZ();

        Vec3 vec3 = context.getClickLocation();
        double x = vec3.x - (double)blockpos.getX();
        double z = vec3.z - (double)blockpos.getZ();

        return (stepX >= 0 || !(z < 0.5D)) && (stepX <= 0 || !(z > 0.5D)) &&
                (stepZ >= 0 || !(x > 0.5D)) && (stepZ <= 0 || !(x < 0.5D)) ?
                DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HINGE);
        super.createBlockStateDefinition(builder);
    }
}
