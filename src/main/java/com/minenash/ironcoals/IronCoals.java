package com.minenash.ironcoals;

import com.minenash.ironcoals.blocks.IronCoalTorchBlock;
import com.minenash.ironcoals.blocks.IronCoalWallTorchBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.*;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class IronCoals implements ModInitializer {

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier("ironcoals","general"), () -> new ItemStack(IronCoals.IRON_COAL));

	private static final Item.Settings ITEM_SETTINGS = new Item.Settings().group(ITEM_GROUP);

	public static final Block IRON_COAL_BLOCK = registerCoalBlock("iron_coal_block", MaterialColor.IRON);
	public static final Block GOLD_COAL_BLOCK = registerCoalBlock("gold_coal_block", MaterialColor.GOLD);
	public static final Block DIAMOND_COAL_BLOCK = registerCoalBlock("diamond_coal_block", MaterialColor.DIAMOND);
	public static final Block EMERALD_COAL_BLOCK = registerCoalBlock("emerald_coal_block", MaterialColor.EMERALD);
	//public static final Block AEON_COAL_BLOCK = registerCoalBlock("aeon_coal_block", MaterialColor.RED);

	public static final Block IRON_COAL_TORCH_BLOCK = registerTorchBlock("iron_coal_torch");
	public static final Block GOLD_COAL_TORCH_BLOCK = registerTorchBlock("gold_coal_torch");
	public static final Block DIAMOND_COAL_TORCH_BLOCK = registerTorchBlock("diamond_coal_torch");
	public static final Block EMERALD_COAL_TORCH_BLOCK = registerTorchBlock("emerald_coal_torch");
	public static final Block AEON_COAL_TORCH_BLOCK = registerTorchBlock("aeon_coal_torch");

	public static final Block IRON_COAL_WALL_TORCH = registerWallTorchBlock("iron_coal_wall_torch");
	public static final Block GOLD_COAL_WALL_TORCH = registerWallTorchBlock("gold_coal_wall_torch");
	public static final Block DIAMOND_COAL_WALL_TORCH = registerWallTorchBlock("diamond_coal_wall_torch");
	public static final Block EMERALD_COAL_WALL_TORCH = registerWallTorchBlock("emerald_coal_wall_torch");
	public static final Block AEON_COAL_WALL_TORCH = registerWallTorchBlock("aeon_coal_wall_torch");

	public static final Item IRON_COAL = registerFuel("iron_coal", 12);
	public static final Item GOLD_COAL = registerFuel("gold_coal", 24);
	public static final Item DIAMOND_COAL = registerFuel("diamond_coal", 48);
	public static final Item EMERALD_COAL = registerFuel("emerald_coal", 96);
	public static final Item AEON_COAL = registerFuel("aeon_coal", Integer.MAX_VALUE/200F);

	public static final Item COAL_CHUNK = registerFuel("coal_chunk", 1);
	public static final Item CHARCOAL_CHUNK = registerFuel("charcoal_chunk", 1);
	public static final Item IRON_COAL_CHUNK = registerFuel("iron_coal_chunk", 12F/8);
	public static final Item GOLD_COAL_CHUNK = registerFuel("gold_coal_chunk", 24F/8);
	public static final Item DIAMOND_COAL_CHUNK = registerFuel("diamond_coal_chunk", 48F/8);
	public static final Item EMERALD_COAL_CHUNK = registerFuel("emerald_coal_chunk", 96F/8);

	public static final Item IRON_COAL_BLOCK_ITEM = registerFuelBlockItem("iron_coal_block", IRON_COAL_BLOCK, 12*10);
	public static final Item GOLD_COAL_BLOCK_ITEM = registerFuelBlockItem("gold_coal_block", GOLD_COAL_BLOCK, 24*10);
	public static final Item DIAMOND_COAL_BLOCK_ITEM = registerFuelBlockItem("diamond_coal_block", DIAMOND_COAL_BLOCK, 48*10);
	public static final Item EMERALD_COAL_BLOCK_ITEM = registerFuelBlockItem("emerald_coal_block", EMERALD_COAL_BLOCK, 96*10);
	//public static final Item AEON_COAL_BLOCK_ITEM = registerFuelBlockItem("aeon_coal_block", AEON_COAL_BLOCK, Integer.MAX_VALUE/200F);

	public static final Item IRON_COAL_TORCH = registerTorchItem("iron_coal_torch", IRON_COAL_TORCH_BLOCK, IRON_COAL_WALL_TORCH);
	public static final Item GOLD_COAL_TORCH = registerTorchItem("gold_coal_torch", GOLD_COAL_TORCH_BLOCK, GOLD_COAL_WALL_TORCH);
	public static final Item DIAMOND_COAL_TORCH = registerTorchItem("diamond_coal_torch", DIAMOND_COAL_TORCH_BLOCK, DIAMOND_COAL_WALL_TORCH);
	public static final Item EMERALD_COAL_TORCH = registerTorchItem("emerald_coal_torch", EMERALD_COAL_TORCH_BLOCK, EMERALD_COAL_WALL_TORCH);
	public static final Item AEON_COAL_TORCH = registerTorchItem("aeon_coal_torch", AEON_COAL_TORCH_BLOCK, AEON_COAL_WALL_TORCH);



	private static Item registerFuel(String name, float smeltAmount) {
		return registerFuelItem(name, new Item(ITEM_SETTINGS), smeltAmount);
	}

	private static Item registerFuelBlockItem(String name, Block block, float smeltAmount) {
		return registerFuelItem(name, new BlockItem(block, ITEM_SETTINGS), smeltAmount);
	}

	private static Item registerFuelItem(String name, Item item, float smeltAmount) {
		System.out.println(smeltAmount + ": " + (smeltAmount % 1 == 0));
		String amount = smeltAmount == (Integer.MAX_VALUE/200F)? "§cUnlimited" : "§c" + (smeltAmount % 1 == 0? (int)smeltAmount : smeltAmount);
		TranslatableText text = new TranslatableText("tooltip.burns", amount, smeltAmount == 1? "" : "§4s");
		ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, list) -> {
			if (itemStack.getItem() == item)
				list.add(text);
		});
		FuelRegistry.INSTANCE.add(item, Math.round(smeltAmount * 200));
		return Registry.register(Registry.ITEM, new Identifier("ironcoals", name), item);
	}


	private static Block registerCoalBlock(String name, MaterialColor color) {
		return registerBlock(name, new Block(FabricBlockSettings.of(Material.STONE, color).requiresTool().strength(5F, 6F)));
	}

	private static Item registerTorchItem(String name, Block floor, Block wall) {
		return Registry.register(Registry.ITEM, new Identifier("ironcoals", name), new WallStandingBlockItem(floor, wall, ITEM_SETTINGS));
	}

	private static Block registerTorchBlock(String name) {
		return registerBlock(name, new IronCoalTorchBlock());
	}

	private static Block registerWallTorchBlock(String name) {
		return registerBlock(name, new IronCoalWallTorchBlock());
	}

	private static Block registerBlock(String name, Block block) {
		return Registry.register(Registry.BLOCK, new Identifier("ironcoals", name), block);
	}

	@Override
	public void onInitialize() {}
}
