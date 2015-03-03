package de.maxikg.cookiecraft.blocks;

import de.maxikg.cookiecraft.CookieCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * @author maxikg <me@maxikg.de>
 */
public class CookieOre extends BlockOre {

    public static final CookieOre INSTANCE = new CookieOre();

    public CookieOre() {
        setHardness(1.75f);
        setStepSound(Block.soundTypeStone);
        setUnlocalizedName("cookie_ore");
        setCreativeTab(CookieCraft.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 3);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemById(357);
    }

    @Override
    public int quantityDropped(Random random) {
        return 2 + random.nextInt(4);
    }
}
