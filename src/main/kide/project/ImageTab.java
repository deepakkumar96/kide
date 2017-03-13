/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.kide.project;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import main.kide.utils.FileUtils;

/**
 *
 * @author Deepak
 */
public class ImageTab extends Tab{
    
    public ImageTab(File file){
        
        StackPane container = new StackPane();
        System.out.println("PATH : " + file.getAbsolutePath());
        
        ImageView imgView = null;
        try{
            imgView = new ImageView(new Image(file.toURI().toURL().toExternalForm()));
        }catch(MalformedURLException ex){
            imgView = new ImageView(FileUtils.getIcon("java"));
        }
        
        container.getChildren().add(new ScrollPane(imgView));
        
        this.setText(file.getName());
        
        this.setContent(container);
    }
    
}
