/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.kide.editor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author deepak
 */
public class kide extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        Parent root = null;
        MainController mainController = new MainController();
        
        try{
            FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/resource/layouts/main.fxml"));
            //mainLoader.setController(mainController);
            root = (Parent) mainLoader.load();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setTitle("Kide");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
