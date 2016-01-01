package vue.jeu.plateau;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.jeu.Jeu;
import model.jeu.Point;
import model.jeu.TypePoint;
import model.joueur.Ressource;
import vue.jeu.Constants;
import vue.jeu.Fenetre;

public class VuePoint extends Group{
	
	private Point m_point;
	private Circle m_cercle;
	private ImageView imageVille;
	private ImageView imageVillage;
	private Rectangle rectangle;

	private Jeu m_jeu;
	private Fenetre m_fenetre;
	
	public VuePoint(Point p, float centreX, float centreY, Jeu jeu)
	{
		m_point = p;
		m_jeu = jeu;
		m_fenetre = m_jeu.getFenetre();

		setOnMouseEntered((e)->{
			m_cercle.setFill(Color.WHITE);
			if(m_jeu.isDeLance())
			{
				if (m_jeu.isConstructionActive())
					m_fenetre.setStatus("Construire un nouveau point");
				else
					m_fenetre.setStatus("Cliquez sur le boutton construire si vous souhaitez construire un bâtiment ici");
			}
		});
		setOnMouseExited((e)->{
			m_fenetre.resetStatus();
			m_cercle.setFill(Color.BLACK);
		});
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				m_jeu.clicPoint(m_point);
			}
		});

		/** Initialisation du cercle **/
		m_cercle = new Circle(centreX,centreY, Constants.pointRadius);
		m_cercle.setStroke(Color.BLACK);
		m_cercle.setFill(Color.BLACK);

		/** Initialisation du rectangle **/
		rectangle = new Rectangle();
		rectangle.setWidth(30);
		rectangle.setHeight(30);
		rectangle.setX(centreX-rectangle.getWidth()/2);
		rectangle.setY(centreY-rectangle.getHeight()/2);

		/** Initialisation de l'image Village **/
		imageVillage = new ImageView("textures/Village.jpg");
		imageVillage.setFitHeight(30);
		imageVillage.setFitWidth(30);
		imageVillage.setX(centreX-imageVillage.getFitHeight()/2);
		imageVillage.setY(centreY-imageVillage.getFitWidth()/2);

		/** Initialisation de l'image Ville **/
		imageVille = new ImageView("textures/ville.png");
		imageVille.setFitHeight(30);
		imageVille.setFitWidth(30);
		imageVille.setX(centreX-imageVille.getFitHeight()/2);
		imageVille.setY(centreY-imageVille.getFitWidth()/2);

		update();
	}

	public void update()
	{
		getChildren().removeAll(getChildren());
		if(m_point.getProprietaire() != null)
			rectangle.setFill(m_point.getProprietaire().getCouleur());
		if(m_point.getType() == TypePoint.Village)
			getChildren().addAll(rectangle, imageVillage);
		else if(m_point.getType() == TypePoint.Ville)
			getChildren().addAll(rectangle, imageVille);
		else
			getChildren().add(m_cercle);
	}
}
