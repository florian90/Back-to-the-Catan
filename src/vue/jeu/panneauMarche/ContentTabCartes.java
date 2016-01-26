package vue.jeu.panneauMarche;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.jeu.Jeu;
import model.joueur.TypeCarte;
import vue.URL;
import vue.jeu.Desactivable;

public class ContentTabCartes extends GridPane implements Desactivable{
	
	private BoutonAchat btTirer;
	private Jeu m_jeu;
	
	public ContentTabCartes(Jeu p_jeu)
	{
		m_jeu = p_jeu;

		btTirer = new BoutonAchat(TypeCarte.DeplacerVoleur, m_jeu);
		ImageView imgCartes = new ImageView(new Image(URL.carte));
		imgCartes.setPreserveRatio(true);
		imgCartes.setFitWidth(100);
		
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);
		add(imgCartes, 0, 2);
		add(btTirer,1,2);
	}
	
	public void update()
	{
		btTirer.update();
	}

	@Override
	public void desactiver() {
		btTirer.setDisable(true);
	}

	@Override
	public void activer() {
		btTirer.setDisable(false);
	}
}
