package plugin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation that is used to mark disabled plugins so they will be
 * ignored by {@link PluginLoader}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisabledPlugin {
}
