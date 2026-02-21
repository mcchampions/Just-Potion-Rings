package com.dplayend.justpotionrings.handler;

import com.dplayend.justpotionrings.registry.RegistryItems;
import io.wispforest.accessories.api.core.Accessory;
import io.wispforest.accessories.api.core.AccessoryRegistry;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.concurrent.atomic.AtomicInteger;

public class HandlerAccessory {

    public static void init() {
            try {
                AccessoryRegistry.register(RegistryItems.RING, AccessoryRing.class.newInstance());
            } catch (NoClassDefFoundError | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
    }

    public static class AccessoryRing implements Accessory {
        public RegistryEntry<StatusEffect> getMobEffect(ItemStack itemStack) {
            return HandlerRing.getEffect(itemStack);
        }

        @Override
        public void tick(ItemStack itemStack, SlotReference reference) {
            if (getMobEffect(itemStack) != null) {
                if (reference.entity() instanceof LivingEntity player) {
                    if (reference.slotContainer() != null) {
                        AtomicInteger amount = new AtomicInteger(-1);
                        reference.slotContainer().getAccessories().forEach(pair -> {
                            if (getMobEffect(itemStack).equals(getMobEffect(pair))) amount.getAndIncrement();
                        });
                        StatusEffectInstance instance = new StatusEffectInstance(getMobEffect(itemStack), -1, amount.get(), false, false, false);
                        player.addStatusEffect(instance);
                    }
                }
            }
        }

        @Override
        public void onUnequip(ItemStack stack, SlotReference reference) {
            if (getMobEffect(stack) != null) {
                if (reference.entity().hasStatusEffect(getMobEffect(stack))) {
                    reference.entity().removeStatusEffect(getMobEffect(stack));
                }
            }
        }

        @Override
        public boolean canEquipFromUse(ItemStack stack,SlotReference reference) {
            return true;
        }
    }
}
