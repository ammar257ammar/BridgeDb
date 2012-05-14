/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bridgedb.linkset;

import java.io.File;
import java.io.IOException;
import org.bridgedb.IDMapperException;
import org.bridgedb.sql.SQLAccess;
import org.bridgedb.sql.URLMapperSQL;
import org.bridgedb.sql.SqlFactory;
import org.openrdf.OpenRDFException;

/**
 *
 * @author Christian
 */
public class LinksetLoader {
    
    private static void parse(URLMapperSQL urlMapperSQL, String fileName) throws IDMapperLinksetException{
        File file = new File(fileName);
        if (file.isFile()){
            LinksetHandler.parse (urlMapperSQL,fileName);
        } else if (file.isDirectory()) {
            parse(urlMapperSQL, file);
        } else {
            throw new IDMapperLinksetException("File: " + fileName + " is neither a file or a directory");
        }
    }
    
    private static void parse(URLMapperSQL urlMapperSQL, File file){
        if (file.isFile()){
            try {
                LinksetHandler.parse (urlMapperSQL,file.getAbsolutePath());
            } catch (IDMapperLinksetException ex) {
                System.err.println("Unable to parse " + file.getAbsolutePath() + " cause: " + ex);
            }
        } else {
            File[] children = file.listFiles();
            for (File child:children){
                parse(urlMapperSQL, child);
            }
        }
    }

    private static void clearAndParse(URLMapperSQL urlMapperSQL, String fileName) throws IDMapperLinksetException{
        File file = new File(fileName);
        if (file.isFile()){
            LinksetHandler.clearAndParse (urlMapperSQL,fileName);
        } else if (file.isDirectory()) {
            clearAndParse(urlMapperSQL, file);
        } else {
            throw new IDMapperLinksetException("File: " + fileName + " is neither a file or a directory");
        }
    }
    
    private static void clearAndParse(URLMapperSQL urlMapperSQL, File file){
        if (file.isFile()){
            try {
                LinksetHandler.clearAndParse (urlMapperSQL,file.getAbsolutePath());
            } catch (IDMapperLinksetException ex) {
                System.err.println("Unable to parse " + file.getAbsolutePath() + " cause: " + ex.getMessage());
            }
        } else {
            File[] children = file.listFiles();
            clearAndParse(urlMapperSQL, children[0]);
            for (int i = 1; i < children.length; i++){
                parse(urlMapperSQL, children[i]);
            }
        }
    }

    public static void main(String[] args) throws IDMapperException, IOException, OpenRDFException  {
        System.out.println(SqlFactory.configFilePath());
        System.out.println(SqlFactory.configSource());
        SQLAccess sqlAccess = SqlFactory.createLoadSQLAccess();
        if (args.length == 1){
            URLMapperSQL urlMapperSQL = new URLMapperSQL(sqlAccess);
            parse (urlMapperSQL, args[0]);
        } else if (args.length == 2){
            if (args[1].equals("new")){
                URLMapperSQL urlMapperSQL = new URLMapperSQL(true, sqlAccess);
                clearAndParse(urlMapperSQL, args[0]);
        } else {
                usage();
            }
        } else {
            usage();
        }
    }

    private static void usage() {
        System.out.println("Welcome to the OPS Linkset Loader.");
        System.out.println("This methods requires the file name (incl path) of the linkset to be loaded.");
        System.out.println("Please run this again with two paramters");
        System.out.println("The file name (including path of the linkset");
        System.out.println("The base uri for any ids without a base URI.");
        System.exit(1);
    }
}