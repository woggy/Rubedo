package rubedo.items.tools;

import java.util.List;

import rubedo.common.ContentTools;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class ToolShovel extends ToolBase {

	public ToolShovel(int id) {
		super(id);
	}

	@Override
	public String getName() {
		return "shovel";
	}

	@Override
	public int getItemDamageOnHit() {
		return 2;
	}

	@Override
	public int getItemDamageOnBreak() {
		return 1;
	}

	@Override
	public float getEffectiveBlockSpeed() {
		return 4.0F;
	}

	@Override
	public Material[] getEffectiveMaterials() {
		return new Material[]{Material.craftedSnow, Material.grass,
				Material.ground, Material.sand, Material.snow};
	}

	@Override
	public Block[] getEffectiveBlocks() {
		return new Block[]{Block.grass, Block.dirt, Block.sand, Block.gravel,
				Block.snow, Block.blockSnow, Block.blockClay,
				Block.tilledField, Block.slowSand, Block.mycelium};
	}

	@Override
	public List<Integer> getAllowedEnchantments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack buildTool(String head, String rod, String cap) {
		ItemStack tool = new ItemStack(ContentTools.toolShovel);

		super.buildTool(tool, head, rod, cap);

		return tool;
	}

	public boolean canHarvestBlock(Block par1Block) {
		return par1Block == Block.snow ? true : par1Block == Block.blockSnow;
	}
}