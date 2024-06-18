package io.arasitensei.sunriseexpress.content.decoraction.trainDoor;

import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;

import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AngleHelper;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.foundation.utility.animation.LerpedFloat;
import io.arasitensei.sunriseexpress.AllPartialModelsSE;
import io.arasitensei.sunriseexpress.foundation.utility.Triple;
import io.arasitensei.sunriseexpress.mixin.create.ISlidingDoorBlockEntityMixin;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.Vec3;

public class TrainDoorRenderer extends SafeBlockEntityRenderer<TrainDoorBlockEntity> {

    public TrainDoorRenderer(Context context) {
    }

    @Override
    protected void renderSafe(TrainDoorBlockEntity blockEntity, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        BlockState blockState = blockEntity.getBlockState();
        if (!blockEntity.shouldRenderSpecial(blockState))
            return;

        Direction facing = blockState.getValue(DoorBlock.FACING);
        Direction movementDirection = facing.getClockWise();

        if (blockState.getValue(DoorBlock.HINGE) == DoorHingeSide.LEFT)
            movementDirection = movementDirection.getOpposite();

        LerpedFloat animation = blockEntity.animation;

        float value = animation.getValue(partialTicks);
        float valueClamp = Mth.clamp(value * 10, 0, 1);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.cutoutMipped());

        Couple<PartialModel> partials = AllPartialModelsSE.TRAIN_SLIDING_DOORS.get(blockState.getBlock()
                .getRegistryName());

        boolean flip = blockState.getValue(DoorBlock.HINGE) == DoorHingeSide.RIGHT;
        for (boolean left : Iterate.trueAndFalse) {
            float f = flip ? -1 : 1;

            SuperByteBuffer partialSliding = CachedBufferer.partial(partials.get(flip ^ left), blockState);

            double rightPosX = facing == Direction.NORTH ? 1 / 2f : facing == Direction.SOUTH ? -1 / 2f : 0;
            double rightPosZ = facing == Direction.EAST ? 1 / 2f : facing == Direction.WEST ? -1 / 2f : 0;

            partialSliding.translate(0, 0, 0);

            if (flip ^ left) {
                partialSliding.translate(Vec3.atLowerCornerOf(movementDirection.getNormal())
                        .scale(value * value * f * 3 / 4f));
            } else {
                partialSliding.translate(rightPosX, 0, rightPosZ)
                        .translate(Vec3.atLowerCornerOf(movementDirection.getOpposite().getNormal())
                                .scale(value * value * f * 3 / 4f));
            }

            partialSliding.rotateCentered(Direction.UP,
                    Mth.DEG_TO_RAD * AngleHelper.horizontalAngle(facing.getClockWise()));

            partialSliding.renderInto(ms, vertexConsumer);
        }
    }
}
