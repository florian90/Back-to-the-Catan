package test.plateau.application;

import javafx.scene.Group;

public class MenuContextuelStyleeee extends Group {/*
	Jeu m_jeu;
	Fenetre m_fenetre;

	Arc arc;
	ImageView imageVille = new ImageView("textures/Ville.jpg");
	ImageView imageVillage = new ImageView("textures/Village.jpg");

	public MenuContextuelStyleeee(Jeu jeu, Fenetre fenetre)
	{
		m_jeu = jeu;
		m_fenetre = fenetre;

		arc = new Arc();
		arc.setRadiusX(Constants.arcWidth);
		arc.setRadiusY(Constants.arcHeight);
		arc.setStartAngle(-15);
		arc.setLength(210);
		arc.setType(ArcType.OPEN);
		arc.setFill(Color.TRANSPARENT);
		arc.setStroke(Color.RED);
		arc.setStrokeWidth(5);/*
		imageVille.setFitWidth(50);
		imageVille.setFitHeight(50);
		imageVille.setOnMouseClicked((e)->{
			System.out.println("Clicked on image");
		});
		imageVille.setOnMouseEntered((e)->{
			System.out.println("Construire");
		});
		imageVillage.setOnMouseClicked((e)->{
			System.out.println("Clicked on image");
		});
		imageVillage.setOnMouseEntered((e)->{
			System.out.println("Construire");
		});
	}

	public void showMenu(VuePoint vuePoint)
	{
		int x = vuePoint.getX();
		int y = vuePoint.getY();

		arc.setCenterX(x);
		arc.setCenterY(y);

		getChildren().add(arc);
		if(vuePoint.getPoint().getType() == TypePoint.Vide)
		{
			imageVillage.setX(x);
			imageVillage.setY(y);
			imageVillage.setOnMouseClicked((e)->{
				m_jeu.getJoueur().construirePoint(TypePoint.Village, vuePoint.getPoint());
				getChildren().removeAll(arc, imageVillage);
			});
			getChildren().add(imageVillage);
		}
		if(vuePoint.getPoint().getType() == TypePoint.Village)
		{
			imageVille.setX(x);
			imageVille.setY(y);
			imageVille.setOnMouseClicked((e)->{
				m_jeu.getJoueur().construirePoint(TypePoint.Ville, vuePoint.getPoint());
				getChildren().removeAll(arc, imageVille);
			});
			getChildren().add(imageVille);
		}
	}*/
}
