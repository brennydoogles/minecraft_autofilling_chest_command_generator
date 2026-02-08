package com.brendondugan.command_generator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CommandGeneratorController implements Initializable {

    @FXML
    private TextField blockTypeField;
    @FXML
    private ChoiceBox<String> colorPicker;
    @FXML
    private ChoiceBox<String> chestDirectionPicker;
    @FXML
    private TextArea outputArea;

    Clipboard clipboard = Clipboard.getSystemClipboard();

    private final List<String> colors = List.of("none", "white", "light_gray", "gray", "black", "brown", "red", "orange", "yellow", "lime", "green", "cyan", "light_blue", "blue", "purple", "magenta", "pink");
    private final List<String> chestDirections = List.of("north", "south", "east", "west");
    private final String commandTemplate = "/setblock ~ ~2 ~ trapped_chest[facing=|direction|]{Items:[{Slot:0,id:|block|,count:|count|},{Slot:1,id:|block|,count:|count|},{Slot:2,id:|block|,count:|count|},{Slot:3,id:|block|,count:|count|},{Slot:4,id:|block|,count:|count|},{Slot:5,id:|block|,count:|count|},{Slot:6,id:|block|,count:|count|},{Slot:7,id:|block|,count:|count|},{Slot:8,id:|block|,count:|count|},{Slot:9,id:|block|,count:|count|},{Slot:10,id:|block|,count:|count|},{Slot:11,id:|block|,count:|count|},{Slot:12,id:|block|,count:|count|},{Slot:13,id:|block|,count:|count|},{Slot:14,id:|block|,count:|count|},{Slot:15,id:|block|,count:|count|},{Slot:16,id:|block|,count:|count|},{Slot:17,id:|block|,count:|count|},{Slot:18,id:|block|,count:|count|},{Slot:19,id:|block|,count:|count|},{Slot:20,id:|block|,count:|count|},{Slot:21,id:|block|,count:|count|},{Slot:22,id:|block|,count:|count|},{Slot:23,id:|block|,count:|count|},{Slot:24,id:|block|,count:|count|},{Slot:25,id:|block|,count:|count|},{Slot:26,id:|block|,count:|count|}]} replace";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.colorPicker.setItems(FXCollections.observableList(colors));
        this.colorPicker.setValue("none");
        this.chestDirectionPicker.setItems(FXCollections.observableList(chestDirections));
        this.chestDirectionPicker.setValue("north");

        ChangeListener<String> changeListener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                generate(null);
            }
        };

        this.blockTypeField.textProperty().addListener(changeListener);
        this.colorPicker.valueProperty().addListener(changeListener);
        this.chestDirectionPicker.valueProperty().addListener(changeListener);
    }

    public void generate(ActionEvent actionEvent) {
        String chestDirection = chestDirectionPicker.getValue().trim();
        String blockName = getBlockName();
        String stackSize = "64";
        String result = commandTemplate.replaceAll("\\|direction\\|", chestDirection).replaceAll("\\|block\\|", blockName).replaceAll("\\|count\\|", stackSize);
        outputArea.setText(result);
        ClipboardContent content = new ClipboardContent();
        content.putString(result);
        clipboard.setContent(content);
    }

    private String getBlockName(){
        String color = colorPicker.getValue().trim();
        String blockType = blockTypeField.getText().trim();
        String tempBlockType;
        if(color.equals("none")){
            tempBlockType = blockType;
        }
        else {
            tempBlockType = String.format("%s_%s", color, blockType);
        }
        if(tempBlockType.contains(":")){
            return String.format("\"%s\"", tempBlockType);
        }
        return tempBlockType;
    }
}
