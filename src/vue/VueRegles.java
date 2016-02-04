package vue;

import java.net.URL;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import vue.jeu.defaultButton;

public class VueRegles extends GridPane{

	private defaultButton retour;
	private WebView browser = new WebView();
	private WebEngine webEngine = browser.getEngine();

	public VueRegles(Stage stage)
	{
		setHgap(10);
		setVgap(10);

		retour = new defaultButton("Retour");	
		URL urlRegles = getClass().getResource("Règles.html");

		webEngine.load(urlRegles.toExternalForm());

		browser.setPrefHeight(850);
		browser.setPrefWidth(1400);

		TitledPane tp = new TitledPane("Règles du jeu",browser);
		tp.setCollapsible(false);
		add(tp,0,0);
		add(retour,0,1);


	}

	public defaultButton getRetour() {
		return retour;
	}
}
