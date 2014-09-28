package rubedo.common;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;
import rubedo.common.materials.MaterialMultiItem;
import rubedo.items.ItemToolHead;
import rubedo.items.tools.ToolAxe;
import rubedo.items.tools.ToolBase;
import rubedo.items.tools.ToolPickaxe;
import rubedo.items.tools.ToolScythe;
import rubedo.items.tools.ToolShovel;
import rubedo.items.tools.ToolSword;
import rubedo.items.tools.recipes.ToolEnchantmentRecipes;
import rubedo.items.tools.recipes.ToolRepairRecipes;
import cpw.mods.fml.common.registry.GameRegistry;

public class ContentTools extends ContentMultiItem<ToolBase, MaterialMultiItem>
		implements IContent {

	public Map<MaterialMultiItem, String> VanillaToolMaterials;

	protected ContentTools() {
		super(ContentTools.class);

		// Tool kinds
		Set<Class<? extends ToolBase>> toolKinds = new LinkedHashSet<Class<? extends ToolBase>>();

		toolKinds.add(ToolSword.class);
		toolKinds.add(ToolPickaxe.class);
		toolKinds.add(ToolShovel.class);
		toolKinds.add(ToolAxe.class);
		toolKinds.add(ToolScythe.class);

		this.setKinds(toolKinds);
	}

	private void initializeToolMaterials() {
		// Tool materials
		Set<Class<? extends MaterialMultiItem>> toolMaterials = new LinkedHashSet<Class<? extends MaterialMultiItem>>();

		toolMaterials.add(MaterialMultiItem.Wood.class);
		toolMaterials.add(MaterialMultiItem.Flint.class);
		toolMaterials.add(MaterialMultiItem.Copper.class);
		toolMaterials.add(MaterialMultiItem.Iron.class);
		toolMaterials.add(MaterialMultiItem.Gold.class);
		toolMaterials.add(MaterialMultiItem.Orichalcum.class);
		toolMaterials.add(MaterialMultiItem.Silver.class);
		toolMaterials.add(MaterialMultiItem.Steel.class);
		toolMaterials.add(MaterialMultiItem.Mythril.class);
		toolMaterials.add(MaterialMultiItem.Hepatizon.class);

		// Rods
		toolMaterials.add(MaterialMultiItem.Bone.class);
		toolMaterials.add(MaterialMultiItem.Leather.class);
		toolMaterials.add(MaterialMultiItem.Blazerod.class);

		this.setMaterials(toolMaterials);

		// Vanilla tool materials
		this.VanillaToolMaterials = new LinkedHashMap<MaterialMultiItem, String>();

		this.VanillaToolMaterials.put(
				this.getMaterial(MaterialMultiItem.Wood.class), "wooden");
		this.VanillaToolMaterials.put(
				this.getMaterial(MaterialMultiItem.Flint.class), "stone");
		this.VanillaToolMaterials.put(
				this.getMaterial(MaterialMultiItem.Iron.class), "iron");
		this.VanillaToolMaterials.put(
				this.getMaterial(MaterialMultiItem.Gold.class), "golden");
		this.VanillaToolMaterials.put(
				this.getMaterial(MaterialMultiItem.Steel.class), "diamond");
	}

	@Override
	public void config(Configuration config) {
	}

	@Override
	public void registerBase() {
		super.registerBase();

		this.initializeToolMaterials();

		boolean registerVanillaTools = !ContentVanilla.Config.replaceVanillaTools;

		// Get all materials
		for (MaterialMultiItem material : this.getMaterials()) {
			// For all tool heads
			if (material.headMaterial != null) {
				// Get all tool kinds
				for (ToolBase kind : this.getItems()) {
					// Check if we need to exclude vanilla materials
					if (registerVanillaTools
							|| !this.VanillaToolMaterials.containsKey(material)) {
						String name = kind.getName() + "_head_" + material.name;
						Item item = ItemToolHead.getHeadMap().get(name);
						GameRegistry.registerItem(item, name);
					}
				}
			}
		}
	}

	@Override
	public void registerDerivatives() {
		this.registerToolRecipes();
	}

	@Override
	public void tweak() {
		MinecraftForge.EVENT_BUS.register(new ToolEnchantmentRecipes());
	}

	private void registerToolRecipes() {
		GameRegistry.addRecipe(new ToolRepairRecipes());

		// Tool head recipes
		for (MaterialMultiItem material : this.getMaterials()) {
			if (material.headMaterial != null) {
				// Sword heads
				GameRegistry.addRecipe(new ShapedOreRecipe(material
						.getToolHead("sword"), "X", "X", 'X',
						material.headMaterial));
				// Shovel heads
				GameRegistry.addRecipe(new ShapedOreRecipe(material
						.getToolHead("shovel"), "XX", "XX", 'X',
						material.headMaterial));
				// Axe heads
				GameRegistry.addRecipe(new ShapedOreRecipe(material
						.getToolHead("axe"), true, "XX", " X", 'X',
						material.headMaterial));
				// Scythe heads
				GameRegistry.addRecipe(new ShapedOreRecipe(material
						.getToolHead("scythe"), true, "XXX", "X  ", 'X',
						material.headMaterial));
				// Pick heads
				GameRegistry.addRecipe(new ShapedOreRecipe(material
						.getToolHead("pickaxe"), "XXX", 'X',
						material.headMaterial));
			}
		}
	}
}
