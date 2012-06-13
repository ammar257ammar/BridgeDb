/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bridgedb.ws.client;

import org.bridgedb.IDMapperException;
import org.bridgedb.ws.WSCoreClientFactory;
import org.bridgedb.ws.WSCoreInterface;
import org.bridgedb.ws.WSCoreMapper;
import org.junit.BeforeClass;

/**
 *
 * @author Christian
 */
public class IDMapperTest  extends org.bridgedb.IDMapperTest{
    
    @BeforeClass
    public static void setupIDMapper() throws IDMapperException {
        connectionOk = false;
        WSCoreInterface webService = WSCoreClientFactory.createTestWSClient();
        connectionOk = true;
        idMapper = new WSCoreMapper(webService);
    }

}