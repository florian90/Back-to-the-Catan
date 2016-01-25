package vue.jeu.panneauMarche;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.jeu.Epoque;
import model.jeu.Jeu;
import model.jeu.TypeArete;
import model.jeu.TypePoint;
import model.joueur.Joueur;
import model.joueur.Ressource;
import vue.jeu.ContentJoueur;
import vue.jeu.Desactivable;

public class ContentTabConstructions extends GridPane implements Desactivable {
	private BoutonAchat btRoute, btAutoroute, btVillage, btVille;
	private Jeu m_jeu;

	public ContentTabConstructions(Jeu jeu)
	{
		m_jeu = jeu;

		setPadding(new Insets(20,5,20,5));
		setHgap(5);
		setVgap(15);

		btRoute = new BoutonAchat(TypeArete.Route, m_jeu);
		btAutoroute = new BoutonAchat(TypeArete.Autoroute, m_jeu);
		btVillage = new BoutonAchat(TypePoint.Village, m_jeu);
		btVille = new BoutonAchat(TypePoint.Ville, m_jeu);

		add(new Label("Route"), 0, 0);
		add(btRoute, 1, 0);

		add(new Label("Autoroute"), 0, 1);
		add(btAutoroute, 1, 1);

		add(new Label("Village"), 0, 2);
		add(btVillage, 1, 2);

		add(new Label("Ville"), 0, 3);
		add(btVille, 1, 3);
	}

	@Override
	public void desactiver() {
		btVille.desactiver();
		btVillage.desactiver();
		btAutoroute.desactiver();
		btRoute.desactiver();
	}

	@Override
	public void activer() {
		btVille.activer();
		btVillage.activer();
		btAutoroute.activer();
		btRoute.activer();
	}

	public void update()
	{
		btRoute.update();
		btAutoroute.update();
		btVillage.update();
		btVille.update();
	}

}
