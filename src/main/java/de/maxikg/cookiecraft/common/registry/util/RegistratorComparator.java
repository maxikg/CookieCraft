package de.maxikg.cookiecraft.common.registry.util;

import de.maxikg.cookiecraft.common.registry.registrator.Registrator;

import java.util.Comparator;

/**
 * @author maxikg <me@maxikg.de>
 */
public class RegistratorComparator implements Comparator<Registrator> {

    @Override
    public int compare(Registrator o1, Registrator o2) {
        int difference = o1.getPriority() - o2.getPriority();

        return difference != 0 ? difference : -1;
    }
}
