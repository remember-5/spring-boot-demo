package com.remember.demo.web.design.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjiahao
 * @date 2021/11/19
 */
@Component
public class StrategyUseService implements ApplicationContextAware {

    private Map<FileTypeResolveEnum,IFileStrategy> iFileStrategyMap = new ConcurrentHashMap<>();


    /**
     * 转化文件
     * @param fileTypeResolveEnum /
     * @param o /
     */
    public void resolveFile(FileTypeResolveEnum fileTypeResolveEnum,Object o){
        IFileStrategy iFileStrategy = iFileStrategyMap.get(fileTypeResolveEnum);
        if (iFileStrategy != null) {
            iFileStrategy.resolve(o);
        }
    }


    /**
     * 将不同策略放到map
     * @param applicationContext /
     * @throws BeansException /
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IFileStrategy> beansOfType = applicationContext.getBeansOfType(IFileStrategy.class);
        beansOfType.values().forEach(e-> iFileStrategyMap.put(e.getFileType(),e));
    }
}
