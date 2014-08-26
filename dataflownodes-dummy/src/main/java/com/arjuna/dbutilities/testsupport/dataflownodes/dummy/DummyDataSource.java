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
import org.risbic.intraconnect.basic.BasicDataProvider;
import com.arjuna.databroker.data.DataProvider;
import com.arjuna.databroker.data.DataSource;

public class DummyDataSource implements DataSource
{
    private static final Logger logger = Logger.getLogger(DummyDataSource.class.getName());

    public DummyDataSource(String name, Map<String, String> properties)
    {
        logger.log(Level.FINE, "DummyDataSource: " + name + ", " + properties);

        _name       = name;
        _properties = properties;

        _dataProvider = new BasicDataProvider<String>(this);
        
        _receivedData = new LinkedList<String>();
    }

    @Override
    public String getName()
    {
        logger.log(Level.FINE, "DummyDataSource.getName");

        return _name;
    }

    @Override
    public Map<String, String> getProperties()
    {
        logger.log(Level.FINE, "DummyDataSource.getProperties");

        return Collections.unmodifiableMap(_properties);
    }

    @Override
    public Collection<Class<?>> getDataProviderDataClasses()
    {
        logger.log(Level.FINE, "DummyDataSource.getDataProviderDataClasses");

        Set<Class<?>> dataProviderDataClasses = new HashSet<Class<?>>();

        dataProviderDataClasses.add(String.class);

        return dataProviderDataClasses;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> DataProvider<T> getDataProvider(Class<T> dataClass)
    {
        logger.log(Level.FINE, "DummyDataSource.getDataProvider");

        if (dataClass == String.class)
            return (DataProvider<T>) _dataProvider;
        else
            return null;
    }

    public void sendData(String data)
    {
        logger.log(Level.FINE, "DummyDataSource.sendData");

        _receivedData.add(data);

        _dataProvider.produce(data);
    }

    public List<String> receivedData()
    {
        logger.log(Level.FINE, "DummyDataSource.receivedData");

        return _receivedData;
    }

    private String               _name;
    private Map<String, String>  _properties;
    private DataProvider<String> _dataProvider;

    private List<String> _receivedData;
}