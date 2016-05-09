/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

/**
 *
 * @author timwalsh300
 */
public class RobotsDocumentController implements Initializable {

    @FXML
    GridPane boardGrid;

    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> spaceOptions
                = FXCollections.observableArrayList(
                        " ",
                        "Red Star",
                        "Red Moon",
                        "Red Gear",
                        "Red Planet",
                        "Blue Star",
                        "Blue Moon",
                        "Blue Gear",
                        "Blue Planet",
                        "Yellow Star",
                        "Yellow Moon",
                        "Yellow Gear",
                        "Yellow Planet",
                        "Green Star",
                        "Green Moon",
                        "Green Gear",
                        "Green Planet"
                );

        for (int row = 0; row < 31; row++) {
            for (int column = 0; column < 31; column++) {
                if (row >= 13 && row <= 17 && column >= 13 && column <= 17) {
                    boardGrid.add(new Label(), column, row);
                } else if (row % 2 == 0) {
                    if (column % 2 == 0) {
                        ComboBox spaceComboBox = new ComboBox(spaceOptions);
                        
                        boardGrid.add(spaceComboBox, column, row);
                    } else {
                        ToggleButton vbarrier = new ToggleButton();
                        vbarrier.getStyleClass().add("vbarrier");
                        boardGrid.add(vbarrier, column, row);
                    }
                } else if (column % 2 == 0) {
                    ToggleButton hbarrier = new ToggleButton();
                    hbarrier.getStyleClass().add("hbarrier");
                    boardGrid.add(hbarrier, column, row);
                } else {
                    Label cornerlabel = new Label();
                    cornerlabel.getStyleClass().add("cornerlabel");
                    boardGrid.add(cornerlabel, column, row);
                }
            }
        }
    }
}
