package at.tyron.vintagecraft.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import at.tyron.vintagecraft.ModInfo;
import at.tyron.vintagecraft.TileEntity.TEOre;
import at.tyron.vintagecraft.World.EnumMaterialDeposit;
import at.tyron.vintagecraft.World.EnumOreType;
import at.tyron.vintagecraft.World.EnumRockType;
import at.tyron.vintagecraft.item.ItemOre;
import at.tyron.vintagecraft.item.ItemStone;
import at.tyron.vintagecraft.item.VCItems;

public class BlockOreVC extends BlockContainer {

	 
	protected BlockOreVC() {
		super(Material.iron);
		
		this.setDefaultState(this.blockState.getBaseState());
	}

    @Override
    public int getRenderType() { return 3; }

    @Override
    public boolean isOpaqueCube() { return true; }

    @Override
    public boolean isFullCube() { return false; }

    @Override
    public boolean isVisuallyOpaque() { return true; }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TEOre();
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
    }

    
    
    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TEOre) {
        	//System.out.println("te is of instance TEOre");
        	TEOre cte = (TEOre) te;
            return cte.getState();
        } else {
        	System.out.println("Error: te is NOT of instance TEOre at pos " + pos);
        }
        return state;
    }

    @Override
    protected BlockState createBlockState() {
        return new ExtendedBlockState(this, new IProperty[0], TEOre.properties);
    }



    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();

        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TEOre) {
        	TEOre teOre = (TEOre) te;
        	
        	ItemStack itemstack = new ItemStack(VCItems.stone, rand.nextInt(2));
            ItemStone.setRockType(itemstack, teOre.getRockType());          
            ret.add(itemstack);

            itemstack = new ItemStack(VCItems.ore, 1);
            ItemOre.setOreType(itemstack, teOre.getOreType());          
            ret.add(itemstack);
        } else {
        	System.out.println("tile entity is not of instance TEOre! D:");
        }
        
		return ret;
    }


}