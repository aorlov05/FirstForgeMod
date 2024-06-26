package io.github.aorlov05.firstforgemod.block;

import io.github.aorlov05.firstforgemod.FirstForgeMod;
import io.github.aorlov05.firstforgemod.block.custom.SoundBlock;
import io.github.aorlov05.firstforgemod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    private static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FirstForgeMod.MOD_ID);

    public static final RegistryObject<Block> ADAMITE_BLOCK = registerBlock("adamite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RAW_ADAMITE_BLOCK = registerBlock("raw_adamite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));

    // Remove .requiresCorrectToolForDrops() to make mineable with hand!
    public static final RegistryObject<Block> ADAMITE_ORE = registerBlock("adamite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(),
                    UniformInt.of(2, 5)));
    public static final RegistryObject<Block> DEEPSLATE_ADAMITE_ORE = registerBlock("deepslate_adamite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 6)));
    public static final RegistryObject<Block> END_STONE_ADAMITE_ORE = registerBlock("end_stone_adamite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).requiresCorrectToolForDrops(),
                    UniformInt.of(5, 8)));
    public static final RegistryObject<Block> NETHER_ADAMITE_ORE = registerBlock("nether_adamite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                    .sound(SoundType.NETHER_GOLD_ORE).requiresCorrectToolForDrops(),
                    UniformInt.of(5, 8)));

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.NOTE_BLOCK)));

    // Registers a block and its corresponding block item
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }

    // Registers a basic block item with the given name for any block
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.getItems().register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

}
