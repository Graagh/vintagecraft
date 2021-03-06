package at.tyron.vintagecraft.Block.Organic;

import java.util.List;

import at.tyron.vintagecraft.VintageCraftConfig;
import at.tyron.vintagecraft.BlockClass.BaseBlockClass;
import at.tyron.vintagecraft.Interfaces.IStateEnum;
import at.tyron.vintagecraft.World.BlocksVC;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDoubleWoodenSlab extends BlockWoodenSlabVC {
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
    	return;
    }
    
	@Override
	public int multistateAvailableTypes() {
		return 16;
	}
	
	@Override
	public BaseBlockClass getBlockClass() {
		return BlocksVC.doubleslab;
	}

	
	@Override
	public boolean isDouble() {
		return true;
	}

	@Override
	public Block registerMultiState(String blockclassname, Class<? extends ItemBlock> itemclass, IStateEnum[] types, String folderprefix) {
		if (VintageCraftConfig.debugBlockRegistration) System.out.println("register block " + this);
		GameRegistry.registerBlock(this, itemclass, blockclassname);
		setUnlocalizedName(blockclassname);
		
		return this;
	}
}
