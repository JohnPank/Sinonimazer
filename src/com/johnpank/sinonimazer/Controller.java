package com.johnpank.sinonimazer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    //файл словаря
    private static final String DICTIONARY_FILE = "dict.txt";

    //обьявление необходимых обьектов
    private FileChooser fileChooser;
    private FileAssistant fileAssistant;
    File file;
    HashMap<String, String> dictMap;

    //Полуние элементов управления из файла формы
    @FXML
    MenuItem miOpen, miExit, miAbout, miSave;

    @FXML
    Label lblFile;

    @FXML
    Button btnExit;

    @FXML
    TextArea txaSource, txaResult;

    //инициализация
    @FXML
    public void initialize() {

        fileChooser = new FileChooser();
        fileAssistant = new FileAssistant();
        dictMap= fileAssistant.parseDictionary(new File(DICTIONARY_FILE));

        //обработка пункта меню Открыть
        miOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileChooser.setTitle("Open file");
                file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    lblFile.setText(file.getPath());
                    String sourse = fileAssistant.loadFile(file);
                    String result = "";

                    txaSource.setText(sourse);

                    txaResult.setText(changeWords(sourse, dictMap));
                }
            }
        });

        //обработка пункта меню Выход
        miExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        //обработка пункта меню О программе
        miAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("О программе");
                alert.setHeaderText(null);
                alert.setContentText("Программа для замены слов их синонимами.");
                alert.showAndWait();
            }
        });

        //обработка пункта меню Сохранить
        miSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //установка фильтра
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                fileChooser.setTitle("Save file");
                fileChooser.setInitialFileName("new" + file.getName());

                //показ сиситемного окна сохранения файла
                File file = fileChooser.showSaveDialog(null);

                if (file != null) {
                    fileAssistant.saveTextToFile(txaResult.getText(), file);
                }
            }
        });

        //обработка нажатия кнопки Выход
        btnExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.exit(0);
                    }
        });
    }

    //замена слов
    private String changeWords(String source, HashMap<String, String> dictMap){
        String result = source;

        for (Map.Entry<String, String> pair : dictMap.entrySet()){
            result = result.replaceAll(pair.getKey(), pair.getValue());
        }

        return result;
    }


}

