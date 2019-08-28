package spring.data;

import io.dtective.data.DataProvider;
import io.dtective.data.interfaces.IDataProviderService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GlobalInMemoryTests {

    private DataProvider provider;

    public GlobalInMemoryTests() {
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

    private IDataProviderService getData() {
        return provider.getGlobalDataService();
    }
}
