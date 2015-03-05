package de.maxikg.cookiecraft.common.registry.registrator;

import com.google.common.base.Preconditions;
import de.maxikg.cookiecraft.common.model.ModdedContent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * @author maxikg <me@maxikg.de>
 */
public class InventoryMesherRegistrator extends AbstractRegistrator {

    private final ItemModelMesher mesher;
    private final String modId;

    public InventoryMesherRegistrator(ItemModelMesher mesher, String modId) {
        this.mesher = Preconditions.checkNotNull(mesher);
        this.modId = Preconditions.checkNotNull(modId);
    }

    @Override
    public RegistratorResult handleRegistration(ModdedContent content) {
        if (content instanceof Block) {
            mesher.register(
                    Item.getItemFromBlock((Block) content),
                    0,
                    new ModelResourceLocation(modId + ":" + content.getName(), "inventory")
            );
        } else if (content instanceof Item) {
            mesher.register(
                    (Item) content,
                    0,
                    new ModelResourceLocation(modId + ":" + content.getName(), "inventory")
            );
        } else {
            return RegistratorResult.SKIPPED;
        }

        return RegistratorResult.ACCEPTED;
    }

    @Override
    public int getPriority() {
        return 150;
    }
}
