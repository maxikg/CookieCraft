package de.maxikg.cookiecraft.common.registry;

import com.google.common.base.Preconditions;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Sets;
import de.maxikg.cookiecraft.common.model.ModdedContent;
import de.maxikg.cookiecraft.common.model.PostRegistrationListener;
import de.maxikg.cookiecraft.common.registry.exception.NoSuchModdedContentException;
import de.maxikg.cookiecraft.common.registry.registrator.Registrator;
import de.maxikg.cookiecraft.common.registry.registrator.RegistratorResult;
import de.maxikg.cookiecraft.common.registry.util.RegistratorComparator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

/**
 * @author maxikg <me@maxikg.de>
 */
public class ModdingRegistry {

    private final Set<Registrator> registrators = Sets.newTreeSet(new RegistratorComparator());
    private final ClassToInstanceMap<ModdedContent> contents = MutableClassToInstanceMap.create();
    private Logger logger = LogManager.getRootLogger();
    private volatile boolean locked = false;

    public ModdingRegistry addRegistrator(Registrator registrator) {
        if (locked)
            throw new UnsupportedOperationException("ModdingRegistry is already locked.");

        synchronized (registrators) {
            registrators.add(Preconditions.checkNotNull(registrator));
        }

        return this;
    }

    public ModdingRegistry addContent(ModdedContent content) {
        if (locked)
            throw new UnsupportedOperationException("ModdingRegistry is already locked.");

        Preconditions.checkNotNull(content);

        synchronized (contents) {
            contents.put(content.getClass(), content);
        }

        return this;
    }

    public void initLogger(Logger logger) {
        this.logger = Preconditions.checkNotNull(logger);
    }

    public void doRegistration(FMLInitializationEvent event) {
        locked = true;

        contentLoop:
        for (ModdedContent content : contents.values()) {
            boolean handled = false;

            for (Registrator registrator : registrators) {
                RegistratorResult result = registrator.handleRegistration(content);

                if (result == null) {
                    logger.error("Registrator {} returns null on content {}.", registrator.getClass().getName(), content.getClass().getName());
                    continue contentLoop;
                }

                switch (result) {
                    case ACCEPTED:
                        handled = true;
                        break;
                    case SKIPPED:
                        break;
                    case UNSUPPORTED:
                        logger.warn("Unsupported content: {}. Skipping further handlers.", content.getClass().getName());
                        continue contentLoop;
                    default:
                        logger.error("Unhandled RegistratorResult {}. This is a bug! Please report it.", result);
                }
            }

            if (!handled) {
                logger.warn("Content {} was not handled by any Registrator.", content.getClass().getName());
                continue;
            }

            if (content instanceof PostRegistrationListener)
                ((PostRegistrationListener) content).postRegistration(event);
        }
    }

    public <T extends ModdedContent> T load(Class<T> clazz) {
        Preconditions.checkNotNull(clazz);

        T instance = contents.getInstance(clazz);

        if (instance == null)
            throw new NoSuchModdedContentException("No such content: " + clazz.getName());

        return instance;
    }
}
