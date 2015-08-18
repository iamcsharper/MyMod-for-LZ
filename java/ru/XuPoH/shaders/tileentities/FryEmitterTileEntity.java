package ru.XuPoH.shaders.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.XuPoH.shaders.ShadersMod;
import ru.XuPoH.shaders.blocks.FryEmitterBlock;
import scala.actors.threadpool.Arrays;

public class FryEmitterTileEntity extends TileEntity {
	private String[] lastVisitors = new String[FryEmitterBlock.MAX_VISITORS];

	private String test = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

	public FryEmitterTileEntity() {
		for (int i = 0; i < lastVisitors.length; ++i) {
			lastVisitors[i] = String.valueOf(test.charAt(i));
		}
	}

	public void processActivate(EntityPlayer player, World world) {
		for (int i = 0; i < lastVisitors.length; ++i) {
			if (i > 0) {
				lastVisitors[i - 1] = lastVisitors[i];
			}
		}

		ShadersMod.logger.debug(Arrays.toString(lastVisitors));
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		for (int i = 0; i < lastVisitors.length; ++i) {
			this.lastVisitors[i] = nbt.getString("visitor_" + i);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		for (int i = 0; i < lastVisitors.length; ++i) {
			nbt.setString("visitor_" + i, this.lastVisitors[i]);
		}
	}
}