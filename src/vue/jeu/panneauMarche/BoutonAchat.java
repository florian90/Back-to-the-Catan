package vue.jeu.panneauMarche;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import model.jeu.Epoque;
import model.jeu.Jeu;
import model.joueur.Achetable;
import model.joueur.PackRess;
import model.joueur.Ressource;
import vue.URL;
import vue.jeu.Desactivable;

/**
 * Crée un boutton permettant d'acheter un objet
 * nécessite une fonction acheter et peutConstruire dans Joueur, prennant en paramètre un achetable
 */
public class BoutonAchat extends Button implements Desactivable {
	private Achetable m_objet;
	private Jeu m_jeu;
	private PopupCout m_popup;

	public BoutonAchat(Achetable p_achetable, Jeu p_jeu)
	{
		super("Acheter");
		setId("defaultButton");
		m_objet = p_achetable;
		m_jeu = p_jeu;
		setPrefWidth(75);
		m_popup = new PopupCout();
		setOnAction((e) -> {
			if (m_jeu.getJoueur().peutAcheter(m_objet))
				m_jeu.getJoueur().acheter(m_objet);
		});

		// Affichage du popup
		setOnMouseEntered((e) -> {
			m_popup.show(this, e.getScreenX(), e.getScreenY());
		});
		setOnMouseMoved((e) -> {
			m_popup.setX(e.getScreenX());
			m_popup.setY(e.getScreenY());
		});
		setOnMouseExited((e) -> {
			m_popup.hide();
		});
	}

	@Override
	public void desactiver()
	{
		setDisable(true);
	}

	@Override
	public void activer()
	{
		setDisable(false);
	}

	public void update()
	{
		m_popup.update();
	}

	/**
	 * Popp qui s'affiche lorsque l'on est sur le bouton acheter
	 * Affiche le cout de construction de l'objet
	 */
	private class PopupCout extends Popup {
		private final GridPane m_grid = new GridPane();

		public PopupCout()
		{
			m_grid.setLayoutX(5);
			m_grid.setLayoutY(5);
			m_grid.setPadding(new Insets(5));
			m_grid.setHgap(10);
			m_grid.setVgap(5);
			getContent().add(m_grid);
			update();
		}

		public void peutAcheter(boolean b)
		{
			if (b)
				m_grid.setStyle("-fx-background-color: azure;-fx-border-color: green");
			else
				m_grid.setStyle("-fx-background-color: azure;-fx-border-color: red");
		}

		public void update()
		{
			m_grid.getChildren().clear();
			PackRess packRess = m_objet.cout(m_jeu.getEpoqueActuelle());
			int i = 0;
			for (Ressource ressource : Ressource.values())
			{
				int nbr = packRess.count(ressource);
				if (nbr > 0)
				{
					ImageView imageView = new ImageView(URL.url(ressource));
					imageView.setFitWidth(40);
					imageView.setFitHeight(40);
					m_grid.add(imageView, 0, i);
					m_grid.add(new Label("x" + nbr), 1, i);
					i++;
				}
			}
			peutAcheter(m_jeu.getJoueur().peutAcheter(m_objet));
		}
	}
}
