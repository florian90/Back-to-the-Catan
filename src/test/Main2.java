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
import model.jeu.*;
import model.joueur.Achetable;
import model.joueur.Invention;
import model.joueur.TypeCarte;
import vue.jeu.panneauMarche.BoutonAchat;

import java.util.ArrayList;

public class Main2 extends Application {
	public static void main(String[] args) { launch(args); }

	@Override public void start(final Stage primaryStage) {
		Plateau plateau = new Plateau(Epoque._1855, 7);

	}
}
