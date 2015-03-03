package de.maxikg.cookiecraft.common.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

/**
 * @author maxikg <me@maxikg.de>
 */
public class BlockRegistry<T extends Block & ModBlock> {

    private final List<T> blocks = Lists.newArrayList();
    private final String modId;

    public BlockRegistry(String modId) {
        this.modId = modId;
    }

    public <U extends T> BlockRegistry<T> addBlock(U block) {
        synchronized (blocks) {
            blocks.add(block);
        }

        return this;
    }

    public void registerBlocks(FMLInitializationEvent e) {
        ItemModelMesher mesher = e.getSide() == Side.CLIENT ? Minecraft.getMinecraft().getRenderItem().getItemModelMesher() : null;

        for (T block : ImmutableList.copyOf(blocks)) {
            String internalName = block.getName();

            GameRegistry.registerBlock(block, internalName);

            if (block instanceof Craftable)
                registerRecipes((Craftable) block);

            if (mesher != null)
                mesher.register(
                        Item.getItemFromBlock(block),
                        0,
                        new ModelResourceLocation(modId + ":" + internalName, "inventory")
                );
        }
    }

    private void registerRecipes(Craftable craftable) {
        for (IRecipe recipe : craftable.getRecipes())
            GameRegistry.addRecipe(recipe);
    }
}
