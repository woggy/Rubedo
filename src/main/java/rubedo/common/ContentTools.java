package rubedo.common;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import rubedo.RubedoCore;
import rubedo.items.tools.ToolAxe;
import rubedo.items.tools.ToolEnchantmentRecipes;
import rubedo.items.tools.ToolPickaxe;
import rubedo.items.tools.ToolScythe;
import rubedo.items.tools.ToolShovel;
import rubedo.items.tools.ToolSword;
import cpw.mods.fml.common.registry.GameRegistry;

public class ContentTools implements IContent {

	public static ToolSword toolSword;
	public static ToolPickaxe toolPickaxe;
	public static ToolShovel toolShovel;
	public static ToolAxe toolAxe;
	public static ToolScythe toolScythe;

	@Override
	public void config(Configuration config) {
		// Tools
		Config.initId("ToolSword");
		Config.initId("ToolPickaxe");
		Config.initId("ToolShovel");
		Config.initId("ToolAxe");
	}

	@Override
	public void register() {
		toolSword = new ToolSword(Config.getId("ToolSword"));
		toolPickaxe = new ToolPickaxe(Config.getId("ToolPickaxe"));
		toolShovel = new ToolShovel(Config.getId("ToolShovel"));
		toolAxe = new ToolAxe(Config.getId("ToolAxe"));
		toolScythe = new ToolScythe(Config.getId("ToolScythe"));

		registerToolMaterials();
		registerToolRecipes();
	}

