package de.maxikg.cookiecraft.common.registry.registrator;

import de.maxikg.cookiecraft.common.model.ModdedContent;

import java.lang.reflect.Method;

/**
 * @author maxikg <me@maxikg.de>
 */
public class UnlocalizedNameFixerRegistrator extends AbstractRegistrator {

    @Override
    public RegistratorResult handleRegistration(ModdedContent content) {
        Class<?> clazz = content.getClass();

        try {
            Method getter = clazz.getMethod("getUnlocalizedName");
            Method setter = clazz.getMethod("setUnlocalizedName", String.class);
            Object result = getter.invoke(content);

            if (result == null || (result instanceof String && ((String) result).endsWith(".null"))) {
                setter.invoke(content, content.getName());

                return RegistratorResult.ACCEPTED;
            }
        } catch (NoSuchMethodException e) {
            return RegistratorResult.SKIPPED;
        } catch (ReflectiveOperationException e) {
            return RegistratorResult.UNSUPPORTED;
        }

        return RegistratorResult.SKIPPED;
    }

    @Override
    public int getPriority() {
        return 150;
    }
}
