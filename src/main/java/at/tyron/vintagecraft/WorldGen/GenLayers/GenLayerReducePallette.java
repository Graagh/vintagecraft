package at.tyron.vintagecraft.WorldGen.GenLayers;

import at.tyron.vintagecraft.WorldProperties.IGenLayerSupplier;

public class GenLayerReducePallette extends GenLayerVC {
	int quantity;
	IGenLayerSupplier []colorSupplier;

	public GenLayerReducePallette(long seed, int quantity, GenLayerVC parent) {
		this(seed, null, parent);
		this.quantity = quantity;
	}
	
	
	public GenLayerReducePallette(long seed, IGenLayerSupplier []colorSupplier, GenLayerVC parent) {
		super(seed);
		super.parent = parent;
		if (colorSupplier != null) {
			this.quantity = colorSupplier.length;
		}
		this.colorSupplier = colorSupplier;
	}

	
	@Override
	public int[] getInts(int xCoord, int zCoord, int xSize, int zSize) {
		int step = 255 / (quantity - 1);
		
		int[] inInts = this.parent.getInts(xCoord, zCoord, xSize, zSize);

		for (int i = 0; i < inInts.length; i++) {
			if (colorSupplier != null) {
				inInts[i] = colorSupplier[inInts[i] / step].getColor();
			} else {
				inInts[i] = (inInts[i] / step) * step;
			}
		}
		
		//layerRocks[this.nextInt(layerRocks.length)].id;
		
		return inInts;
	}

}
