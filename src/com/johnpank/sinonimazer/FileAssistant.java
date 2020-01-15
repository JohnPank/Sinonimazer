package com.johnpank.sinonimazer;

import java.io.*;
import java.util.HashMap;

public class FileAssistant {

    //сохранение текста в файл
    public void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Загрузка текста из файла
    public String loadFile(File file){
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while ((s = reader.readLine()) != null){
                stringBuilder.append(s).append("\n");
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    //загрузка словаря
    public HashMap<String, String> parseDictionary(File dictionaryFile){

        HashMap<String, String> dictionaryMap = new HashMap<>();

        String strDict = loadFile(dictionaryFile);
        String[] strings = strDict.split(",");

        //проверка чтобы слово не оканчивалось переводом строки
        for (int i = 0; i < strings.length; i++) {
            if(strings[i].endsWith("\n")){
                strings[i] = strings[i].substring(0, strings[i].length()-1);
            }
        }

        for (int i = 0; i < strings.length; i=i+2) {
            dictionaryMap.put(strings[i], strings[i+1]);
        }

        //System.out.println(dictionaryMap);

        return dictionaryMap;
    }

}
