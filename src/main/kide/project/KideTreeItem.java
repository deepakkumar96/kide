/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.kide.project;

import java.io.File;
import javafx.scene.control.TreeItem;

/**
 *
 * @author deepak
 */
public class KideTreeItem extends TreeItem<String>
{
    private File file;
    
    public KideTreeItem(File file){
        super(file.getName());
        this.file = file;
    }
    
    public KideTreeItem(String file){
        this(new File(file));
    }
    
    public File getFile(){
        return file;
    }
    
}
