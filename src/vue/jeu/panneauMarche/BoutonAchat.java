package vue.jeu.panneauMarche;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import model.jeu.Epoque;
import model.jeu.Jeu;
import model.joueur.Achetable;
import model.joueur.PackRess;
import model.joueur.Ressource;
import vue.jeu.Desactivable;

public class BoutonAchat extends Button implements Desactivable {
	private Achetable m_objet;
	private Jeu m_jeu;
	private PopupCout popupCout;

	public BoutonAchat(Achetable p_achetable, Jeu p_jeu)
	{
		super("Acheter");
		m_objet = p_achetable;
		m_jeu = p_jeu;
		setPrefWidth(75);
		popupCout = new PopupCout();
		setOnAction((e) -> {
			if (m_jeu.getJoueur().peutConstruire(m_objet, Epoque._2015))
				m_jeu.getJoueur().acheter(m_objet);
		});

		setOnMouseEntered((e) -> {
			popupCout.show(this, e.getScreenX(), e.getScreenY());
		});
		setOnMouseMoved((e) -> {
			popupCout.setX(e.getScreenX());
			popupCout.setY(e.getScreenY());
		});
		setOnMouseExited((e) -> {
			popupCout.hide();
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

	private class PopupCout extends Popup {

		public PopupCout()
		{
			GridPane grid = new GridPane();
			grid.setLayoutX(5);
			grid.setLayoutY(5);
			grid.setPadding(new Insets(5));
			grid.setHgap(10);
			grid.setVgap(5);
			grid.setStyle("-fx-background-color: azure;-fx-border-color: black");
			PackRess packRess = m_objet.cout(m_jeu.getEpoqueActuelle());
			int i = 0;
			for (Ressource ressource : Ressource.values())
			{
				int nbr = packRess.count(ressource);
				if (nbr > 0)
				{
					ImageView imageView = new ImageView("textures/hex" + ressource + ".png");
					imageView.setFitWidth(40);
					imageView.setFitHeight(40);
					grid.add(imageView, 0, i);
					grid.add(new Label("x" + nbr), 1, i);
					i++;
				}
			}
			getContent().add(grid);
		}
	}
}
