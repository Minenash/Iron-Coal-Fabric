package com.minenash.ironcoals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.TorchBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class IronCoalTorchBlock extends TorchBlock {

    public IronCoalTorchBlock() {
        super(
            FabricBlockSettings.of(Material.SUPPORTED).noCollision().breakInstantly().luminance(_state -> 15).sounds(BlockSoundGroup.WOOD),
            ParticleTypes.FLAME
        );
    }
}
