/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bridgedb.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.bridgedb.DataSource;
import org.bridgedb.IDMapperException;
import org.bridgedb.Xref;
import org.bridgedb.ops.OpsMapper;

/**
 *
 * @author Christian
 */
public class ByPositionXrefIterator implements Iterator<Xref>, Iterable<Xref>{

    private Xref bufferedNext;
    private OpsMapper opsMapper;  
    private ArrayList<DataSource> dataSources;
    int position;
    
    //Statics for easy readability of method calls
    private static final ArrayList<String> ALL_PROVENANCE_IDS = new ArrayList<String>();
    
    public ByPositionXrefIterator (OpsMapper opsMapper, DataSource dataSource){
        this(opsMapper);
        dataSources.add(dataSource);
    }
    
    public ByPositionXrefIterator (OpsMapper opsMapper){
        this.opsMapper = opsMapper;
        dataSources = new ArrayList<DataSource>();
        position = -1;
    }
   
    @Override
    public boolean hasNext() {
        try {
            bufferedNext = getNext();
            return (bufferedNext != null);
        } catch (IDMapperException ex) {
            return false;
        }
    }

    private Xref getNext() throws IDMapperException{
        position ++;
        List<Xref> list = opsMapper.getXrefs(dataSources, ALL_PROVENANCE_IDS, position, 1);
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    
    @Override
    public Xref next() {
        Xref result;
        if (bufferedNext == null){
            try {
                result = getNext();
                if (result == null){
                    throw new NoSuchElementException("End of database reached");
                }
            } catch (IDMapperException ex) {
                ex.printStackTrace();
                throw new NoSuchElementException("Able to generate next due to exception " + ex.getMessage());
            }
        } else {
            result = bufferedNext;
            bufferedNext = null;
        }
        return result;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<Xref> iterator() {
        return this;
    }

    
}
