package ru.XuPoH.shaders.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import ru.XuPoH.shaders.ItemsBlocksCollection;
import ru.XuPoH.shaders.ShadersMod;
import ru.XuPoH.shaders.lib.Reference;
import ru.XuPoH.shaders.tileentities.FryEmitterTileEntity;

/*
 * Мультиблок. Создаёт столб блоков вверх высотой в FRY_HEIGHT
 * При разрушении любого из этих блоков ломает остальные.
 */
public class FryEmitterBlock extends BlockContainer {
	public static final int MAX_VISITORS = 5;

	public static final int FRY_HEIGHT = 4;
	private List<Vec3> connectedBlocksPositions = new ArrayList<Vec3>();
	
	private IIcon[] icons = new IIcon[6];

	public FryEmitterBlock(Material mat) {
		super(mat);
		
		this.setBlockName("LZ_fryEmitter");
		this.setCreativeTab(ShadersMod.anomalyTab);
		this.setHardness(-1f);
		this.setResistance(10f);
	}
	
	@Override
	public void onBlockPreDestroy(World world, int p_149725_2_,
			int p_149725_3_, int p_149725_4_, int p_149725_5_) {
		this.destroyConnected(world);
	}
	
	public void destroyConnected(World contextWorld) {
		for (Vec3 pos : connectedBlocksPositions) {
			Block block = contextWorld.getBlock((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord);
			
			if (block instanceof FryBlock) {
				FryBlock fry = (FryBlock) block;
				
				System.err.println("HEEEY! block " + block.getUnlocalizedName() + " removed from pos " + pos.toString());
			}
			
			contextWorld.func_147480_a((int)pos.xCoord, (int)pos.yCoord, (int)pos.zCoord, false);
		}
		
		connectedBlocksPositions.clear();
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		Block block = (Block) ItemsBlocksCollection.fryBlock.setEmitterPos(x, y, z);
		
		for (int j = 1; j < FRY_HEIGHT; ++j) {
			if (world.getBlock(x, y + j, z).getMaterial() == Material.air) {
				connectedBlocksPositions.clear();
				
				break;
			}
			
			Vec3 vec = Vec3.createVectorHelper(x, y + j, z);
			
			ShadersMod.logger.info(vec.toString() + " is the position ");
			world.setBlock(x, y + j, z, block);
			
			connectedBlocksPositions.add(vec);
		}
		
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FryEmitterTileEntity t = (FryEmitterTileEntity) world.getTileEntity(x, y, z);
            t.processActivate(player, world);
		}
		
		return true;
	}
	public String getTextureName() {
		return this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('_')+1);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		/*
		 * 0 bottom face
		 * 1 top face
		 * 2 northern face
		 * 3 southern face
		 * 4 western face
		 * 5 eastern face
		 */
		for (int i = 0; i < 6; ++i) {
			icons[i] = reg.registerIcon(Reference.modID + ':' + this.getTextureName() + '_' + i);
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
	    return this.icons[side];
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
	    return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
	    try
	    {
	        return new FryEmitterTileEntity();
	    }
	    catch (Exception e)
	    {
	        throw new RuntimeException(e);
	    }
	}
}