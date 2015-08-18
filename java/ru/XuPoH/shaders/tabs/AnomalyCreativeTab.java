package ru.XuPoH.shaders.tabs;

import ru.XuPoH.shaders.ItemsBlocksCollection;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AnomalyCreativeTab extends CreativeTabs {

	public AnomalyCreativeTab(int id, String name) {
		super(id, name);
	}

	@Override
	public Item getTabIconItem() {
		return ItemsBlocksCollection.anomalyChecker;
	}

}