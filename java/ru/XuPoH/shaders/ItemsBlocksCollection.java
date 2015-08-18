package ru.XuPoH.shaders;

import net.minecraft.block.material.Material;
import ru.XuPoH.shaders.blocks.FryBlock;
import ru.XuPoH.shaders.blocks.FryEmitterBlock;
import ru.XuPoH.shaders.items.AnomalyCheckerItem;
import ru.XuPoH.shaders.tileentities.FryEmitterTileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemsBlocksCollection {
	public static FryEmitterBlock fryEmitter;
	public static FryBlock fryBlock;
	public static AnomalyCheckerItem anomalyChecker;
	public static FryEmitterTileEntity fryEmitterTile;
	
	public static void initItems() {
		anomalyChecker = new AnomalyCheckerItem();
		GameRegistry.registerItem(anomalyChecker, anomalyChecker.getUnlocalizedName());
	}
	
	public static void initBlocks() {
		fryEmitter = new FryEmitterBlock(Material.rock);
		GameRegistry.registerBlock(fryEmitter, fryEmitter.getUnlocalizedName());
		fryBlock = new FryBlock(Material.air);
		GameRegistry.registerBlock(fryBlock, fryBlock.getUnlocalizedName());
	}

	public static void initTiles() {
		fryEmitterTile = new FryEmitterTileEntity();
		GameRegistry.registerTileEntity(FryEmitterTileEntity.class, "fryEmitterTile");
	}
}