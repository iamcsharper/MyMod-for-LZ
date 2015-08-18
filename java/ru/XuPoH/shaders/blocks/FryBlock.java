package ru.XuPoH.shaders.blocks;

import ru.XuPoH.shaders.ShadersMod;
import ru.XuPoH.shaders.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class FryBlock extends Block {
	private Vec3 emitterPos;

	@Override
	public void onBlockDestroyedByPlayer(World world, int p_149664_2_,
			int p_149664_3_, int p_149664_4_, int p_149664_5_) {
		this.destroyConnected(world);
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int p_149723_2_,
			int p_149723_3_, int p_149723_4_, Explosion p_149723_5_) {
		this.destroyConnected(world);
	}

	public FryBlock setEmitterPos(int x, int y, int z) {
		this.emitterPos = Vec3.createVectorHelper(x, y, z);
		return this;
	}

	public FryBlock setEmitterPos(Vec3 pos) {
		this.emitterPos = pos;
		return this;
	}

	public void destroyConnected(World contextWorld) {
		if (emitterPos != null) {
			contextWorld.func_147480_a((int) emitterPos.xCoord,
					(int) emitterPos.yCoord, (int) emitterPos.zCoord, false);
			emitterPos = null;
		}
	}

	public FryBlock(Material mat) {
		super(mat);
		this.setBlockName("LZ_fryBlock");
		this.setCreativeTab(ShadersMod.anomalyTab);
		this.setHardness(1.0f);
		this.setResistance(10f);
	}

	public FryBlock(Material mat, Vec3 emitterPos) {
		this(mat);
		this.emitterPos = emitterPos;
	}

	public FryBlock(Material mat, int eX, int eY, int eZ) {
		this(mat);
		this.emitterPos = Vec3.createVectorHelper(eX, eY, eZ);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int i) {
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 8;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.attackEntityFrom(DamageSource.inFire, 2.5F);
	}
}