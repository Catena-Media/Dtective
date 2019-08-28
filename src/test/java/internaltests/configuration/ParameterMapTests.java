package internaltests.configuration;


import io.dtective.configuration.ParameterMap;
import org.junit.Assert;
import org.junit.Test;

public class ParameterMapTests {

    @Test
    public void configurationIsSingleInstance() {

        ParameterMap.setParamIsSingleInstance("false");

        boolean isSingleInstance = true;
        ParameterMap.setParamIsSingleInstance("" + isSingleInstance);
        Assert.assertEquals(isSingleInstance, ParameterMap.getParamIsSingleInstance());
    }

    @Test
    public void configurationErrorIsSingleInstance() {

        String invalidValue = "Yes";

        ParameterMap.setParamIsSingleInstance(invalidValue);

        try {
            boolean value = ParameterMap.getParamIsSingleInstance();
            Assert.fail("Did not encounter exception on invalid configuration value");

        } catch (IllegalArgumentException ex) {

            Assert.assertTrue(ex.getMessage().contains(
                    "Configuration value <" + invalidValue + "> is not acceptable for IsSingleInstance."));
            Assert.assertTrue(ex.getMessage().contains("Required is < true / false >"));
        }


    }
}
