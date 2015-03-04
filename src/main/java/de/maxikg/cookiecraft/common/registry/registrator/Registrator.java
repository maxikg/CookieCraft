package de.maxikg.cookiecraft.common.registry.registrator;

import de.maxikg.cookiecraft.common.model.ModdedContent;

/**
 * @author maxikg <me@maxikg.de>
 */
public interface Registrator {

    RegistratorResult handleRegistration(ModdedContent content);

    int getPriority();
}
