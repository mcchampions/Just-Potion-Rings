package com.dplayend.justpotionrings.common.item;

import com.dplayend.justpotionrings.JustPotionRings;
import com.dplayend.justpotionrings.handler.HandlerRing;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;

import java.awt.*;

public class Ring extends Item {

    public Ring(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return getMobEffect(stack) != null;
    }

    public RegistryEntry<StatusEffect> getMobEffect(ItemStack itemStack) {
        return HandlerRing.getEffect(itemStack);
    }

    public int getColorEffect(ItemStack itemStack) {
        int result = -1;
        if (getMobEffect(itemStack) != null) {
            Color color = new Color(getMobEffect(itemStack).value().getColor());
            result = ColorHelper.getArgb(color.getRed(), color.getGreen(), color.getBlue());
        }
        return result;
    }

    @Override
    public Text getName(ItemStack stack) {
        return getMobEffect(stack) != null ? Text.translatable("item." + JustPotionRings.MOD_ID + ".ring").append(Text.literal(" ")).append(getMobEffect(stack).value().getName().getString()).formatted(getMobEffect(stack).value().getCategory().getFormatting()) : Text.translatable("item." + JustPotionRings.MOD_ID + ".potion_ring");
    }
}
