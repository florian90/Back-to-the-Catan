package vue.jeu.panneauMarche;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.jeu.Jeu;
import model.joueur.Invention;
import vue.jeu.Desactivable;

public class ContentTabInventions extends GridPane implements Desactivable{

	private BoutonAchat acheterTrain;
	private BoutonAchat acheterRadio;
	private BoutonAchat acheterConvecteur;
	private BoutonAchat acheterHoverboard;
	private Jeu m_jeu;

	public ContentTabInventions(Jeu jeu )
	{
		m_jeu = jeu;

		acheterTrain = new BoutonAchat(Invention.Train, m_jeu);
		acheterRadio = new BoutonAchat(Invention.Radio, m_jeu);
		acheterConvecteur = new BoutonAchat(Invention.ConvecteurTemporel, m_jeu);
		acheterHoverboard = new BoutonAchat(Invention.HoverBoard, m_jeu);

		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);
		setHeight(800);

		add(new Label("Train"), 0, 0);
		add(new Label("Radio"), 0, 1);
		add(new Label("Convecteur Temporel"), 0, 2);
		add(new Label("Hoverboard"), 0, 3);

		add(acheterTrain, 2, 0);
		add(acheterRadio, 2, 1);
		add(acheterConvecteur, 2, 2);
		add(acheterHoverboard, 2, 3);
	}

	public void update()
	{
		acheterConvecteur.update();
		acheterHoverboard.update();
		acheterRadio.update();
		acheterTrain.update();
	}

	@Override
	public void desactiver() {
		acheterConvecteur.desactiver();
		acheterHoverboard.desactiver();
		acheterRadio.desactiver();
		acheterTrain.desactiver();
	}

	@Override
	public void activer() {
		acheterConvecteur.activer();
		acheterHoverboard.activer();
		acheterRadio.activer();
		acheterTrain.activer();
	}

}
