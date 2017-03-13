/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.kide.project;

import java.io.File;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import main.kide.utils.FileType;
import main.kide.utils.FileUtils;

/**
 *
 * @author deepak
 * @param <T>
 */
public class KideProjectView extends TreeView<String>{
    
    private KideTreeFolder root;
    
    public KideProjectView(TabPane editorPane){
        this.setOnMouseClicked(e ->{
            if(e.getClickCount() == 2){
                TreeItem<String> item = this.getSelectionModel().getSelectedItem();
                // if selected item is file not folder
                if(item instanceof KideTreeItem){
                    /*
                    Opening a the file in Editor Window
                    File can be either source file or media file
                    */
                    KideTreeItem kideProjectItem = (KideTreeItem) item;
                    System.out.println("Selected File " + kideProjectItem.getFile());
                    File selectedFile = kideProjectItem.getFile();
                    
                    FileType fileType = FileUtils.getFileType(selectedFile);
                    
                    Tab newTab = null;
                    switch(fileType){
                        case SOURCE_FILE:
                            newTab = new KideEditorTab(kideProjectItem.getFile());
                            break;
                        case IMAGE_FILE:
                            newTab = new ImageTab(selectedFile);
                            break;
                        case VIDEO_FILE:
                            newTab = new VideoTab(selectedFile);
                            break;
                        
                    }
                    if(newTab != null){
                        editorPane.getTabs().add(newTab);
                        //selecting recent created tab in the editor
                        editorPane.getSelectionModel().select(newTab);
                    }
                }
            }
        });
        
    }
    
    public KideProjectView(String file){
        super(new TreeItem<>(file));
    }
    
    public KideProjectView(File file){
        //this.rootDir = file;
    }
    
    public KideProjectView(){
        
    }
    
    public void setRoot(File file){
        //this.rootDir = file;
    }
    
}
