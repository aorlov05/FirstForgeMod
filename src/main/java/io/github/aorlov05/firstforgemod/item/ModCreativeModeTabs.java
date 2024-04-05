package io.github.aorlov05.firstforgemod.item;

import io.github.aorlov05.firstforgemod.FirstForgeMod;
import io.github.aorlov05.firstforgemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstForgeMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("mod_items_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativemodetab.mod_items_tab"))
                    .icon(() -> ModItems.ADAMITE.get().getDefaultInstance())
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ADAMITE.get());
                        output.accept(ModItems.RAW_ADAMITE.get());
                        output.accept(ModItems.METAL_DETECTOR.get());
                        output.accept(ModItems.TELEPORT_STICK.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("mod_blocks_tab", () -> CreativeModeTab.builder()
                    .withTabsBefore(MOD_ITEMS_TAB.getKey())
                    .title(Component.translatable("creativemodetab.mod_blocks_tab"))
                    .icon(() -> new ItemStack(ModBlocks.ADAMITE_BLOCK.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ADAMITE_BLOCK.get());
                        output.accept(ModBlocks.RAW_ADAMITE_BLOCK.get());
                        output.accept(ModBlocks.ADAMITE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ADAMITE_ORE.get());
                        output.accept(ModBlocks.END_STONE_ADAMITE_ORE.get());
                        output.accept(ModBlocks.NETHER_ADAMITE_ORE.get());
                    })
                    .build());

    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
    }

}
