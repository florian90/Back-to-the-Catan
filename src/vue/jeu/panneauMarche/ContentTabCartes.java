package vue.jeu.panneauMarche;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.jeu.Epoque;
import model.jeu.Jeu;
import model.joueur.Ressource;
import model.joueur.TypeCarte;
import vue.jeu.ContentJoueur;
import vue.jeu.Desactivable;

public class ContentTabCartes extends GridPane implements Desactivable{
	
	private BoutonAchat tirer;
	private Jeu m_jeu;
	private ContentJoueur ctj;
	private Label cout;
	
	public ContentTabCartes(Jeu p_jeu, ContentJoueur p_ctj)
	{

		m_jeu = p_jeu;
		ctj = p_ctj;
		
		ImageView cartes = new ImageView(new Image("textures/cards.jpg"));
		tirer = new BoutonAchat(TypeCarte.DeplacerVoleur, m_jeu);
		cartes.setPreserveRatio(true);
		cartes.setFitWidth(100);
		
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);
		cout = new Label("Coût : \n - 1 x "+Ressource.toString(Epoque.getR1(p_jeu.getEpoqueActuelle()))+"\n - 1 x "+Ressource.toString(Epoque.getR2(p_jeu.getEpoqueActuelle())));
		add(cout, 0, 0,2,1);
		add(cartes, 0, 2);
		add(tirer,1,2);
		
		tirer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				m_jeu.tirerCarte();
				ctj.update();
				
			}
		});
		
	}
	
	public void update()
	{
		cout.setText("Coût : \n - 1 x "+Ressource.toString(Epoque.getR1(m_jeu.getEpoqueActuelle()))+"\n - 1 x "+Ressource.toString(Epoque.getR2(m_jeu.getEpoqueActuelle())));
	}

	@Override
	public void desactiver() {
		tirer.setDisable(true);
		
	}

	@Override
	public void activer() {
		tirer.setDisable(false);
		
	}
}
