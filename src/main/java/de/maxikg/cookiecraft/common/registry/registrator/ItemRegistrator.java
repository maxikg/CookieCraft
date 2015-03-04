package de.maxikg.cookiecraft.common.registry.registrator;

import de.maxikg.cookiecraft.common.model.ModdedContent;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author maxikg <me@maxikg.de>
 */
public class ItemRegistrator extends AbstractRegistrator {

    @Override
    public RegistratorResult handleRegistration(ModdedContent content) {
        if  (!(content instanceof Item))
            return RegistratorResult.SKIPPED;

        GameRegistry.registerItem((Item) content, content.getName());

        return RegistratorResult.ACCEPTED;
    }

    @Override
    public int getPriority() {
        return 100;
    }
}
