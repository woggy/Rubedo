package rubedo.items.spells;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import rubedo.common.ContentSpells;
import rubedo.common.materials.MaterialMultiItem;
import rubedo.raycast.IBlockRayFilter;
import rubedo.raycast.IShapedRayCast;
import rubedo.raycast.ShapedRayCast;
import rubedo.raycast.SphericalRayCast;
import rubedo.util.Singleton;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

public class SpellArea extends SpellBase {

	public SpellArea() {
		super();
	}

	@Override
	public String getName() {
		return "area";
	}

	@Override
	public void castSpell(World world, EntityPlayer entityPlayer,
			ItemStack itemStack) {
		SpellProperties properties = this.getSpellProperties(itemStack);
		float focusModifier = properties.getFocusModifier();
		int power = properties.getPower();
		String effectType = properties.getEffectType();

		// get the camera position and direction
		Vec3 direction = ShapedRayCast.eulerToVec(world,
				entityPlayer.rotationPitch, entityPlayer.rotationYaw);
		Vec3 camera = ShapedRayCast.getCameraPosition(world, entityPlayer);

		// create a new raycaster
		IShapedRayCast rayCaster = new SphericalRayCast(world, camera.xCoord,
				camera.yCoord, camera.zCoord, direction.xCoord,
				direction.yCoord, direction.zCoord, focusModifier);

		ChunkPosition cameraCP = new ChunkPosition(camera);

		for (Entity entity : rayCaster.getEntitiesExcludingEntity(entityPlayer)) {
			SoulNetworkHandler.syphonAndDamageFromNetwork(itemStack,
					entityPlayer, properties.getCost());
			SpellEffects.hitEntity(world, entityPlayer, properties);
			
			if (entityPlayer.getHealth() <= 0)
				break;
		}

		if (SpellEffects.hitsBlocks(effectType)) {
			IBlockRayFilter filter = new IBlockRayFilter() {
				@Override
				public boolean matches(WorldPosition position) {
					if (position.getBlock() == null)
						return false;
					else if (position.getBlock().isAir(position.world,
							position.position.chunkPosX,
							position.position.chunkPosY,
							position.position.chunkPosZ))
						return false;
					else
						return true;

				}

				@Override
				public float getBlockResistance(WorldPosition position) {
					return 0;
				}

			};

			for (ChunkPosition pos : rayCaster.getBlocks(filter)) {
				SoulNetworkHandler.syphonAndDamageFromNetwork(itemStack,
						entityPlayer, properties.getCost());
				SpellEffects.hitBlock(world, properties, pos.chunkPosX,
						pos.chunkPosY, pos.chunkPosZ, 1);
				
				if (entityPlayer.getHealth() <= 0)
					break;
			}
		}
	}

	@Override
	public ItemStack buildSpell(MaterialMultiItem base,
			MaterialMultiItem focus, MaterialMultiItem effect) {
		ContentSpells contentSpells = Singleton
				.getInstance(ContentSpells.class);
		ItemStack spell = new ItemStack(contentSpells.getItem(SpellArea.class));

		super.buildSpell(spell, base, focus, effect);

		return spell;
	}

}
