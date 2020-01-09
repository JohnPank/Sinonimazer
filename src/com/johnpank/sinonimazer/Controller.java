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

    private static final String DICTIONARY_FILE = "dict.txt";

    private FileChooser fileChooser;
    private FileAssistant fileAssistant;

    @FXML
    MenuItem miOpen, miExit, miAbout, miSave;

    @FXML
    Label lblFile;

    @FXML
    Button btnExit;

    @FXML
    TextArea txaSource, txaResult;

    @FXML
    public void initialize() {

        fileChooser = new FileChooser();
        fileAssistant = new FileAssistant();

        HashMap<String, String> dictMap = fileAssistant.parseDictionary(new File(DICTIONARY_FILE));

        //обработка пункта меню Открыть
        miOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileChooser.setTitle("Open file");
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    lblFile.setText(file.getPath());
                    String sourse = fileAssistant.loadFile(file);
                    String result = "";

                    txaSource.setText(sourse);

                    for (Map.Entry<String, String> pair : dictMap.entrySet()){
                        sourse = sourse.replaceAll(pair.getKey(), pair.getValue());

                    }

                    txaResult.setText(sourse);
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
                alert.setTitle("About Sinonimazer");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Course project of JohnPank");

                alert.showAndWait();
            }
        });

        //обработка пункта меню Сохранить
//        miSave.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                //Set extension filter for text files
//                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//                fileChooser.getExtensionFilters().add(extFilter);
//                fileChooser.setTitle("Save file");
//
//                //Show save file dialog
//                File file = fileChooser.showSaveDialog(null);
//
//                if (file != null) {
//                    fileAssistant.saveTextToFile(txaResult.getText(), file);
//                }
//            }
//        });

        //обработка нажатия кнопки Выход
        btnExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.exit(0);
                    }
                });

    }


}

