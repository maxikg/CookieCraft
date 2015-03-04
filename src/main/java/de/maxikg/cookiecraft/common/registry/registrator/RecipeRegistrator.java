package de.maxikg.cookiecraft.common.registry.registrator;

import de.maxikg.cookiecraft.common.model.Craftable;
import de.maxikg.cookiecraft.common.model.ModdedContent;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author maxikg <me@maxikg.de>
 */
public class RecipeRegistrator extends AbstractRegistrator {

    @Override
    public RegistratorResult handleRegistration(ModdedContent content) {
        if (!(content instanceof Craftable))
            return RegistratorResult.SKIPPED;

        RegistratorResult result = RegistratorResult.SKIPPED;

        for (IRecipe recipe : ((Craftable) content).getRecipes()) {
            if (recipe != null) {
                GameRegistry.addRecipe(recipe);
                result = RegistratorResult.ACCEPTED;
            }
        }

        return result;
    }
}
