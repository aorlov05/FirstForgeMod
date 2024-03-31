package io.github.aorlov05.firstforgemod.item;

import io.github.aorlov05.firstforgemod.FirstForgeMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    // Maintains a list of items
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FirstForgeMod.MOD_ID);

    public static final RegistryObject<Item> ADAMITE = ITEMS.register("adamite",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> RAW_ADAMITE = ITEMS.register("raw_adamite",
            () -> new Item(new Item.Properties()));

    // Registers the items during RegisterEvent
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

}
