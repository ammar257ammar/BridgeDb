// BridgeDb,
// An abstraction layer for identifier mapping services, both local and online.
//
// Copyright 2006-2009  BridgeDb developers
// Copyright 2012-2013  Christian Y. A. Brenninkmeijer
// Copyright 2012-2013  OpenPhacts
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package org.bridgedb.tools.metadata;

import java.util.Set;
import org.bridgedb.rdf.constants.VoidConstants;
import org.bridgedb.tools.metadata.constants.DctermsConstants;
import org.bridgedb.tools.metadata.rdf.LinksetStatementReader;
import org.bridgedb.tools.metadata.rdf.LinksetStatements;
import org.bridgedb.tools.metadata.validator.ValidationType;
import org.bridgedb.utils.BridgeDBException;
import org.bridgedb.utils.TestUtils;
import static org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.impl.URIImpl;

/**
 *
 * @author Christian
 */
@Ignore
public class LinksetVoidInformationSubset1Test extends LinksetVoidInformationSubsetTest{
    
    public static String LINK_FILE = "test-data/chemspider2chemblrdf-linksetSubSet_1.ttl";
    
    public LinksetVoidInformationSubset1Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        LinksetStatements statements = new LinksetStatementReader(LINK_FILE);
        instance = new LinksetVoidInformation(FileTest.LINK_FILE, statements, ValidationType.LINKS);
    }


}