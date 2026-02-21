package com.dplayend.justpotionrings;

import com.dplayend.justpotionrings.common.item.Ring;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record RingColorTintSource(int defaultColor) implements TintSource {
    public static final MapCodec<RingColorTintSource> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codecs.RGB.fieldOf("default").forGetter(RingColorTintSource::defaultColor)).apply(instance, RingColorTintSource::new));

    public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        int color = -1;
        if (stack.getItem() instanceof Ring ring) {
            color = ring.getColorEffect(stack);
        }
        if (color == -1) {
            return defaultColor;
        }
        return color;
    }

    public MapCodec<RingColorTintSource> getCodec() {
        return CODEC;
    }
}
