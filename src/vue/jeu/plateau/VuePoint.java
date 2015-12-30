package vue.jeu.plateau;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.jeu.Jeu;
import model.jeu.Point;
import model.jeu.TypePoint;
import vue.jeu.Constants;
import vue.jeu.Fenetre;

public class VuePoint extends Group{
	
	private Point m_point;
	private Circle m_cercle;

	private Jeu m_jeu;
	private Fenetre m_fenetre;
	
	public VuePoint(Point p, float centreX, float centreY, Jeu jeu)
	{
		m_point = p;
		m_jeu = jeu;
		m_fenetre = m_jeu.getFenetre();

		m_cercle = new Circle(centreX,centreY, Constants.pointRadius);
		m_cercle.setStroke(Color.BLACK);
		m_cercle.setFill(Color.BLACK);
		if(p.getType() == TypePoint.Vide)
		{
			getChildren().add(m_cercle);
		}

		m_cercle.setOnMouseEntered((e)->{
			m_cercle.setFill(Color.WHITE);
			if(m_jeu.isDeLance())
			{
				if (m_jeu.isConstructionActrive())
					m_fenetre.setStatus("Construire un nouveau point");
				else
					m_fenetre.setStatus("Cliquez sr le boutton construire si vous souhaitez construire un bâtiment ici");
			}
		});

		m_cercle.setOnMouseExited((e)->{
			m_fenetre.resetStatus();
			m_cercle.setFill(Color.BLACK);
		});

		m_cercle.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				m_jeu.clicPoint(m_point);
			}
		});
	}
}
