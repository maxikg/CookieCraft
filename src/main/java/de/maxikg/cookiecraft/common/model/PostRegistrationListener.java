package de.maxikg.cookiecraft.common.model;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * @author maxikg <me@maxikg.de>
 */
public interface PostRegistrationListener {

    void postRegistration(FMLInitializationEvent event);
}
