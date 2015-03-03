package de.maxikg.cookiecraft.blocks;

import de.maxikg.cookiecraft.CookieCraft;
import de.maxikg.cookiecraft.common.block.AbstractBlock;
import de.maxikg.cookiecraft.common.item.Craftable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * @author maxikg <me@maxikg.de>
 */
public class CookieBlock extends AbstractBlock implements Craftable {

    public static final CookieBlock INSTANCE = new CookieBlock();

    public CookieBlock() {
        super("cookie_block", Material.rock);

        setHardness(1.5f);
        setStepSound(Block.soundTypeStone);
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

    @Override
    public Collection<IRecipe> getRecipes() {
        Item cookie = Item.getItemById(357);

        return Collections.<IRecipe>singleton(new ShapedRecipes(3, 3, new ItemStack[] {
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie)
        }, new ItemStack(this)));
    }
}
