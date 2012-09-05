/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bridgedb.metadata;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.bridgedb.linkset.constants.DctermsConstants;
import org.bridgedb.linkset.constants.FoafConstants;
import org.bridgedb.linkset.constants.PavConstants;
import org.bridgedb.linkset.constants.RdfConstants;
import org.bridgedb.linkset.constants.VoidConstants;
import org.openrdf.model.Resource;
import org.openrdf.model.URI;

/**
 *
 * @author Christian
 */
public class DescriptionMetaData extends CollectionMetaData{
    
    public static URI RESOURCE_TYPE = VoidConstants.DATASET_DESCRIPTION;
    
    public DescriptionMetaData(Resource id, RDFData input){
        super(id, input);
    }
    
    public DescriptionMetaData(RDFData input){
        super(input);
    }

    @Override
    void setupValues() {
        values.add(new SingletonValue("Title", DctermsConstants.TITLE, String.class, RequirementLevel.MAY));
        values.add(new SingletonValue("Description", DctermsConstants.DESCRIPTION, String.class, RequirementLevel.MAY));
        values.add(new SingletonValue("Created By", PavConstants.CREATED_BY, String.class, RequirementLevel.MAY));
        values.add(new SingletonValue("Created On", PavConstants.CREATED_ON, Date.class, RequirementLevel.MAY));
        values.add(new SingletonValue("Primary Topic", FoafConstants.PRIMARY_TOPIC, String.class, RequirementLevel.MAY));
    }

    @Override
    URI getResourceType() {
        return RESOURCE_TYPE;
    }

    @Override
    void readFromInput(RDFData input) {
        super.readFromInput(input);
        Set<MetaData> collection = getCollection();
        Resource linksetId = getByPredicateObject(input,  RdfConstants.TYPE_URI, LinkSetMetaData.RESOURCE_TYPE);
        while (linksetId != null){
            collection.add(new LinkSetMetaData(linksetId, input));
            linksetId = getByPredicateObject(input,  RdfConstants.TYPE_URI, LinkSetMetaData.RESOURCE_TYPE);
        }
        Resource datasetId = getByPredicateObject(input,  RdfConstants.TYPE_URI, DataSetMetaData.RESOURCE_TYPE);
        while (datasetId != null){
            collection.add(new DataSetMetaData(datasetId, input));
            datasetId = getByPredicateObject(input,  RdfConstants.TYPE_URI, DataSetMetaData.RESOURCE_TYPE);
        }
    }
    
}