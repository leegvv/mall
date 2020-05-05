package net.arver.mall.security.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring工具类
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(final String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(final String name, final Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
