package de.maxikg.cookiecraft.items;

import de.maxikg.cookiecraft.CookieCraft;
import de.maxikg.cookiecraft.common.model.Craftable;
import de.maxikg.cookiecraft.common.model.ModdedContent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

import java.util.Collection;
import java.util.Collections;

/**
 * @author maxikg <me@maxikg.de>
 */
public class GoldenCookieItem extends ItemFood implements ModdedContent, Craftable {

    public GoldenCookieItem() {
        super(9, 0.9F, false);

        setCreativeTab(CookieCraft.CREATIVE_TAB);
    }

    @Override
    public String getName() {
        return "golden_cookie";
    }

    @Override
    public Collection<IRecipe> getRecipes() {
        Item cookie = Item.getItemById(357);

        return Collections.<IRecipe>singleton(new ShapedRecipes(3, 3, new ItemStack[] {
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(Item.getItemById(266)),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie)
        }, new ItemStack(this)));
    }
}
