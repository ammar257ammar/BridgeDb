/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bridgedb.ws;

import java.util.ArrayList;
import java.util.List;
import org.bridgedb.IDMapperException;
import org.bridgedb.ws.bean.MappingSetInfoBean;
import org.bridgedb.ws.bean.MappingSetStatisticsBean;
import org.bridgedb.ws.bean.URLBean;
import org.bridgedb.ws.bean.URLExistsBean;
import org.bridgedb.ws.bean.URLMappingBean;
import org.bridgedb.ws.bean.URLSearchBean;
import org.bridgedb.ws.bean.UriSpacesBean;
import org.bridgedb.ws.bean.XrefBean;

/**
 *
 * @author Christian
 */
public interface WSOpsInterface extends WSCoreInterface{

    public List<URLMappingBean> mapURL(String sourceURL, List<String> targetUriSpace) throws IDMapperException;
    
    public URLExistsBean urlExists(String URL) throws IDMapperException;

    public URLSearchBean URLSearch(String text, String limitString) throws IDMapperException;

    public XrefBean toXref(String URL) throws IDMapperException;

    public URLMappingBean getMapping(String id) throws IDMapperException;

    public List<URLBean> getSampleSourceURLs() throws IDMapperException;

    public MappingSetStatisticsBean getMappingSetStatistics() throws IDMapperException;

    public List<MappingSetInfoBean> getMappingSetInfos() throws IDMapperException;

    public UriSpacesBean getUriSpaces(String sysCode) throws IDMapperException;
}