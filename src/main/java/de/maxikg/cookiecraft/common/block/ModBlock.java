package de.maxikg.cookiecraft.common.block;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * @author maxikg <me@maxikg.de>
 */
public interface ModBlock {

    String getName();

    void postRegisterBlock(FMLInitializationEvent e);
}
