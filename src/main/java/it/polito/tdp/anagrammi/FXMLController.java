package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtErrati;

    @FXML
    private TextField txtInput;

    @FXML
    void calcolaAnagrammi(ActionEvent event) {
    	this.txtCorretti.clear();
    	this.txtErrati.clear();
    	try {
    		String parola = this.txtInput.getText();
    		if(parola.isBlank()) {
    			this.txtCorretti.setText("Inserire una parola da anagrammare");
    			return;
    		}
    		Set<String> anagrammi = this.model.trovaAnagrammi(parola);
    		for(String s: anagrammi) {
    			if(this.model.isCorrect(s)) {
    				this.txtCorretti.appendText(s+"\n");
    			} else {
    				this.txtErrati.appendText(s+"\n");
    			}
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }

    @FXML
    void reset(ActionEvent event) {
    	this.txtInput.clear();
    	this.txtCorretti.clear();
    	this.txtErrati.clear();
    }

    @FXML
    void initialize() {
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

}
