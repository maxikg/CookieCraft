package de.maxikg.cookiecraft.blocks;

import de.maxikg.cookiecraft.CookieCraft;
import de.maxikg.cookiecraft.common.model.Craftable;
import de.maxikg.cookiecraft.common.model.ModdedContent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
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
public class CookieBlock extends Block implements Craftable, ModdedContent {

    public CookieBlock() {
        super(Material.rock);

        setHardness(1.5f);
        setStepSound(Block.soundTypeStone);
        setCreativeTab(CookieCraft.CREATIVE_TAB);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public String getName() {
        return "cookie_block";
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.cookie;
    }

    @Override
    public int quantityDropped(Random random) {
        return 9;
    }

    @Override
    public Collection<IRecipe> getRecipes() {
        return Collections.<IRecipe>singleton(new ShapedRecipes(3, 3, new ItemStack[] {
                new ItemStack(Items.cookie),
                new ItemStack(Items.cookie),
                new ItemStack(Items.cookie),
                new ItemStack(Items.cookie),
                new ItemStack(Items.cookie),
                new ItemStack(Items.cookie),
                new ItemStack(Items.cookie),
                new ItemStack(Items.cookie),
                new ItemStack(Items.cookie)
        }, new ItemStack(this)));
    }
}
