package de.maxikg.cookiecraft.items;

import com.google.common.collect.ImmutableSet;
import de.maxikg.cookiecraft.CookieCraft;
import de.maxikg.cookiecraft.common.model.Craftable;
import de.maxikg.cookiecraft.common.model.ModdedContent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

import java.util.Collection;

/**
 * @author maxikg <me@maxikg.de>
 */
public class PestleItem extends Item implements ModdedContent, Craftable {

    public PestleItem() {
        setCreativeTab(CookieCraft.CREATIVE_TAB);
    }

    @Override
    public String getName() {
        return "pestle";
    }

    @Override
    public Collection<IRecipe> getRecipes() {
        return ImmutableSet.<IRecipe>of(
                new ShapedRecipes(1, 2, new ItemStack[]{
                        new ItemStack(Items.quartz),
                        new ItemStack(Items.quartz)
                }, new ItemStack(this)),
                new ShapedRecipes(2, 2, new ItemStack[] {
                        new ItemStack(Items.quartz),
                        null,
                        null,
                        new ItemStack(Items.quartz)
                }, new ItemStack(this)),
                new ShapedRecipes(2, 2, new ItemStack[]{
                        null,
                        new ItemStack(Items.quartz),
                        new ItemStack(Items.quartz),
                        null
                }, new ItemStack(this))
        );
    }
}