	public void registerToolMaterials() {
		Material flint = new Material();
		{
			flint.name = "flint";
			flint.durability = 60;
			flint.damage = 0;
			flint.speed = 2.0f;
			flint.miningLevel = 0;
			flint.swordHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/sword_head_flint"));
			flint.pickaxeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/pickaxe_head_flint"));
			flint.shovelHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/shovel_head_flint"));
			flint.axeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/axe_head_flint"));
			flint.scytheHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/scythe_head_flint"));
			flint.headMaterial = new ItemStack(Item.flint, 1, 0);
		}
		Material wood = new Material();
		{
			wood.name = "wood";
			wood.modifier = 0.5f;
			wood.special = 0;
			wood.rodMaterial = new ItemStack(Item.stick);
			wood.capMaterial = new ItemStack(Block.planks);
			// TODO: make this use ore dictionary so it accepts any plank
		}
		Material stone = new Material();
		{
			stone.name = "stone";
			stone.special = 1;
			stone.capMaterial = new ItemStack(Block.stone);
		}
		Material leather = new Material();
		{
			leather.name = "leather";
			leather.modifier = 1.0f;
			leather.rodMaterial = new ItemStack(Item.leather);
		}
		Material bone = new Material();
		{
			bone.name = "bone";
			bone.modifier = 1.2f;
			bone.rodMaterial = new ItemStack(Item.bone);
		}
		Material blazerod = new Material();
		{
			blazerod.name = "blazerod";
			blazerod.modifier = 2.0f;
			blazerod.rodMaterial = new ItemStack(Item.blazeRod);
		}
		Material copper = new Material();
		{
			copper.name = "copper";
			copper.durability = 130;
			copper.special = 2;
			copper.damage = 1;
			copper.speed = 4.0f;
			copper.miningLevel = 1;
			copper.swordHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/sword_head_copper"));
			copper.pickaxeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/pickaxe_head_copper"));
			copper.shovelHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/shovel_head_copper"));
			copper.axeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/axe_head_copper"));
			copper.scytheHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/scythe_head_copper"));
			copper.headMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("copper_ingot"));
			copper.capMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("copper_ingot"));
		}
		Material iron = new Material();
		{
			iron.name = "iron";
			iron.durability = 250;
			iron.special = 3;
			iron.damage = 2;
			iron.speed = 6.0f;
			iron.miningLevel = 2;
			iron.swordHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/sword_head_iron"));
			iron.pickaxeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/pickaxe_head_iron"));
			iron.shovelHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/shovel_head_iron"));
			iron.axeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/axe_head_iron"));
			iron.scytheHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/scythe_head_iron"));
			iron.headMaterial = new ItemStack(Item.ingotIron, 1, 0);
			iron.capMaterial = new ItemStack(Item.ingotIron);
		}
		Material gold = new Material();
		{
			gold.name = "gold";
			gold.durability = 35;
			gold.special = 3;
			gold.damage = 0;
			gold.speed = 6.0f;
			gold.miningLevel = 0;
			gold.swordHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/sword_head_gold"));
			gold.pickaxeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/pickaxe_head_gold"));
			gold.shovelHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/shovel_head_gold"));
			gold.axeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/axe_head_gold"));
			gold.scytheHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/scythe_head_gold"));
			gold.headMaterial = new ItemStack(Item.ingotGold, 1, 0);
			gold.capMaterial = new ItemStack(Item.ingotGold);
		}
		Material orichalcum = new Material();
		{
			orichalcum.name = "orichalcum";
			orichalcum.durability = 500;
			orichalcum.special = 3;
			orichalcum.damage = 2;
			orichalcum.speed = 4.0f;
			orichalcum.miningLevel = 1;
			orichalcum.swordHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/sword_head_orichalcum"));
			orichalcum.pickaxeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/pickaxe_head_orichalcum"));
			orichalcum.shovelHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/shovel_head_orichalcum"));
			orichalcum.axeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/axe_head_orichalcum"));
			orichalcum.scytheHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/scythe_head_orichalcum"));
			orichalcum.headMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("orichalcum_ingot"));
			orichalcum.capMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("orichalcum_ingot"));
		}
		Material silver = new Material();
		{
			silver.name = "silver";
			silver.durability = 75;
			silver.special = 2;
			silver.damage = 1;
			silver.speed = 8.0f;
			silver.miningLevel = 3;
			silver.swordHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/sword_head_silver"));
			silver.pickaxeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/pickaxe_head_silver"));
			silver.shovelHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/shovel_head_silver"));
			silver.axeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/axe_head_silver"));
			silver.scytheHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/scythe_head_silver"));
			silver.headMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("silver_ingot"));
			silver.capMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("silver_ingot"));
		}
		Material steel = new Material();
		{
			steel.name = "steel";
			steel.durability = 1500;
			steel.special = 2;
			steel.damage = 3;
			steel.speed = 9.0f;
			steel.miningLevel = 3;
			steel.swordHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/sword_head_steel"));
			steel.pickaxeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/pickaxe_head_steel"));
			steel.shovelHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/shovel_head_steel"));
			steel.axeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/axe_head_steel"));
			steel.scytheHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/scythe_head_steel"));
			steel.headMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("steel_ingot"));
			steel.capMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("steel_ingot"));
		}
		Material mythril = new Material();
		{
			mythril.name = "mythril";
			mythril.durability = 130;
			mythril.special = 2;
			mythril.damage = 1;
			mythril.speed = 12.0f;
			mythril.miningLevel = 4;
			mythril.swordHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/sword_head_mythril"));
			mythril.pickaxeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/pickaxe_head_mythril"));
			mythril.shovelHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/shovel_head_mythril"));
			mythril.axeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/axe_head_mythril"));
			mythril.scytheHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/scythe_head_mythril"));
			mythril.headMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("mythril_ingot"));
			mythril.capMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("mythril_ingot"));
		}
		Material hepatizon = new Material();
		{
			hepatizon.name = "hepatizon";
			hepatizon.durability = 750;
			hepatizon.special = 2;
			hepatizon.damage = 3;
			hepatizon.speed = 10.0f;
			hepatizon.miningLevel = 4;
			hepatizon.swordHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/sword_head_hepatizon"));
			hepatizon.pickaxeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/pickaxe_head_hepatizon"));
			hepatizon.shovelHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/shovel_head_hepatizon"));
			hepatizon.axeHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/axe_head_hepatizon"));
			hepatizon.scytheHead = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems
							.getTextureIndex("tools/scythe_head_hepatizon"));
			hepatizon.headMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("hepatizon_ingot"));
			hepatizon.capMaterial = new ItemStack(ContentWorld.metalItems, 1,
					ContentWorld.metalItems.getTextureIndex("hepatizon_ingot"));
		}

		toolHeads = new HashMap<String, Material>();
		{
			toolHeads.put(flint.name, flint);
			toolHeads.put(copper.name, copper);
			toolHeads.put(iron.name, iron);
			toolHeads.put(gold.name, gold);
			toolHeads.put(silver.name, silver);
			toolHeads.put(steel.name, steel);
			toolHeads.put(mythril.name, mythril);
			toolHeads.put(hepatizon.name, hepatizon);
		}

		toolRods = new HashMap<String, Material>();
		{
			toolRods.put(wood.name, wood);
			toolRods.put(leather.name, leather);
			toolRods.put(bone.name, bone);
			toolRods.put(blazerod.name, blazerod);
		}

		toolCaps = new HashMap<String, Material>();
		{
			toolCaps.put(wood.name, wood);
			toolCaps.put(stone.name, stone);
			toolCaps.put(copper.name, copper);
			toolCaps.put(iron.name, iron);
			toolCaps.put(gold.name, gold);
		}
	}

	private void registerToolRecipes() {
		GameRegistry.addRecipe(new ToolEnchantmentRecipes());

		Item[] toBeRemoved = {Item.swordDiamond, Item.swordGold,
				Item.swordIron, Item.swordStone, Item.swordWood,
				Item.shovelDiamond, Item.shovelGold, Item.shovelIron,
				Item.shovelStone, Item.shovelWood, Item.axeDiamond,
				Item.axeGold, Item.axeIron, Item.axeStone, Item.axeWood,
				Item.hoeDiamond, Item.hoeGold, Item.hoeIron, Item.hoeStone,
				Item.hoeWood, Item.pickaxeDiamond, Item.pickaxeGold,
				Item.pickaxeIron, Item.pickaxeStone, Item.pickaxeWood};
		for (int i = 0; i < toBeRemoved.length; i++) {
			RecipeRemover.removeAnyRecipe(new ItemStack(toBeRemoved[i]));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).removeItem(
					new ItemStack(toBeRemoved[i]));
		}

		// TODO: figure out how to make repair recipes work
		for (Entry<String, Material> headEntry : toolHeads.entrySet())
			for (Entry<String, Material> rodEntry : toolRods.entrySet())
				for (Entry<String, Material> capEntry : toolCaps.entrySet()) {
					// Sword Recipes
					GameRegistry.addRecipe(new ShapedRecipes(1, 3,
							new ItemStack[]{headEntry.getValue().swordHead,
									rodEntry.getValue().rodMaterial,
									capEntry.getValue().capMaterial}, toolSword
									.buildTool(headEntry.getKey(),
											rodEntry.getKey(),
											capEntry.getKey())));

					// Shovel Recipes
					GameRegistry.addRecipe(new ShapedRecipes(1, 3,
							new ItemStack[]{headEntry.getValue().shovelHead,
									rodEntry.getValue().rodMaterial,
									capEntry.getValue().capMaterial},
							toolShovel.buildTool(headEntry.getKey(),
									rodEntry.getKey(), capEntry.getKey())));

					// Axe Recipes
					GameRegistry.addRecipe(new ShapedRecipes(1, 3,
							new ItemStack[]{headEntry.getValue().axeHead,
									rodEntry.getValue().rodMaterial,
									capEntry.getValue().capMaterial}, toolAxe
									.buildTool(headEntry.getKey(),
											rodEntry.getKey(),
											capEntry.getKey())));

					// Scythe Recipes
					GameRegistry.addRecipe(new ShapedRecipes(1, 3,
							new ItemStack[]{headEntry.getValue().scytheHead,
									rodEntry.getValue().rodMaterial,
									capEntry.getValue().capMaterial},
							toolScythe.buildTool(headEntry.getKey(),
									rodEntry.getKey(), capEntry.getKey())));

					// Pickaxe Recipes
					GameRegistry.addRecipe(new ShapedRecipes(1, 3,
							new ItemStack[]{headEntry.getValue().pickaxeHead,
									rodEntry.getValue().rodMaterial,
									capEntry.getValue().capMaterial},
							toolPickaxe.buildTool(headEntry.getKey(),
									rodEntry.getKey(), capEntry.getKey())));
				}

		// Tool head recipes
		for (Entry<String, Material> headEntry : toolHeads.entrySet()) {
			RubedoCore.logger.info(headEntry.getValue().headMaterial
					.getDisplayName());

			// Sword heads
			GameRegistry.addRecipe(new ShapedRecipes(1, 2, new ItemStack[]{
					headEntry.getValue().headMaterial,
					headEntry.getValue().headMaterial},
					headEntry.getValue().swordHead));
			// Shovel heads
			GameRegistry.addRecipe(new ShapedRecipes(2, 2, new ItemStack[]{
					headEntry.getValue().headMaterial,
					headEntry.getValue().headMaterial,
					headEntry.getValue().headMaterial,
					headEntry.getValue().headMaterial},
					headEntry.getValue().shovelHead));
			// Axe heads
			GameRegistry.addRecipe(new ShapedRecipes(2, 2, new ItemStack[]{
					headEntry.getValue().headMaterial,
					headEntry.getValue().headMaterial, null,
					headEntry.getValue().headMaterial},
					headEntry.getValue().axeHead));
			// Scythe heads
			GameRegistry.addRecipe(new ShapedRecipes(3, 2, new ItemStack[]{
					headEntry.getValue().headMaterial,
					headEntry.getValue().headMaterial,
					headEntry.getValue().headMaterial, null, null,
					headEntry.getValue().headMaterial},
					headEntry.getValue().scytheHead));
			// Pick heads
			GameRegistry.addRecipe(new ShapedRecipes(3, 1, new ItemStack[]{
					headEntry.getValue().headMaterial,
					headEntry.getValue().headMaterial,
					headEntry.getValue().headMaterial},
					headEntry.getValue().pickaxeHead));
		}
	}
	public static Map<String, Material> toolHeads;
	public static Map<String, Material> toolRods;
	public static Map<String, Material> toolCaps;

	public class Material {
		public String name;
		public int durability;
		public float modifier;
		public int damage;
		public float speed;
		public int special;
		public int miningLevel;
		public ItemStack swordHead;
		public ItemStack pickaxeHead;
		public ItemStack shovelHead;
		public ItemStack axeHead;
		public ItemStack scytheHead;
		public ItemStack headMaterial;
		public ItemStack rodMaterial;
		public ItemStack capMaterial;
	}
}
