/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.kide.project;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Deepak
 */
public class VideoTab extends Tab{
    
    public VideoTab(File file){
        
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.play();
        this.setText(file.getName());
        
        HBox controllers = new HBox();
        
        Button btnPlay = new Button("play");
        Button btnPause = new Button("Pause");
        Button btnStop = new Button("Stop");
        
        btnPlay.setOnAction(e -> mediaPlayer.pause());
        btnPause.setOnAction(e -> mediaPlayer.stop());
        btnStop.setOnAction(e -> mediaPlayer.stop());
        
        controllers.getChildren().addAll(btnPlay, btnPause, btnStop);
        
        BorderPane container = new BorderPane();
        BorderPane.setAlignment(controllers, Pos.CENTER);
        controllers.setAlignment(Pos.CENTER);
        
        container.setCenter(mediaView);
        container.setBottom(controllers);
        
        this.setContent(container);
        
        //container.prefWidthProperty().bind();
        
        this.setOnClosed(e ->{
            mediaPlayer.stop();
        });
    }
    
}
