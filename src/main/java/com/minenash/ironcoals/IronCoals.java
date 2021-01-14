package com.minenash.ironcoals;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class IronCoals implements ModInitializer {

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier("ironcoals","general"), () -> new ItemStack(IronCoals.IRON_COAL));

	private static final Item.Settings itemSettings = new Item.Settings().group(ITEM_GROUP);

	public static final Block IRON_COAL_BLOCK = registerBlock("iron_coal_block", MaterialColor.IRON);
	public static final Block GOLD_COAL_BLOCK = registerBlock("gold_coal_block", MaterialColor.GOLD);
	public static final Block DIAMOND_COAL_BLOCK = registerBlock("diamond_coal_block", MaterialColor.DIAMOND);
	public static final Block EMERALD_COAL_BLOCK = registerBlock("emerald_coal_block", MaterialColor.EMERALD);

	public static final Item IRON_COAL = registerFuel("iron_coal", 12);
	public static final Item GOLD_COAL = registerFuel("gold_coal", 24);
	public static final Item DIAMOND_COAL = registerFuel("diamond_coal", 48);
	public static final Item EMERALD_COAL = registerFuel("emerald_coal", 96);
	public static final Item AEON_COAL = registerFuel("aeon_coal", Integer.MAX_VALUE/200);

	public static final Item COAL_CHUNK = registerFuel("coal_chunk", 1);
	public static final Item CHARCOAL_CHUNK = registerFuel("charcoal_chunk", 1);
	public static final Item IRON_COAL_CHUNK = registerFuel("iron_coal_chunk", 12/8);
	public static final Item GOLD_COAL_CHUNK = registerFuel("gold_coal_chunk", 24/8);
	public static final Item DIAMOND_COAL_CHUNK = registerFuel("diamond_coal_chunk", 48/8);
	public static final Item EMERALD_COAL_CHUNK = registerFuel("emerald_coal_chunk", 96/8);

	public static final Item IRON_COAL_BLOCK_ITEM = registerFuelBlockItem("iron_coal_block", IRON_COAL_BLOCK, 12*10);
	public static final Item GOLD_COAL_BLOCK_ITEM = registerFuelBlockItem("gold_coal_block", GOLD_COAL_BLOCK, 24*10);
	public static final Item DIAMOND_COAL_BLOCK_ITEM = registerFuelBlockItem("diamond_coal_block", DIAMOND_COAL_BLOCK, 48*10);
	public static final Item EMERALD_COAL_BLOCK_ITEM = registerFuelBlockItem("emerald_coal_block", EMERALD_COAL_BLOCK, 96*10);

	private static Item registerFuel(String name, int smeltAmount) {
		return registerItem(name, new Item(itemSettings), smeltAmount);
	}

	private static Item registerFuelBlockItem(String name, Block block, int smeltAmount) {
		return registerItem(name, new BlockItem(block, itemSettings), smeltAmount);
	}

	private static Item registerItem(String name, Item item, int smeltAmount) {
		TranslatableText text = new TranslatableText("tooltip.burns", smeltAmount == (Integer.MAX_VALUE/200)? "§cUnlimited" : "§c" + smeltAmount, smeltAmount == 1? "" : "§4s");
		ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, list) -> {
			if (itemStack.getItem() == item)
				list.add(text);
		});
		FuelRegistry.INSTANCE.add(item, smeltAmount * 200);
		return Registry.register(Registry.ITEM, new Identifier("ironcoals", name), item);
	}


	private static Block registerBlock(String name, MaterialColor color) {
		return Registry.register(Registry.BLOCK, new Identifier("ironcoals", name), new Block(
				FabricBlockSettings.of(Material.AGGREGATE, color).requiresTool().strength(5F, 6F)));
	}

	@Override
	public void onInitialize() {}
}
