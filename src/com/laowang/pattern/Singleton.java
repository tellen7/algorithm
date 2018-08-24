package com.laowang.pattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 只要继承这个类,并且子类的构造方法是protected，就可以通过这种方式创建单例
 * @author wangyonghao
 * @date 2018/8/24
 */
public class Singleton {

    private String name;

    protected Singleton(){this.name = "只有一个我";}

    public String getName(){
        return name;
    }

    private static Map<Class<? extends Singleton>, Singleton> map = new ConcurrentHashMap<>();

    public static <T extends Singleton> Singleton getInstance(Class<T> clazz){
        Singleton obj = map.get(clazz);
        if (obj != null){
            return obj;
        }else{
            // 可能有多个线程同时到达这里，为了不重复创建对象，同步块中还要在判断一下
            // 测试方法：去掉同步块里面的if判断，打开sleep代码块
            // 说   明：在创建对象前让线程休眠1秒，就能看到打印出来的hashcode不一样，创建了多个对象
            // 结果如下：
            // com.laowang.pattern.Singleton$SubA@53200150
            // com.laowang.pattern.Singleton$SubA@70463f82
            // com.laowang.pattern.Singleton$SubA@72d978a5
            // com.laowang.pattern.Singleton$SubA@42c8ccb8
            // com.laowang.pattern.Singleton$SubA@2e249d35
            // 所以 double-check 还是很有必要的
            // 注：这里不能用obj做双重检查，因为连编译器都告诉你它为null了
            synchronized (map){
                if ((map.get(clazz)) == null){
                    try {
                        //Thread.sleep(1000);
                        obj = clazz.newInstance();
                        map.put(clazz,obj);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return map.get(clazz);
        }
    }

    private static class SubA extends Singleton{
        /**因为父类通过newInstance创建对象，所以这个构造方法不能为private*/
        protected SubA(){super.name = "只有一个我SubA";}
    }
    private static class SubB extends Singleton{
        /**同SubA*/
        protected SubB(){super.name = "只有一个我SubB";}
    }

    /** 测试*/
    public static void main(String[] args) throws InterruptedException {
        //测试多线程
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                //获取到单例对象，打印hash值
                Singleton multi = Singleton.getInstance(SubA.class);
                System.out.println(multi);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();

        //等上面执行完了再测试是否可以这样获取到不同的子类的对象
        Singleton a = Singleton.getInstance(SubA.class);
        Singleton b = Singleton.getInstance(SubB.class);
        System.out.println("a-->name: "+a.getName());
        System.out.println("b-->name: "+b.getName());
        System.out.println(a);
        System.out.println(b);
    }
}
