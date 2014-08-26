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
import org.risbic.intraconnect.basic.BasicDataConsumer;
import org.risbic.intraconnect.basic.BasicDataProvider;
import com.arjuna.databroker.data.DataConsumer;
import com.arjuna.databroker.data.DataProvider;
import com.arjuna.databroker.data.DataStore;

public class DummyDataStore implements DataStore
{
    private static final Logger logger = Logger.getLogger(DummyDataStore.class.getName());

    public DummyDataStore(String name, Map<String, String> properties)
    {
        logger.log(Level.FINE, "DummyDataStore: " + name + ", " + properties);

        _name       = name;
        _properties = properties;

        _dataConsumer = new BasicDataConsumer<String>(this, "sendData", String.class);
        _dataProvider = new BasicDataProvider<String>(this);
        
        _receivedData = new LinkedList<String>();
    }

    @Override
    public String getName()
    {
        logger.log(Level.FINE, "DummyDataStore.getName");

        return _name;
    }

    @Override
    public Map<String, String> getProperties()
    {
        logger.log(Level.FINE, "DummyDataStore.getProperties");

        return Collections.unmodifiableMap(_properties);
    }

    @Override
    public Collection<Class<?>> getDataConsumerDataClasses()
    {
        logger.log(Level.FINE, "DummyDataStore.getDataConsumerDataClasses");

        Set<Class<?>> dataConsumerDataClasses = new HashSet<Class<?>>();

        dataConsumerDataClasses.add(String.class);

        return dataConsumerDataClasses;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> DataConsumer<T> getDataConsumer(Class<T> dataClass)
    {
        logger.log(Level.FINE, "DummyDataStore.getDataConsumer");

        if (dataClass == String.class)
            return (DataConsumer<T>) _dataConsumer;
        else
            return null;
    }

    @Override
    public Collection<Class<?>> getDataProviderDataClasses()
    {
        logger.log(Level.FINE, "DummyDataStore.getDataProviderDataClasses");

        Set<Class<?>> dataProviderDataClasses = new HashSet<Class<?>>();

        dataProviderDataClasses.add(String.class);

        return dataProviderDataClasses;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> DataProvider<T> getDataProvider(Class<T> dataClass)
    {
        logger.log(Level.FINE, "DummyDataStore.getDataProvider");

        if (dataClass == String.class)
            return (DataProvider<T>) _dataProvider;
        else
            return null;
    }

    public void sendData(String data)
    {
        logger.log(Level.FINE, "DummyDataStore.sendData");

        _receivedData.add(data);

        _dataProvider.produce(data);
    }

    public List<String> receivedData()
    {
        logger.log(Level.FINE, "DummyDataStore.receivedData");

        return _receivedData;
    }

    private String               _name;
    private Map<String, String>  _properties;
    private DataConsumer<String> _dataConsumer;
    private DataProvider<String> _dataProvider;

    private List<String> _receivedData;
}