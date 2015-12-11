package test.plateau.application;


import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Fenetre extends BorderPane
{
	StackPane stack;
	ArrayList<Plateau> plateaux;
	int plateauActuel;
	Button suiv, prec;
	Label numPlateau;

	public Fenetre()
	{
		plateauActuel =1;
		numPlateau = new Label("Plateau n° :"+plateauActuel);
		plateaux = new ArrayList<Plateau>();
		plateaux.add(new Plateau(0, 0));
		plateaux.add(new Plateau(0, 0));
		plateaux.add(new Plateau(0, 0));
		plateaux.add(new Plateau(0, 0));
		stack = new StackPane(plateaux.get(0));
		suiv = new Button("Suivant");
		prec = new Button("Précédent");
		setCenter(stack);
		setBottom(new HBox(prec,suiv,numPlateau));

		suiv.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				plateauActuel=(plateauActuel+1)%4;
				System.out.println("pa :"+plateauActuel);
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(plateaux.get(plateauActuel));
				numPlateau.setText("Plateau n° :"+(plateauActuel+1));

			}
		});

		prec.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				plateauActuel=(plateauActuel+3)%4;
				System.out.println(plateauActuel);
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(plateaux.get(plateauActuel));
				numPlateau.setText("Plateau n° :"+(plateauActuel+1));

			}
		});
	}
}
