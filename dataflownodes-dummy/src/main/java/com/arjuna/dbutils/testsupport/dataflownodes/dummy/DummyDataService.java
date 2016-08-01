/*
 * Copyright (c) 2014-2016, Arjuna Technologies Limited, Newcastle-upon-Tyne, England. All rights reserved.
 */

package com.arjuna.dbutils.testsupport.dataflownodes.dummy;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.arjuna.databroker.data.DataConsumer;
import com.arjuna.databroker.data.DataFlow;
import com.arjuna.databroker.data.DataProvider;
import com.arjuna.databroker.data.DataService;
import com.arjuna.databroker.data.core.jee.DefaultObservableDataProvider;
import com.arjuna.databroker.data.core.jee.DefaultObserverDataConsumer;

public class DummyDataService implements DataService
{
    private static final Logger logger = Logger.getLogger(DummyDataService.class.getName());

    public DummyDataService()
    {
        logger.log(Level.FINE, "DummyDataService");
    }

    public DummyDataService(String name, Map<String, String> properties)
    {
        logger.log(Level.FINE, "DummyDataService: " + name + ", " + properties);

        _name       = name;
        _properties = properties;

        _dataConsumer = new DefaultObserverDataConsumer<Object>(this, "sendData", Object.class);
        _dataProvider = new DefaultObservableDataProvider<Object>(this);

        _receivedData = new LinkedList<Object>();
    }

    @Override
    public DataFlow getDataFlow()
    {
        logger.log(Level.FINE, "DummyDataService.getDataFlow");

        return _dataFlow;
    }

    @Override
    public void setDataFlow(DataFlow dataFlow)
    {
        logger.log(Level.FINE, "DummyDataService.setDataFlow");

        _dataFlow = dataFlow;
    }

    @Override
    public String getName()
    {
        logger.log(Level.FINE, "DummyDataService.getName");

        return _name;
    }

    @Override
    public void setName(String name)
    {
        logger.log(Level.FINE, "DummyDataService.setName");

        _name = name;
    }

    @Override
    public Map<String, String> getProperties()
    {
        logger.log(Level.FINE, "DummyDataService.getProperties");

        return Collections.unmodifiableMap(_properties);
    }

    @Override
    public void setProperties(Map<String, String> properties)
    {
        logger.log(Level.FINE, "DummyDataService.setProperties");

        _properties = properties;
    }

    @Override
    public Collection<Class<?>> getDataConsumerDataClasses()
    {
        logger.log(Level.FINE, "DummyDataService.getDataConsumerDataClasses");

        Set<Class<?>> dataConsumerDataClasses = new HashSet<Class<?>>();

        dataConsumerDataClasses.add(Object.class);

        return dataConsumerDataClasses;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> DataConsumer<T> getDataConsumer(Class<T> dataClass)
    {
        logger.log(Level.FINE, "DummyDataService.getDataConsumer");

        if (Object.class.isAssignableFrom(dataClass))
            return (DataConsumer<T>) _dataConsumer;
        else
            return null;
    }

    @Override
    public Collection<Class<?>> getDataProviderDataClasses()
    {
        logger.log(Level.FINE, "DummyDataService.getDataProviderDataClasses");

        Set<Class<?>> dataProviderDataClasses = new HashSet<Class<?>>();

        dataProviderDataClasses.add(Object.class);

        return dataProviderDataClasses;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> DataProvider<T> getDataProvider(Class<T> dataClass)
    {
        logger.log(Level.FINE, "DummyDataService.getDataProvider");

        if (Object.class.isAssignableFrom(dataClass))
            return (DataProvider<T>) _dataProvider;
        else
            return null;
    }

    public void sendData(Object data)
    {
        logger.log(Level.FINE, "DummyDataSource.sendData");

        _receivedData.add(data);

        _dataProvider.produce(data);
    }

    public List<Object> receivedData()
    {
        logger.log(Level.FINE, "DummyDataSource.receivedData");

        return _receivedData;
    }

    private DataFlow             _dataFlow;
    private String               _name;
    private Map<String, String>  _properties;
    private DataConsumer<Object> _dataConsumer;
    private DataProvider<Object> _dataProvider;

    private List<Object> _receivedData;
}