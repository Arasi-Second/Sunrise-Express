package io.arasitensei.sunriseexpress.content.decoraction.panel.singlePanel;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PanelShapes {
    protected static final VoxelShape E_AABB = Block.box(13, 0, 0, 16, 16, 16);
    protected static final VoxelShape W_AABB = Block.box(0, 0, 0, 3, 16, 16);
    protected static final VoxelShape N_AABB = Block.box(0, 0, 0, 16, 16, 3);
    protected static final VoxelShape S_AABB = Block.box(0, 0, 13, 16, 16, 16);

    protected static VoxelShape get(Direction facing) {
        return switch (facing) {
            case EAST -> E_AABB;
            case WEST -> W_AABB;
            case NORTH -> N_AABB;
            case SOUTH -> S_AABB;
            case UP, DOWN -> E_AABB;
        };
    }
}
