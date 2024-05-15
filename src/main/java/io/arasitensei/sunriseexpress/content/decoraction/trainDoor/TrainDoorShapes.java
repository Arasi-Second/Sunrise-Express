package io.arasitensei.sunriseexpress.content.decoraction.trainDoor;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class TrainDoorShapes {

    protected static final VoxelShape E_AABB = Stream.of(
            Block.box(14, 0, -4, 15, 16, 20),
            Block.box(13, 0, -16, 16, 16, -4),
            Block.box(13, 0, 20, 16, 16, 32)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    protected static final VoxelShape W_AABB = Stream.of(
            Block.box(1, 0, -4, 2, 16, 20),
            Block.box(0, 0, 20, 3, 16, 32),
            Block.box(0, 0, -16, 3, 16, -4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    protected static final VoxelShape N_AABB = Stream.of(
            Block.box(-4, 0, 1, 20, 16, 2),
            Block.box(-16, 0, 0, -4, 16, 3),
            Block.box(20, 0, 0, 32, 16, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    protected static final VoxelShape S_AABB = Stream.of(
            Block.box(-4, 0, 14, 20, 16, 15),
            Block.box(20, 0, 13, 32, 16, 16),
            Block.box(-16, 0, 13, -4, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    protected static final VoxelShape E_AABB_OPEN = Shapes.join(
            Block.box(13, 0, -16, 16, 16, -4),
            Block.box(13, 0, 20, 16, 16, 32),
            BooleanOp.OR);
    protected static final VoxelShape W_AABB_OPEN = Shapes.join(
            Block.box(0, 0, 20, 3, 16, 32),
            Block.box(0, 0, -16, 3, 16, -4),
            BooleanOp.OR);
    protected static final VoxelShape N_AABB_OPEN = Shapes.join(
            Block.box(-16, 0, 0, -4, 16, 3),
            Block.box(20, 0, 0, 32, 16, 3),
            BooleanOp.OR);
    protected static final VoxelShape S_AABB_OPEN = Shapes.join(
            Block.box(20, 0, 13, 32, 16, 16),
            Block.box(-16, 0, 13, -4, 16, 16),
            BooleanOp.OR);

    protected static VoxelShape get(Direction facing, boolean open) {
        return switch (facing) {
            case WEST -> (open ? W_AABB_OPEN : W_AABB);
            case NORTH -> (open ? N_AABB_OPEN : N_AABB);
            case SOUTH -> (open ? S_AABB_OPEN : S_AABB);
            default -> (open ? E_AABB_OPEN : E_AABB);
        };
    }
}
