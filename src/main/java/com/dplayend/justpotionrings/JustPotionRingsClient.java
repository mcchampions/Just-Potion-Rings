package com.dplayend.justpotionrings;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import net.minecraft.util.Identifier;

public class JustPotionRingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        TintSourceTypes.ID_MAPPER.put(Identifier.of(JustPotionRings.MOD_ID, "ring_color"),RingColorTintSource.CODEC);
    }
}
