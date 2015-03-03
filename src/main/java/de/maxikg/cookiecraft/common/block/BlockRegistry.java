package de.maxikg.cookiecraft.common.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import de.maxikg.cookiecraft.common.item.Craftable;
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

    public int registerBlocks(FMLInitializationEvent e) {
        ItemModelMesher mesher = e.getSide() == Side.CLIENT ? Minecraft.getMinecraft().getRenderItem().getItemModelMesher() : null;
        int counter = 0;

        for (T block : ImmutableList.copyOf(blocks)) {
            String internalName = block.getName();

            if (block.getUnlocalizedName() == null)
                block.setUnlocalizedName(internalName);

            GameRegistry.registerBlock(block, internalName);

            block.postRegisterBlock(e);

            if (block instanceof Craftable)
                registerRecipes((Craftable) block);

            if (mesher != null) {
                mesher.register(
                        Item.getItemFromBlock(block),
                        0,
                        new ModelResourceLocation(modId + ":" + internalName, "inventory")
                );
            }

            counter++;
        }

        return counter;
    }

    private void registerRecipes(Craftable craftable) {
        for (IRecipe recipe : craftable.getRecipes())
            GameRegistry.addRecipe(recipe);
    }
}
