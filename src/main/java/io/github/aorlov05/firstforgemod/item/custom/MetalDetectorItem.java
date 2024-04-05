package io.github.aorlov05.firstforgemod.item.custom;

import io.github.aorlov05.firstforgemod.block.ModBlocks;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;
            for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockPos blockBelow = positionClicked.below(i);
                BlockState block = pContext.getLevel().getBlockState(blockBelow);
                if (isModBlock(block)) {
                    sendFoundMessage(player, block, blockBelow);
                    foundBlock = true;
                    break;
                }
            }

            if (!foundBlock) {
                sendNotFoundMessage(player);
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));
        return InteractionResult.SUCCESS;
    }

    private void sendNotFoundMessage(Player player) {
        player.sendSystemMessage(Component.translatable("item.firstforgemod.metal_detector.no_ore"));
    }

    private void sendFoundMessage(Player player, BlockState block, BlockPos blockBelow) {
        player.sendSystemMessage(Component.literal("Found ore: "
                + I18n.get(block.getBlock().getDescriptionId()) + " at ("
                + blockBelow.getX() + ", " + blockBelow.getY() + ", " + blockBelow.getZ() + ")"));
    }

    // TODO Add and search by tags to avoid repetition
    private boolean isModBlock(BlockState blockState) {
        return blockState.is(ModBlocks.ADAMITE_ORE.get()) || blockState.is(ModBlocks.DEEPSLATE_ADAMITE_ORE.get())
                || blockState.is(ModBlocks.END_STONE_ADAMITE_ORE.get()) || blockState.is(ModBlocks.NETHER_ADAMITE_ORE.get());
    }

}
