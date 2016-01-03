/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author timwalsh300
 */
public class Robots extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        HBox root = (HBox) FXMLLoader.load(getClass().getResource("RobotsDocument.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("RobotsCSS.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
