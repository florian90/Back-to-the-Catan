package test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.jeu.Jeu;
import model.jeu.TypeArete;
import model.jeu.TypePoint;
import model.joueur.Achetable;
import model.joueur.Invention;
import model.joueur.TypeCarte;
import vue.jeu.panneauMarche.BoutonAchat;

import java.util.ArrayList;

public class Main2 extends Application {
	public static void main(String[] args) { launch(args); }

	@Override public void start(final Stage primaryStage) {
		primaryStage.setTitle("Popup Example");

		ArrayList<String> noms = new ArrayList<>();
		noms.add("Tung");
		noms.add("Coupat");
		BoutonAchat bt = new BoutonAchat(TypePoint.Ville, new Jeu(noms));
		BoutonAchat bt2 = new BoutonAchat(Invention.HoverBoard, new Jeu(noms));
		BoutonAchat bt3 = new BoutonAchat(TypeArete.Autoroute, new Jeu(noms));

		HBox layout = new HBox(10);
		layout.getChildren().addAll(bt, bt2, bt3);
		primaryStage.setScene(new Scene(layout, 500, 700));
		primaryStage.show();
	}
}
