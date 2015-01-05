/*
 * Copyright (c) 2014-2015, Arjuna Technologies Limited, Newcastle-upon-Tyne, England. All rights reserved.
 */

package com.arjuna.dbutilities.testsupport.dataflownodes.lifecycle;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.arjuna.databroker.data.jee.DataFlowNodeState;

public class DummyDataFlowNodeState implements DataFlowNodeState
{
    private static final Logger logger = Logger.getLogger(DummyDataFlowNodeState.class.getName());

    public DummyDataFlowNodeState(String id)
    {
        logger.log(Level.FINE, "DummyDataFlowNodeState: " + id);

        _id = id;
    }

    public String getId()
    {
        return _id;
    }

    @Override
    public Serializable getState()
    {
        return _state;
    }

    @Override
    public void setState(Serializable state)
    {
        _state = state;
    }

    private String       _id;
    private Serializable _state;
}
