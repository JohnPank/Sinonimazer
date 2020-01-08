package com.johnpank.sinonimazer;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    private FileChooser fileChooser;

    @FXML
    Button btnExit, btnOpen;
    @FXML
    Label lbl1;

    @FXML
    public void initialize(){
        fileChooser = new FileChooser();
        btnOpen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                File file = fileChooser.showOpenDialog(null);
                lbl1.setText(file.getPath());
            }
        });

        //обработка кнопки Exit
        btnExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });
    }

    public void openFile(){
        lbl1.setText("ok");
    }

}
//node.getScene().getWindow()


