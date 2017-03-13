/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.kide.managers;

import com.kide.openapi.PluginService;
import com.kide.openapi.spi.PluginInfo;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.kide.project.KideProjectView;
import main.kide.project.KideTreeFolder;
import main.kide.project.KideTreeItem;
import main.kide.utils.FileUtils;

/**
 *
 * @author deepak
 */
public class ProjectManager {
    
    private File rootDir;
    private KideProjectView projectView;
    private Project project;
    
    
    public ProjectManager(KideProjectView projectView){
        this.projectView = projectView;
        this.project = new Project();
    }
    
    
    public ProjectManager(File file){
        this.rootDir = file;
    }
    
    public void loadProject(){
        this.rootDir = new File(project.getPath());      // creating root dir from where project will be loaded
        
        PluginInfo pluginInfo = PluginService.getService(PluginInfo.class);
        System.out.println(pluginInfo);
        
        KideTreeFolder projectRootFolder = new KideTreeFolder(rootDir);
        for(File rootFile : rootDir.listFiles()){
            addFilesToProjectView(rootFile, projectRootFolder, pluginInfo.getFileIcon(), pluginInfo.getFileIcon());
        }
        projectView.setRoot(projectRootFolder);
        
    }
    
    public void addFilesToProjectView(File file, KideTreeFolder treeFolder, Image folderIcon, Image fileIcon){
        if(file.isFile()){
            KideTreeItem projectItem = new KideTreeItem(file);
            //setting icon to tree node
            Image img = null;
            if((img = FileUtils.getIcon(FileUtils.getExtension(file))) == null)
                img = fileIcon;
            projectItem.setGraphic(new ImageView(img));
            treeFolder.getChildren().add(projectItem);
        }
        else{
            KideTreeFolder projectFolder = new KideTreeFolder(file);
            projectFolder.setGraphic(
                new ImageView(new Image(FileUtils.class.getResourceAsStream("/main/kide/resource/file_icons/folder_icon.png")))
            );
            treeFolder.getChildren().add(projectFolder);
            for(File f : file.listFiles()){
                addFilesToProjectView(f, projectFolder, folderIcon, fileIcon);
            }
        }
    }
    
    
}
