/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author gusjg
 */
public class JSONHandler 
{
    /**
     * Loads a JSON from an InputStream.
     * 
     * @param stream
     * @return 
     * @throws java.io.IOException 
     * @throws org.json.simple.parser.ParseException 
     */
    public static JSONObject loadJSON(InputStream stream) throws IOException, ParseException
    {
        JSONObject json;
        JSONParser parser = new JSONParser();
        
        Reader reader = new InputStreamReader(stream);
        json = (JSONObject) parser.parse(reader);
        System.out.println(json);
        return json;
    }
    
    /**
     * Loads a JSON from a URL.
     * 
     * @param url
     * @return 
     * @throws java.io.IOException 
     * @throws org.json.simple.parser.ParseException 
     */
    public static JSONObject loadJSON(URL url) throws IOException, ParseException
    {
        JSONObject json;
        JSONParser parser = new JSONParser();
        
        Reader reader = new InputStreamReader(url.openStream());
        json = (JSONObject) parser.parse(reader);
        System.out.println(json);
        return json;
    }
    
    /**
     * Loads a JSON from a File.
     * 
     * @param file
     * @return 
     * @throws java.io.IOException 
     * @throws org.json.simple.parser.ParseException 
     */
    public static JSONObject loadJSON(File file) throws IOException, ParseException
    {
        JSONObject json;
        JSONParser parser = new JSONParser();
        
        Reader reader = new FileReader(file);
        json = (JSONObject) parser.parse(reader);
        System.out.println(json);
        return json;
    }
}
