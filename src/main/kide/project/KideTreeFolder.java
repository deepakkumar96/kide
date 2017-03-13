/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.kide.project;

import java.io.File;
import javafx.event.EventType;
import javafx.scene.control.TreeItem;

/**
 *
 * @author deepak
 * @param <T>
 */
public class KideTreeFolder extends TreeItem<String>{
    
    private File file;
    
    public KideTreeFolder(File file){
        super(file.getName());
        this.file = file;
    }
    
    public KideTreeFolder(String file){
        this(new File(file));
    }
    
    public KideTreeFolder(){
        //setValue("");
    }
    
}
