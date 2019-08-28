package spring.data;

import io.dtective.data.DataProvider;
import io.dtective.data.interfaces.IDataProviderService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LocalInMemoryTests {

    private static final Thread THREAD_NONSTATIC1 = new Thread(() -> {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("./Beans.xml");
        DataProvider provider = applicationContext.getBean("DataProvider", DataProvider.class);

        String key = "test1";
        String value = "test1";

        provider.getLocalDataService().put(key, value);

        Assert.assertTrue(provider.getLocalDataService().containsKey(key));
        Assert.assertTrue(provider.getLocalDataService().containsValue(value));
        Assert.assertTrue(provider.getLocalDataService().get(key).equals(value));

        System.out.println(Thread.currentThread().getName() + " --> " + System.currentTimeMillis());
    });
    private static final Thread THREAD_NONSTATIC2 = new Thread(() -> {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("./Beans.xml");
        DataProvider provider = applicationContext.getBean("DataProvider", DataProvider.class);

        String key = "test2";
        String value = "test2";

        provider.getLocalDataService().put(key, value);

        Assert.assertTrue(provider.getLocalDataService().containsKey(key));
        Assert.assertTrue(provider.getLocalDataService().containsValue(value));
        Assert.assertTrue(provider.getLocalDataService().get(key).equals(value));

        System.out.println(Thread.currentThread().getName() + " --> " + System.currentTimeMillis());
    });
    private static DataProvider provider;
    private static final Thread THREAD_STATIC1 = new Thread(() -> {
        String key = "test1";
        String value = "test1";

        provider.getLocalDataService().put(key, value);

        Assert.assertTrue(provider.getLocalDataService().containsKey(key));
        Assert.assertTrue(provider.getLocalDataService().containsValue(value));
        Assert.assertTrue(provider.getLocalDataService().get(key).equals(value));

        System.out.println(Thread.currentThread().getName() + " --> " + System.currentTimeMillis());
    });

    private static final Thread THREAD_STATIC2 = new Thread(() -> {
        String key = "test2";
        String value = "test2";

        provider.getLocalDataService().put(key, value);

        Assert.assertTrue(provider.getLocalDataService().containsKey(key));
        Assert.assertTrue(provider.getLocalDataService().containsValue(value));
        Assert.assertTrue(provider.getLocalDataService().get(key).equals(value));

        System.out.println(Thread.currentThread().getName() + " --> " + System.currentTimeMillis());
    });


    public LocalInMemoryTests() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("./Beans.xml");
        provider = applicationContext.getBean("DataProvider", DataProvider.class);
    }

    @Test
    public void whenGetBeans_returnsBean() {

        String key = "test";
        String value = "test";

        getData().put(key, value);

        Assert.assertTrue(getData().containsKey(key));
        Assert.assertTrue(getData().containsValue(value));
        Assert.assertTrue(getData().get(key).equals(value));

    }

    @Test
    public void test_threads() {

        ExecutorService staticExecutorService = Executors.newCachedThreadPool();

        staticExecutorService.submit(THREAD_STATIC1);
        staticExecutorService.submit(THREAD_STATIC1);
        staticExecutorService.submit(THREAD_STATIC2);
        staticExecutorService.submit(THREAD_STATIC2);

        try {
            staticExecutorService.shutdown();
            staticExecutorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_nonstatic_threads() {

        ExecutorService staticExecutorService = Executors.newCachedThreadPool();

        staticExecutorService.submit(THREAD_NONSTATIC1);
        staticExecutorService.submit(THREAD_NONSTATIC1);
        staticExecutorService.submit(THREAD_NONSTATIC2);
        staticExecutorService.submit(THREAD_NONSTATIC2);

        try {
            staticExecutorService.shutdown();
            staticExecutorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private IDataProviderService getData() {
        return provider.getLocalDataService();
    }
}
