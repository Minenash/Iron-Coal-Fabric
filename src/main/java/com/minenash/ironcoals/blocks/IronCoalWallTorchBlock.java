package com.minenash.ironcoals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class IronCoalWallTorchBlock extends WallTorchBlock {

    public IronCoalWallTorchBlock() {
        super(
            FabricBlockSettings.of(Material.SUPPORTED).noCollision().breakInstantly().luminance(_state -> 15).sounds(BlockSoundGroup.WOOD),
            ParticleTypes.FLAME
        );
    }

}
