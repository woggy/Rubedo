package rubedo.items.tools.recipes;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import rubedo.items.tools.ToolBase;
import rubedo.items.tools.ToolProperties;

public class ToolEnchantmentRecipes implements IRecipe {
	private ToolProperties tool;
	private ItemStack enchantedBook;

	@Override
	public ItemStack getRecipeOutput()
	{
		return null;
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		this.enchantedBook = null;
		this.tool = null;

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 3; ++j)
			{
				ItemStack itemstack = par1InventoryCrafting.getStackInRowAndColumn(j, i);

				if (itemstack != null)
				{
					if (Item.getIdFromItem(itemstack.getItem()) == Item.getIdFromItem(Items.enchanted_book)) {
						if (this.enchantedBook != null)
							return false;

						this.enchantedBook = itemstack;
					}

					if (itemstack.getItem() instanceof ToolBase) {
						if (this.tool != null)
							return false;

						this.tool = ((ToolBase)itemstack.getItem()).getToolProperties(itemstack);
					}
				}
			}
		}

		return this.enchantedBook != null && this.tool != null;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		ItemStack output = this.tool.getStack().copy();

		NBTTagList toolList = getEnchantmentTagList(output);
		NBTTagList bookList = getEnchantmentTagList(this.enchantedBook.copy());

		boolean changed = false;

		for (int iBook = 0; iBook < bookList.tagCount(); iBook++) {
			boolean found = false;
			NBTTagCompound bookEnchant = bookList.getCompoundTagAt(iBook);

			//Check the tool for allowed enchants
			if (!this.tool.getItem().getAllowedEnchantments().contains((int)bookEnchant.getShort("id")))
				continue;

			//Check if the enchant already exists
			for (int iTool = 0; iTool < toolList.tagCount(); iTool++) {
				NBTTagCompound toolEnchant = toolList.getCompoundTagAt(iTool);
				if (toolEnchant.getShort("id") == bookEnchant.getShort("id")) {
					found = true;
					if (toolEnchant.getShort("lvl") < bookEnchant.getShort("lvl")) {
						changed = true;
						toolEnchant.setShort("lvl", bookEnchant.getShort("lvl"));
					}
					continue;
				}
			}

			//It doesn't exist yet, just add it
			if (!found) {
				boolean allowed = true;
				for (int iTool = 0; iTool < toolList.tagCount(); iTool++) {
					int toolEnchant = toolList.getCompoundTagAt(iTool).getShort("id");
					if (!Enchantment.enchantmentsList[toolEnchant]
							.canApplyTogether(Enchantment.enchantmentsList[bookEnchant.getShort("id")])) {
						allowed = false;
					}
				}

				if (allowed) {
					changed = true;
					toolList.appendTag(bookEnchant);
				}
			}
		}

		if (output.getEnchantmentTagList() == null && toolList.tagCount() > 0) {
			output.getTagCompound().setTag("ench", toolList);
		}

		return changed ? output : null;
	}

	/**
	 * Returns the size of the recipe area
	 */
	@Override
	public int getRecipeSize()
	{
		return 2;
	}

	public static NBTTagList getEnchantmentTagList(ItemStack itemStack) {
		NBTTagList nbttaglist = itemStack.getEnchantmentTagList();

		if (nbttaglist == null || (nbttaglist.tagCount() == 0 && Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(Items.enchanted_book)))
			nbttaglist = Items.enchanted_book.func_92110_g(itemStack);

		return nbttaglist != null ? nbttaglist : new NBTTagList();
	}
}