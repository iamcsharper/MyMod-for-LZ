package ru.XuPoH.shaders.items;

import ru.XuPoH.shaders.ShadersMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AnomalyCheckerItem extends BasicIconedItem {
	public AnomalyCheckerItem() {
		super();
		this.setUnlocalizedName("LZ_anomalyCheckerItem");
		this.setCreativeTab(ShadersMod.anomalyTab);
		this.setMaxStackSize(32);
		this.setFull3D();
	}

	@Override
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_,
			Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
		super.onUpdate(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
	}
}