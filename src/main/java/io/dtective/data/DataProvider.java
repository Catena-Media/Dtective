package io.dtective.data;

import io.dtective.data.interfaces.IDataProviderService;

public class DataProvider {

    private IDataProviderService localDataService;
    private IDataProviderService globalDataService;
    private IDataProviderService configurationDataService;

    public DataProvider(IDataProviderService localDataService,
                        IDataProviderService globalDataService,
                        IDataProviderService configurationDataService) {
        this.localDataService = localDataService;
        this.globalDataService = globalDataService;
        this.configurationDataService = configurationDataService;
    }

    public IDataProviderService getLocalDataService() {
        return localDataService;
    }

    public IDataProviderService getGlobalDataService() {
        return globalDataService;
    }

    public IDataProviderService getConfigurationDataService() {
        return configurationDataService;
    }
}
