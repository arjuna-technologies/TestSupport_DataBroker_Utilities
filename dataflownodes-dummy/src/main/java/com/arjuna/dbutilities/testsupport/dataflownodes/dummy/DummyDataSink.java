/*
 * Copyright (c) 2014-2015, Arjuna Technologies Limited, Newcastle-upon-Tyne, England. All rights reserved.
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
import com.arjuna.databroker.data.DataConsumer;
import com.arjuna.databroker.data.DataFlow;
import com.arjuna.databroker.data.DataProvider;
import com.arjuna.databroker.data.DataSink;
import com.arjuna.databroker.data.core.jee.DefaultObservableDataProvider;
import com.arjuna.databroker.data.core.jee.DefaultObserverDataConsumer;

public class DummyDataSink implements DataSink
{
    private static final Logger logger = Logger.getLogger(DummyDataSink.class.getName());

    public DummyDataSink()
    {
        logger.log(Level.FINE, "DummyDataSink");
    }

    public DummyDataSink(String name, Map<String, String> properties)
    {
        logger.log(Level.FINE, "DummyDataSink: " + name + ", " + properties);

        _name       = name;
        _properties = properties;

        _dataConsumer = new DefaultObserverDataConsumer<Object>(this, "sendData", Object.class);
        _dataProvider = new DefaultObservableDataProvider<Object>(this);

        _receivedData = new LinkedList<Object>();
    }

    @Override
    public DataFlow getDataFlow()
    {
        logger.log(Level.FINE, "DummyDataSink.getDataFlow");

        return _dataFlow;
    }

    @Override
    public void setDataFlow(DataFlow dataFlow)
    {
        logger.log(Level.FINE, "DummyDataSink.setDataFlow");

        _dataFlow = dataFlow;
    }

    @Override
    public String getName()
    {
        logger.log(Level.FINE, "DummyDataSink.getName");

        return _name;
    }

    @Override
    public void setName(String name)
    {
        logger.log(Level.FINE, "DummyDataSink.setName");

        _name = name;
    }

    @Override
    public Map<String, String> getProperties()
    {
        logger.log(Level.FINE, "DummyDataSink.getProperties");

        return Collections.unmodifiableMap(_properties);
    }

    @Override
    public void setProperties(Map<String, String> properties)
    {
        logger.log(Level.FINE, "DummyDataSink.setProperties");

        _properties = properties;
    }

    @Override
    public Collection<Class<?>> getDataConsumerDataClasses()
    {
        logger.log(Level.FINE, "DummyDataSink.getDataConsumerDataClasses");

        Set<Class<?>> dataConsumerDataClasses = new HashSet<Class<?>>();

        dataConsumerDataClasses.add(Object.class);

        return dataConsumerDataClasses;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> DataConsumer<T> getDataConsumer(Class<T> dataClass)
    {
        logger.log(Level.FINE, "DummyDataSink.getDataConsumer");

        if (Object.class.isAssignableFrom(dataClass))
            return (DataConsumer<T>) _dataConsumer;
        else
            return null;
    }

    public void sendData(Object data)
    {
        logger.log(Level.FINE, "DummyDataSink.sendData");

        _receivedData.add(data);

        _dataProvider.produce(data);
    }

    public List<Object> receivedData()
    {
        logger.log(Level.FINE, "DummyDataSink.receivedData");

    	return _receivedData;
    }

    private DataFlow             _dataFlow;
    private String               _name;
    private Map<String, String>  _properties;
    private DataConsumer<Object> _dataConsumer;
    private DataProvider<Object> _dataProvider;

    private List<Object> _receivedData;
}