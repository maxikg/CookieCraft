package de.maxikg.cookiecraft.world.gen;

import de.maxikg.cookiecraft.blocks.CookieOre;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * @author maxikg <me@maxikg.de>
 */
public class CookieWorldGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimensionId() == 0)
            generate(world, random, chunkX * 16, chunkZ * 16);
    }

    private void generate(World world, Random random, int chunkX, int chunkZ) {
        for (int i = 0; i < 8; i++) {
            int firstBlockXCoord = chunkX + random.nextInt(16);
            int firstBlockZCoord = chunkZ + random.nextInt(16);
            int y = random.nextInt(60);
            BlockPos pos = new BlockPos(firstBlockXCoord, y, firstBlockZCoord);

            new WorldGenMinable(CookieOre.INSTANCE.getDefaultState(), 14).generate(world, random, pos);
        }
    }
}
