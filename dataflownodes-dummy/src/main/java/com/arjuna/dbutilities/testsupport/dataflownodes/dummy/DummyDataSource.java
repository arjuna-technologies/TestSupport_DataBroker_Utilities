/*
 * Copyright (c) 2014, Arjuna Technologies Limited, Newcastle-upon-Tyne, England. All rights reserved.
 */

package com.arjuna.dbutilities.testsupport.dataflownodes.dummy;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.arjuna.databroker.data.DataFlow;
import com.arjuna.databroker.data.DataProvider;
import com.arjuna.databroker.data.DataSource;
import com.arjuna.databroker.data.jee.DefaultObservableDataProvider;

public class DummyDataSource implements DataSource
{
    private static final Logger logger = Logger.getLogger(DummyDataSource.class.getName());

    public DummyDataSource(String name, Map<String, String> properties)
    {
        logger.log(Level.FINE, "DummyDataSource: " + name + ", " + properties);

        _name       = name;
        _properties = properties;

        _dataProvider = new DefaultObservableDataProvider<Object>(this);
        
        _receivedData = new LinkedList<Object>();
    }

    @Override
    public DataFlow getDataFlow()
    {
        logger.log(Level.FINE, "DummyDataSource.getDataFlow");

        return _dataFlow;
    }

    @Override
    public void setDataFlow(DataFlow dataFlow)
    {
        logger.log(Level.FINE, "DummyDataSource.setDataFlow");

        _dataFlow = dataFlow;
    }

    @Override
    public String getName()
    {
        logger.log(Level.FINE, "DummyDataSource.getName");

        return _name;
    }

    @Override
    public void setName(String name)
    {
        logger.log(Level.FINE, "DummyDataSource.setName");

        _name = name;
    }

    @Override
    public Map<String, String> getProperties()
    {
        logger.log(Level.FINE, "DummyDataSource.getProperties");

        return Collections.unmodifiableMap(_properties);
    }

    @Override
    public void setProperties(Map<String, String> properties)
    {
        logger.log(Level.FINE, "DummyDataSource.setProperties");

        _properties = properties;
    }

    @Override
    public Collection<Class<?>> getDataProviderDataClasses()
    {
        logger.log(Level.FINE, "DummyDataSource.getDataProviderDataClasses");

        Set<Class<?>> dataProviderDataClasses = new HashSet<Class<?>>();

        dataProviderDataClasses.add(Object.class);

        return dataProviderDataClasses;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> DataProvider<T> getDataProvider(Class<T> dataClass)
    {
        logger.log(Level.FINE, "DummyDataSource.getDataProvider");

        if (dataClass == Object.class)
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
    private DataProvider<Object> _dataProvider;

    private List<Object> _receivedData;
}