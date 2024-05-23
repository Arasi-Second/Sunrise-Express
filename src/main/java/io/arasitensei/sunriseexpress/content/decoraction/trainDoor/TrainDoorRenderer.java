package io.arasitensei.sunriseexpress.content.decoraction.trainDoor;

import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;

import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AngleHelper;
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

        LerpedFloat animation = ((ISlidingDoorBlockEntityMixin) blockEntity).getAnimation();

        float value = animation.getValue(partialTicks);
        float valueClamp = Mth.clamp(value * 10, 0, 1);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.cutoutMipped());

        Triple<PartialModel> partials = AllPartialModelsSE.TRAIN_SLIDING_DOORS.get(blockState.getBlock()
                .getRegistryName());

        boolean flip = blockState.getValue(DoorBlock.HINGE) == DoorHingeSide.RIGHT;
        for (boolean left : Iterate.trueAndFalse) {
            float f = flip ? -1 : 1;

            int slidingIndex = flip ^ left ? 0 : 1;

            SuperByteBuffer partialSliding = CachedBufferer.partial(partials.get(slidingIndex), blockState);

            double rightPosX = facing == Direction.NORTH ? 0.5f : facing == Direction.SOUTH ? -0.5f : 0;
            double rightPosZ = facing == Direction.EAST ? 0.5f : facing == Direction.WEST ? -0.5f : 0;

            partialSliding.translate(0, -0.001953125f, 0)
                    .translate(Vec3.atLowerCornerOf(facing.getNormal())
                            .scale(valueClamp * 0.0625f));

            if (slidingIndex == 0) {
                partialSliding.translate(Vec3.atLowerCornerOf(movementDirection.getNormal())
                        .scale(value * value * f * 0.748046875f));
            }

            if (slidingIndex == 1) {
                partialSliding.translate(rightPosX, 0, rightPosZ)
                        .translate(Vec3.atLowerCornerOf(movementDirection.getOpposite().getNormal())
                                .scale(value * value * f * 0.748046875f));
            }

            partialSliding.rotateCentered(Direction.UP,
                    Mth.DEG_TO_RAD * AngleHelper.horizontalAngle(facing.getClockWise()));

            partialSliding.renderInto(ms, vertexConsumer);

            if (left) {
                int frameIndex = 2;

                SuperByteBuffer partialFrame = CachedBufferer.partial(partials.get(frameIndex), blockState);

                partialFrame.translate(0, 0, 0)
                        .rotateCentered(Direction.UP,
                                Mth.DEG_TO_RAD * AngleHelper.horizontalAngle(facing.getClockWise()));

                partialFrame.renderInto(ms, vertexConsumer);
            }
        }
    }
}
