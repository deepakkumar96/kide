/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.kide.editor;

import com.terminalfx.TerminalBuilder;
import com.terminalfx.TerminalTab;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import main.kide.managers.ProjectManager;
import main.kide.project.KideEditorTab;
import main.kide.project.KideProjectView;
import main.kide.project.KideTreeFolder;
import main.kide.project.KideTreeItem;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class MainController implements Initializable {

    @FXML
    private TabPane tabEditor;
    @FXML
    private TabPane tabBottom;
    @FXML 
    private StackPane projectWindowPanel;
    
    ProjectManager projectManager;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        KideProjectView projectView = new KideProjectView(tabEditor);
        projectManager = new ProjectManager(projectView);
        
        projectManager.loadProject();

        
        System.out.println("Root : " +projectView.getRoot());
        //projectView.getCh
        
        projectWindowPanel.getChildren().add(projectView);
        
        //KideEditorTab codeTab = new KideEditorTab("");
        //tabEditor.getTabs().add(codeTab);
        
        //handling terminal
        TerminalBuilder terminalBuilder = new TerminalBuilder();
        TerminalTab terminal = terminalBuilder.newTerminal();
        terminal.setText("Terminal");
        
        
        Tab compileRunTab = new Tab();
        compileRunTab.setText("Compile");
        compileRunTab.setClosable(true);
        compileRunTab.setContent(new TextArea());
        
        //tabBottom
        tabBottom.getTabs().addAll(terminal);
    }    
    
}
