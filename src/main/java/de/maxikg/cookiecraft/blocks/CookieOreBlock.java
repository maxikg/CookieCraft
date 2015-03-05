package de.maxikg.cookiecraft.blocks;

import de.maxikg.cookiecraft.CookieCraft;
import de.maxikg.cookiecraft.common.model.ModdedContent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author maxikg <me@maxikg.de>
 */
public class CookieOreBlock extends BlockOre implements ModdedContent {

    public CookieOreBlock() {
        setHardness(1.75f);
        setStepSound(Block.soundTypeStone);
        setCreativeTab(CookieCraft.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 3);
    }

    @Override
    public String getName() {
        return "cookie_ore";
    }

    @Override
    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune) {
        return 1 + (world instanceof World ? ((World) world).rand : new Random()).nextInt(2);
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
