package ru.XuPoH.shaders.items;

import ru.XuPoH.shaders.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasicIconedItem extends Item {
	public BasicIconedItem() {
		super();
	}
	
	public String getTextureName() {
		return this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('_')+1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		this.itemIcon = register.registerIcon(Reference.modID + ':' + this.getTextureName());
	}
}