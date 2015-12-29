package vue.jeu.panneauMarche;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.jeu.Jeu;
import model.joueur.Joueur;
import model.joueur.PackRess;
import model.joueur.Ressource;
import vue.jeu.ContentJoueur;
import vue.jeu.Desactivable;

public class ContentTabCartes extends GridPane implements Desactivable{
	
	private Button tirer;
	private	Joueur joueur;
	private Jeu jeu;
	private ContentJoueur ctj;
	
	public ContentTabCartes(Joueur p_joueur, Jeu p_jeu, ContentJoueur p_ctj)
	{
		joueur = p_joueur;
		jeu = p_jeu;
		ctj = p_ctj;
		
		ImageView cartes = new ImageView(new Image("textures/cards.jpg"));
		tirer = new Button("Tirer une carte");
		cartes.setPreserveRatio(true);
		cartes.setFitWidth(200);
		
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);
		add(new Label("Coût : 2 ressources d'époques différentes"), 0, 0);
		add(cartes, 0, 2);
		add(tirer,0,4);
		
		tirer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				joueur.acheterCarte(new PackRess(Ressource.Metal), jeu.tirerCarte()); //mettre le vrai cout d'une carte
				System.out.println(jeu.tirerCarte());
				ctj.update(joueur);
				
			}
		});
		
	}

	@Override
	public void desactiver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activer() {
		// TODO Auto-generated method stub
		
	}
}
