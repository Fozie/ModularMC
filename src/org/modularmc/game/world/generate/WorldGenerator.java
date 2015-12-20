package org.modularmc.game.world.generate;

import org.modularmc.game.world.Chunk;
import org.modularmc.game.world.World;

/**
 * @author Caspar Norée Palm
 */
public interface WorldGenerator {
	public Chunk generateChunk(World w, int x, int z);
}
