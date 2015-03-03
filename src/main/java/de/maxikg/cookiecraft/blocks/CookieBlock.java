package de.maxikg.cookiecraft.blocks;

import de.maxikg.cookiecraft.CookieCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * @author maxikg <me@maxikg.de>
 */
public class CookieBlock extends Block {

    public static final CookieBlock INSTANCE = new CookieBlock();

    public CookieBlock() {
        super(Material.rock);

        setHardness(1.5f);
        setStepSound(Block.soundTypeStone);
        setUnlocalizedName("cookie_block");
        setCreativeTab(CookieCraft.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 3);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemById(357);
    }

    @Override
    public int quantityDropped(Random random) {
        return 9;
    }
}
