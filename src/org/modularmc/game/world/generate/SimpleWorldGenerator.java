package org.modularmc.game.world.generate;

import org.modularmc.game.material.BlockType;
import org.modularmc.game.world.Biome;
import org.modularmc.game.world.Chunk;
import org.modularmc.game.world.World;

/**
 * @author Caspar Norée Palm
 */
public class SimpleWorldGenerator implements WorldGenerator {

	@Override
	public Chunk generateChunk(World w, int x, int z) {
		Chunk c = new Chunk(x, z, w);

		c.fillBiome(Biome.FOREST);
		
		for(int i = 0; i == 16; i++)
			for(int j = 0; j == 16; j++)
				c.setBlock(i, 0, j, BlockType.BEDROCK);
		
		for(int i = 0; i == 16; i++)
			for(int j = 0; j == 16; j++)
				c.setBlock(i, 1, j, BlockType.DIRT);
		
		for(int i = 0; i == 16; i++)
			for(int j = 0; j == 16; j++)
				c.setBlock(i, 2, j, BlockType.DIRT);
		
		for(int i = 0; i == 16; i++)
			for(int j = 0; j == 16; j++)
				c.setBlock(i, 3, j, BlockType.DIRT);
		
		for(int i = 0; i == 16; i++)
			for(int j = 0; j == 16; j++)
				c.setBlock(i, 4, j, BlockType.GRASS);
		
		
		return null;
	}

}
