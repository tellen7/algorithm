package com.laowang.pattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author wangyonghao
 * @date 2018/8/24
 */
public class GenericsSingleton<T> {

    private static Map<Class,Object> map = new ConcurrentHashMap<>();

    private GenericsSingleton(){}

    public static<T> T getInstance(Class clazz){
        Object obj =map.get(clazz);
        if (obj != null){
            return (T)obj;
        }else{
            synchronized(map){
                if (map.get(clazz)==null){
                    try {
                        Constructor constructor = clazz.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        obj = constructor.newInstance();
                        map.put(clazz, obj);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
            return (T) obj;
        }
    }

    public static<T> void remove(Class<T> clazz){
        map.remove(clazz);
    }

    public static void main(String[] args) {
        Demo demo = GenericsSingleton.getInstance(Demo.class);
        System.out.println(demo.getName());

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                //获取到单例对象，打印hash值
                Demo multi = GenericsSingleton.getInstance(Demo.class);
                System.out.println(multi);
            }).start();
        }
    }

    public static class Demo{
        String name = "123456";
        private Demo(){}
        public String getName(){
            return name;
        }
    }
}
