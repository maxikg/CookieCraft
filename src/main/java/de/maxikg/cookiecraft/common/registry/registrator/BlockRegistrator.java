package de.maxikg.cookiecraft.common.registry.registrator;

import de.maxikg.cookiecraft.common.model.ModdedContent;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author maxikg <me@maxikg.de>
 */
public class BlockRegistrator extends AbstractRegistrator {

    @Override
    public RegistratorResult handleRegistration(ModdedContent content) {
        if (!(content instanceof Block))
            return RegistratorResult.SKIPPED;

        GameRegistry.registerBlock((Block) content, content.getName());

        return RegistratorResult.ACCEPTED;
    }

    @Override
    public int getPriority() {
        return 100;
    }
}
