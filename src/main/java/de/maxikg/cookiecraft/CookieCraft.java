package de.maxikg.cookiecraft;

import de.maxikg.cookiecraft.blocks.CookieBlock;
import de.maxikg.cookiecraft.blocks.CookieOre;
import de.maxikg.cookiecraft.world.gen.CookieWorldGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author maxikg <me@maxikg.de>
 */
@Mod(modid = "cookiecraft", name = "CookieCraft", version = "0.0.1")
public class CookieCraft {

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(CreativeTabs.getNextID(), "cookiecraft") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemById(357);
        }
    };

    @Mod.EventHandler
    public void init(@SuppressWarnings("unused") FMLInitializationEvent e) {
        GameRegistry.registerBlock(CookieBlock.INSTANCE, "cookie_block");
        Item cookie = Item.getItemById(357);
        GameRegistry.addRecipe(new ShapedRecipes(3, 3, new ItemStack[] {
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie),
                new ItemStack(cookie)
        }, new ItemStack(CookieBlock.INSTANCE)));

        GameRegistry.registerBlock(CookieOre.INSTANCE, "cookie_ore");
        GameRegistry.registerWorldGenerator(new CookieWorldGenerator(), 2);

        if (e.getSide() == Side.CLIENT) {
            ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
            mesher.register(
                    Item.getItemFromBlock(CookieBlock.INSTANCE),
                    0,
                    new ModelResourceLocation("cookiecraft:cookie_block", "inventory")
            );
            mesher.register(
                    Item.getItemFromBlock(CookieOre.INSTANCE),
                    0,
                    new ModelResourceLocation("cookiecraft:cookie_ore", "inventory")
            );
        }
    }
}
