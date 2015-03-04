package de.maxikg.cookiecraft.common.registry.registrator;

/**
 * @author maxikg <me@maxikg.de>
 */
public abstract class AbstractRegistrator implements Registrator {

    @Override
    public int getPriority() {
        return 0;
    }
}
