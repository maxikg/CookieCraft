package de.maxikg.cookiecraft;

import de.maxikg.cookiecraft.blocks.CookieBlock;
import de.maxikg.cookiecraft.blocks.CookieOreBlock;
import de.maxikg.cookiecraft.blocks.CookieSpringBlock;
import de.maxikg.cookiecraft.common.registry.ModdingRegistry;
import de.maxikg.cookiecraft.common.registry.registrator.*;
import de.maxikg.cookiecraft.items.GoldenCookieItem;
import de.maxikg.cookiecraft.world.generator.CookieWorldGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author maxikg <me@maxikg.de>
 */
@Mod(modid = CookieCraft.MOD_ID, name = "CookieCraft", version = "0.0.1")
public class CookieCraft {

    public static final String MOD_ID = "cookiecraft";
    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(CreativeTabs.getNextID(), "cookiecraft") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemById(357);
        }
    };
    private final ModdingRegistry registry = new ModdingRegistry();

    public CookieCraft() {
        registry.addRegistrator(new BlockRegistrator());
        registry.addRegistrator(new ItemRegistrator());
        registry.addRegistrator(new RecipeRegistrator());
        registry.addRegistrator(new UnlocalizedNameFixerRegistrator());

        registry.addContent(new CookieBlock());
        registry.addContent(new CookieOreBlock());
        registry.addContent(new CookieSpringBlock());

        registry.addContent(new GoldenCookieItem());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        registry.initLogger(e.getModLog());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        if (e.getSide() == Side.CLIENT)
            registry.addRegistrator(new InventoryMesherRegistrator(Minecraft.getMinecraft().getRenderItem().getItemModelMesher(), MOD_ID));

        registry.doRegistration(e);

        GameRegistry.registerWorldGenerator(new CookieWorldGenerator(registry), 2);
    }
}
