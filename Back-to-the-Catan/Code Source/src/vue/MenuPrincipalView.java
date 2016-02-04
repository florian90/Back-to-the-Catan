package vue;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import model.joueur.Joueur;

import java.util.ArrayList;

public class MenuPrincipalView extends AnchorPane {

	private GridPane buttonGrid, parametresGrid;
	private Spinner<Integer> spinNbJoueurs;
	private defaultButton nouvellePartie, regles, quitter;
	private TitledPane parametres;
	private defaultButton valider;
	private ArrayList<TextField> nomsJoueurs;


	public MenuPrincipalView()
	{
		nomsJoueurs = new ArrayList<TextField>();
		valider = new defaultButton("Valider");
		
		parametres = new TitledPane("Paramètres de la partie", null);
		parametres.setPrefWidth(350);
		parametres.setPadding(new Insets(10, 10, 10, 10));
		parametres.setTranslateX(75);
		parametres.setTranslateY(500);
		parametres.setCollapsible(false);
		parametres.setVisible(false);
		spinNbJoueurs = new Spinner<Integer>(2, 4, 4);
		spinNbJoueurs.setPrefWidth(75);
		spinNbJoueurs.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event)
			{

				actualiseJoueurs();
			}
		});
		
		parametresGrid = new GridPane();
		parametresGrid.setHgap(10);
		parametresGrid.setVgap(10);

		
		parametresGrid.getChildren().removeAll(parametresGrid.getChildren());
		parametresGrid.add(new Label("Nombre de Joueurs (2 à 4) :"), 0, 0);
		for (int i = 0; i < 4; ++i)
		{
			TextField nomJoueur = new TextField("Joueur " + (i + 1));
			nomJoueur.setEditable(true);
			parametresGrid.add(new Label("Joueur " + (i + 1) + " :"), 0, (i + 1));
			nomsJoueurs.add(nomJoueur);
			parametresGrid.add(nomJoueur, 1, i + 1);
			
		}
		parametresGrid.add(spinNbJoueurs, 1, 0);
		parametresGrid.add(valider, 1, 6);
		parametres.setContent(parametresGrid);
		
		nouvellePartie = new defaultButton("Nouvelle Partie");
		nouvellePartie.setId("bouttonMenuPrincipal");
		nouvellePartie.setPrefWidth(100);
		regles = new defaultButton("Règles");
		regles.setId("bouttonMenuPrincipal");
		regles.setPrefWidth(100);
		quitter = new defaultButton("Quitter");
		quitter.setId("boutonMenuPrincipal");
		quitter.setPrefWidth(100);


		buttonGrid = new GridPane();
		buttonGrid.setVgap(5);
		buttonGrid.setTranslateX(200);
		buttonGrid.setTranslateY(400);
		setBackground(new Background(new BackgroundImage(new Image("textures/fondMenu.jpg"), null, null, null, null)));

		buttonGrid.add(nouvellePartie, 0, 0);
		buttonGrid.add(regles, 0, 1);
		buttonGrid.add(quitter, 0, 2);
		
		getChildren().add(buttonGrid);
		getChildren().add(parametres);
		

	}
	
	private void actualiseJoueurs()
	{
		for (int i = 0; i < 4; ++i)
		{
			nomsJoueurs.get(i).setVisible(i < spinNbJoueurs.getValue());
			if (!(nomsJoueurs.get(i).isVisible()))
			{
				nomsJoueurs.get(i).setText("");
			}

		}
	}
	
	public ArrayList<Joueur> getListeJoueurs()
	{
		ArrayList<Joueur> joueurs = new ArrayList<>();
		int i = 1;
		for (TextField tf : nomsJoueurs)
		{
			if (!(tf.getText().equals("")))
			{
				joueurs.add(new Joueur(tf.getText(), i));
				++i;
			}

		}
		
		return joueurs;
	}
	
	public void setParametersVisible()
	{
		parametres.setVisible(true);
	}
	
	public defaultButton getNouvellePartie()
	{
		return nouvellePartie;
	}

	public defaultButton getRegles()
	{
		return regles;
	}

	public defaultButton getQuitter()
	{
		return quitter;
	}
	
	public defaultButton getValider()
	{
		return valider;
	}


}
