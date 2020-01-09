package com.johnpank.sinonimazer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FileAssistant {



    public void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {

        }
    }

    public String loadFile(File file){
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while ((s = reader.readLine()) != null){
                stringBuilder.append(s + "\n");
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public HashMap<String, String> parseDictionary(File dictionaryFile){

        HashMap<String, String> dictionaryMap = new HashMap<>();

        String strDict = loadFile(dictionaryFile);
        String[] strings = strDict.split(",");

        for (int i = 0; i < strings.length; i=i+2) {
            dictionaryMap.put(strings[i], strings[i+1]);
        }

        System.out.println(dictionaryMap);

        return dictionaryMap;
    }

}
