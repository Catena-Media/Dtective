package spring.data;

import io.dtective.data.DataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataProviderTests {
    @Test
    public void whenGetBeans_returnsBean() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("./Beans.xml");
        DataProvider indexApp = applicationContext.getBean(
                "DataProvider", DataProvider.class);

        Assert.assertNotNull(indexApp);
        Assert.assertNotNull(indexApp.getLocalDataService());
        Assert.assertNotNull(indexApp.getGlobalDataService());
    }
}
