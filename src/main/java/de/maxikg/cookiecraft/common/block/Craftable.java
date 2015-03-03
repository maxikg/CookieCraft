package de.maxikg.cookiecraft.common.block;

import net.minecraft.item.crafting.IRecipe;

import java.util.Collection;

/**
 * @author maxikg <me@maxikg.de>
 */
public interface Craftable {

    Collection<IRecipe> getRecipes();
}
