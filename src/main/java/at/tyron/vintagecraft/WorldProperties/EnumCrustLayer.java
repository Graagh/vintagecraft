package at.tyron.vintagecraft.WorldProperties;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import at.tyron.vintagecraft.World.BlocksVC;
import at.tyron.vintagecraft.World.VCBiome;
import at.tyron.vintagecraft.block.BlockTopSoil;
import at.tyron.vintagecraft.block.BlockVC;

public enum EnumCrustLayer {
	TOPSOIL (-1, 0, -999),
	SUBSOIL (-1, 1, 0),
	REGOLITH(-1, 2, 1),
	ROCK_1   (0, -1, -1),
	ROCK_2   (1, -1, -1),
	ROCK_3   (2, -1, -1),
	ROCK_4   (3, -1, -1),
	ROCK_5   (4, -1, -1),
	ROCK_6   (5, -1, -1),
	ROCK_7   (6, -1, -1),
	
	;
	
	
	public final int dataLayerIndex;
	public final int depthStart;
	public final int underwaterDepthStart;
	
	private EnumCrustLayer(int index, int depthStart, int underwaterDepthStart) {
		this.dataLayerIndex = index;
		this.depthStart = depthStart;
		this.underwaterDepthStart = underwaterDepthStart;
	}
	
	
	public static EnumCrustLayer fromDataLayerIndex(int datalayerindex) {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].dataLayerIndex == datalayerindex) return values()[i];
		}
		return null;
	}
	
	
	public IBlockState getFixedBlock(EnumRockType rocktype, int depth) {
		switch (this) {
			case TOPSOIL: return depth > 0 ? EnumOrganicLayer.None.getTopSoilVariantForBlock(BlocksVC.topsoil) : EnumOrganicLayer.NormalGrass.getTopSoilVariantForBlock(BlocksVC.topsoil);
			case SUBSOIL: return rocktype.getRockVariantForBlock(BlocksVC.subsoil);
			case REGOLITH: return rocktype.getRockVariantForBlock(BlocksVC.regolith);
			default: return null;
		}
	}
	
	public static EnumCrustLayer crustLayerForDepth(int depth, boolean underwater) {
		EnumCrustLayer curlayer, layer = null;
		for (int i = 0; i < values().length; i++) {
			curlayer = values()[i];
			int depthStart = underwater ? curlayer.underwaterDepthStart : curlayer.depthStart;
			
			if (depth >= depthStart) layer = curlayer;
		}
		return layer;
	}
	

	
}
