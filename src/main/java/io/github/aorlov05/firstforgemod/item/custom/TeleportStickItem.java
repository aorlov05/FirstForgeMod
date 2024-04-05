package io.github.aorlov05.firstforgemod.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TeleportStickItem extends Item {

    public TeleportStickItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        // TODO Arbitrary hardcoded reach of 15 blocks, maybe could be customizable by player?
        BlockHitResult rayTrace = rayTraceFromPlayerEyes(15, pLevel, pPlayer, ClipContext.Fluid.NONE);
        // Offsets the teleported position based on the direction of the ray trace
        BlockPos blockPos = rayTrace.getBlockPos().relative(rayTrace.getDirection());
        pPlayer.setPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        // Adds an in-game cooldown of 60 ticks to the item
        pPlayer.getCooldowns().addCooldown(this, 60);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel,
                                List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.firstforgemod.teleport_stick.tooltip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.firstforgemod.teleport_stick.tooltip"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    /**
     * Copies Item.getPlayerPOVHitResult(Level, Player, ClipContext.Fluid) but modifies the
     * maximum reach that the ray trace shoots from relative to the player's eye position
     * @param reach The max number of blocks that the ray trace searches
     * @param pLevel The world
     * @param pPlayer The player
     * @param pFluidMode If the ray trace stops at fluids or not
     * @return The block that the ray trace reaches
     */
    private BlockHitResult rayTraceFromPlayerEyes(int reach, Level pLevel, Player pPlayer, ClipContext.Fluid pFluidMode) {
        float f = pPlayer.getXRot();
        float f1 = pPlayer.getYRot();
        Vec3 vec3 = pPlayer.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = Mth.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -Mth.cos(-f * ((float) Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vec31 = vec3.add((double) f6 * reach, (double) f5 * reach, (double) f7 * reach);
        return pLevel.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, pFluidMode, pPlayer));
    }

}
