package de.maxikg.cookiecraft;

import de.maxikg.cookiecraft.blocks.CookieBlock;
import de.maxikg.cookiecraft.blocks.CookieOre;
import de.maxikg.cookiecraft.common.block.BlockRegistry;
import de.maxikg.cookiecraft.world.gen.CookieWorldGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

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
    private final BlockRegistry blockRegistry = new BlockRegistry(CookieCraft.MOD_ID);
    private Logger logger;

    public CookieCraft() {
        blockRegistry
                .addBlock(CookieBlock.INSTANCE)
                .addBlock(CookieOre.INSTANCE);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        logger.info("Registered {} blocks.", blockRegistry.registerBlocks(e));

        GameRegistry.registerWorldGenerator(new CookieWorldGenerator(), 2);
    }
}
