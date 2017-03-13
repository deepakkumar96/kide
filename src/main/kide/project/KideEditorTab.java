/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.kide.project;

import com.kide.openapi.PluginService;
import com.kide.openapi.spi.SyntaxHighlighter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import java.util.stream.*;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
/**
 *
 * @author deepak
 */
public class KideEditorTab  extends Tab{
    
    private File file;
    
    
    public KideEditorTab(String fileName){
        this(new File(fileName));
    }
    
    public KideEditorTab(File file){
        this.file = file;
        this.setClosable(true);
        
         
        setText(file.getName());
        
        CodeArea codeArea = new CodeArea();
        SyntaxHighlighter highlighter = PluginService.getService(SyntaxHighlighter.class);
        
        codeArea.richChanges()
                .filter(ch -> !ch.getInserted().equals(ch.getRemoved())) // XXX
                .subscribe(change -> {
                    codeArea.setStyleSpans(0, computeHighlighting(highlighter.getPattern(), codeArea.getText()));
                });
        codeArea.getStylesheets().add(highlighter.getStyleSheet().toExternalForm());
        
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                codeArea.appendText(line + "\n");
            }
            fr.close();
            br.close();
        }catch(IOException ioex){
            
        }finally{
            try{
                fr.close();
                br.close();
            }catch(IOException ioex){
                
            }
        }
        
        setContent(codeArea);
    }
    
    private static StyleSpans<Collection<String>> computeHighlighting(Pattern PATTERN, String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                    matcher.group("PAREN") != null ? "paren" :
                    matcher.group("BRACE") != null ? "brace" :
                    matcher.group("BRACKET") != null ? "bracket" :
                    matcher.group("SEMICOLON") != null ? "semicolon" :
                    matcher.group("STRING") != null ? "string" :
                    matcher.group("COMMENT") != null ? "comment" :
                    null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
    
}
