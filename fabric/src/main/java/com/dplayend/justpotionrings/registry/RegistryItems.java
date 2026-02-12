package com.dplayend.justpotionrings.registry;

import com.dplayend.justpotionrings.JustPotionRings;
import com.dplayend.justpotionrings.common.item.Ring;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class RegistryItems {
    public static Item registerItems(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JustPotionRings.MOD_ID, name));
        Item item = factory.apply(settings.registryKey(key));

        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registries.ITEM, key, item);
    }

    public static Item POTION_RING = registerItems("potion_ring", Item::new,(new Item.Settings().maxCount(1)));
    public static Item RING = registerItems("ring", Ring::new,(new Item.Settings().maxCount(1)));

    public static void load() {}
}
