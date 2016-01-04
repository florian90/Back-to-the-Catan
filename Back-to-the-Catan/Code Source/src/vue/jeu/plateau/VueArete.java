package vue.jeu.plateau;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.jeu.Arete;
import model.jeu.Jeu;
import model.jeu.TypeArete;
import vue.jeu.Constants;
import vue.jeu.Fenetre;

public class VueArete extends Group {
	private Arete m_arete;
	private Jeu m_jeu;
	private Fenetre m_fenetre;

	private Line m_ligne;
	private Line m_ligneJoueur;
	
	public VueArete(Arete a, float debutX, float debutY, float finX, float finY, Jeu jeu)
	{
		m_arete = a;
		m_jeu = jeu;
		m_fenetre = jeu.getFenetre();

		m_ligne = new Line(debutX, debutY, finX, finY);
		m_ligne.setStrokeWidth(Constants.roadWidth - 4);
		m_ligne.setStroke(Color.GRAY);

		m_ligneJoueur = new Line(debutX, debutY, finX, finY);
		m_ligneJoueur.setStrokeWidth(Constants.roadWidth);

		getChildren().addAll(m_ligneJoueur, m_ligne);

		/***** Evènements sur les objets *****/
		setOnMouseEntered((e) -> {
			m_ligne.setStroke(Color.LIGHTGRAY);
			m_ligneJoueur.setStroke(Color.LIGHTGRAY);
			if (m_jeu.isDeLance())
			{
				if (m_jeu.isConstructionActive())
					m_fenetre.setStatus("Construire une nouvelle arète");
				else
					m_fenetre.setStatus("Cliquez sur le boutton construire si vous souhaitez construire une liaison ici");
			}
		});
		setOnMouseExited((e) -> {
			m_fenetre.resetStatus();
			update();
		});
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				m_jeu.clicArete(m_arete);
			}
		});
		
		update();
	}

	public void update()
	{
		if (m_arete.getType() == TypeArete.Route)
			m_ligne.setStroke(Color.GRAY);
		else if (m_arete.getType() == TypeArete.Autoroute)
			m_ligne.setStroke(Color.BLACK);
		else
			m_ligne.setStroke(Color.GRAY);
		if (m_arete.getProprietaire() != null)
			m_ligneJoueur.setStroke(m_arete.getProprietaire().getCouleur());
		else
			m_ligneJoueur.setStroke(m_ligne.getStroke());
	}

}
