package io.arasitensei.sunriseexpress.foundation.data;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.contraptions.behaviour.DoorMovingInteraction;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorMovementBehaviour;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import io.arasitensei.sunriseexpress.content.decoraction.sidePanel.DoubleHingedSidePanelBlock;
import io.arasitensei.sunriseexpress.content.decoraction.trainDoor.TrainDoorBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

import static com.simibubi.create.AllInteractionBehaviours.interactionBehaviour;
import static com.simibubi.create.AllMovementBehaviours.movementBehaviour;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

public class BuilderTransformersSE {

    public static <B extends DoubleHingedSidePanelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>>
    doubleHingedSidePanelBlock(String type) {
        return b -> b.initialProperties(Material.METAL)
                .properties(p -> p.requiresCorrectToolForDrops()
                        .strength(3.0F, 6.0F)
                        .sound(SoundType.NETHERITE_BLOCK))
                .blockstate((c, p) -> {
                    ModelFile top = AssetLookup.partialBaseModel(c, p, "top");
                    ModelFile bottom = AssetLookup.partialBaseModel(c, p, "bottom");
                    p.horizontalBlock(c.get(), state -> {
                        boolean topOrBottom = state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) ==
                                DoubleBlockHalf.UPPER;
                        boolean leftOrRight = state.getValue(BlockStateProperties.DOOR_HINGE) == DoorHingeSide.LEFT;

                        int facing = (int) ((state.getValue(DoorBlock.FACING)).toYRot());
                        int rotationY = leftOrRight ? facing : facing + 180;
                        rotationY %= 360;

                        return ConfiguredModel.builder().modelFile(topOrBottom ? top : bottom)
                                .rotationY(rotationY).buildLast().model;
                    }, 90);
                })
                .addLayer(() -> RenderType::cutoutMipped)
                .transform(pickaxeOnly())
                .loot((lr, block) -> lr.add(block, BlockLoot.createDoorTable(block)))
                .item()
                .model((c, p) -> p.blockSprite(c, p.modLoc("item/" + type )))
                .build();
    }

    public static <B extends TrainDoorBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> slidingDoor(
            String type) {
        return b -> b.initialProperties(Material.NETHER_WOOD)
                .properties(p -> p.requiresCorrectToolForDrops()
                        .strength(3.0F, 6.0F)
                        .sound(SoundType.NETHERITE_BLOCK))
                .blockstate((c, p) -> {
                    ModelFile top = AssetLookup.partialBaseModel(c, p, "top");
                    ModelFile bottom = AssetLookup.partialBaseModel(c, p, "bottom");
                    p.doorBlock(c.get(), bottom, bottom, top, top);
                })
                .addLayer(() -> RenderType::cutoutMipped)
                .transform(pickaxeOnly())
                .onRegister(interactionBehaviour(new DoorMovingInteraction()))
                .onRegister(movementBehaviour(new SlidingDoorMovementBehaviour()))
                .tag(BlockTags.DOORS)
                .tag(BlockTags.WOODEN_DOORS)
                .tag(AllTags.AllBlockTags.NON_DOUBLE_DOOR.tag)
                .loot((lr, block) -> lr.add(block, BlockLoot.createDoorTable(block)))
                .item()
                .tag(ItemTags.DOORS)
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .model((c, p) -> p.blockSprite(c, p.modLoc("item/" + type)))
                .build();
    }
}
