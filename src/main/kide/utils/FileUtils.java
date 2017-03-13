/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.kide.utils;

import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author Deepak
 */
public class FileUtils {
    
    public static String getExtension(File f){
        String fName = f.getName();
        return f.getName().substring(fName.indexOf(".")+1, fName.length());
    }
    
    public static Image getIcon(String extension){
        switch(extension){
            case "html":
                return new Image(FileUtils.class.getResourceAsStream("/main/kide/resource/file_icons/html_icon.png"));
            case "css":
                return new Image(FileUtils.class.getResourceAsStream("/main/kide/resource/file_icons/css_icon.png"));
            case "js":
                return new Image(FileUtils.class.getResourceAsStream("/main/kide/resource/file_icons/js_icon.png"));
            case "jpg":
            case "jpeg":
            case "png":
                return new Image(FileUtils.class.getResourceAsStream("/main/kide/resource/file_icons/image_icon.png"));
            case "mp4":
            case "mp3":
            case "flv":
            case "webm":
            case "avi":
                return new Image(FileUtils.class.getResourceAsStream("/main/kide/resource/file_icons/video_icon.png"));
            case "txt":
                return new Image(FileUtils.class.getResourceAsStream("/main/kide/resource/file_icons/txt_icon.png"));
            case "jar":
                return new Image(FileUtils.class.getResourceAsStream("/main/kide/resource/file_icons/jar_icon.png"));
            
            case "java":
                return new Image(FileUtils.class.getResourceAsStream("/main/kide/resource/file_icons/java_icon.png"));
            
            default:
                return null;
        }
    }
    
    /**
     * 
     * @param file
     * @return the type of file on the basis of its extension 
     */
    public static FileType getFileType(File file){
        String fname = file.getName();
        int dotIndex = fname.lastIndexOf(".");
        
        if(dotIndex < 0)
            return FileType.UNKNOWN_TYPE;
        
        String extension = fname.substring(dotIndex+1, fname.length());
        switch(extension){
            case "mp4":
            case "flv":
            case "webm":
            case "avi":
                return FileType.VIDEO_FILE;
            
            case "mp3":
                return FileType.AUDIO_FILE;
                
            case "jpg":
            case "jpeg":
            case "png":
                return FileType.IMAGE_FILE;
            
            case "jar":
            case "exe":
                return FileType.BINARY_FILE;
            
            default:
                return FileType.SOURCE_FILE;
        }
        
    }
    
    public static void main(String...args){
        System.out.println(getFileType(new File("C:\\Users\\Deepak\\Documents\\NetBeans_8.0.2\\Pong_4\\src\\Pong4\\Ball.java")));
    }
    
}
