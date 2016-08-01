/*
 * Copyright (c) 2014-2016, Arjuna Technologies Limited, Newcastle-upon-Tyne, England. All rights reserved.
 */

package com.arjuna.dbutils.testsupport.dataflownodes.lifecycle;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.arjuna.databroker.data.DataFlow;
import com.arjuna.databroker.data.DataFlowNode;
import com.arjuna.databroker.data.core.DataFlowNodeLinkLifeCycleControl;
import com.arjuna.databroker.data.core.DataFlowNodeLinkManagementException;
import com.arjuna.databroker.data.core.NoCompatableCommonDataTransportTypeException;
import com.arjuna.databroker.data.core.NoCompatableCommonDataTypeException;

public class TestJEEDataFlowNodeLinkLifeCycleControl implements DataFlowNodeLinkLifeCycleControl
{
    private static final Logger logger = Logger.getLogger(TestJEEDataFlowNodeLinkLifeCycleControl.class.getName());

    @Override
    public <T> Boolean createDataFlowNodeLink(DataFlowNode sourceDataFlowNode, DataFlowNode sinkDataFlowNode, DataFlow dataFlow)
        throws DataFlowNodeLinkManagementException, NoCompatableCommonDataTypeException, NoCompatableCommonDataTransportTypeException
    {
        logger.log(Level.WARNING, "TestJEEDataFlowNodeLinkLifeCycleControl.createDataFlowNodeLink");

        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Boolean recreateDataFlowNodeLink(DataFlowNode sourceDataFlowNode, DataFlowNode sinkDataFlowNode, DataFlow dataFlow)
        throws DataFlowNodeLinkManagementException, NoCompatableCommonDataTypeException, NoCompatableCommonDataTransportTypeException
    {
        logger.log(Level.WARNING, "TestJEEDataFlowNodeLinkLifeCycleControl.recreateDataFlowNodeLink");

        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Boolean removeDataFlowNodeLink(DataFlowNode sourceDataFlowNode, DataFlowNode sinkDataFlowNode, DataFlow dataFlow)
        throws DataFlowNodeLinkManagementException, NoCompatableCommonDataTypeException, NoCompatableCommonDataTransportTypeException
    {
        logger.log(Level.WARNING, "TestJEEDataFlowNodeLinkLifeCycleControl.removeDataFlowNodeLink");

        throw new UnsupportedOperationException();
    }
}
