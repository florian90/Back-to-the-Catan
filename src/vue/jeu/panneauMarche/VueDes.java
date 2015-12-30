package vue.jeu.panneauMarche;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import vue.jeu.Desactivable;

public class VueDes extends GridPane implements Desactivable {
	private Button lancer;
	private Label de1;
	private Label de2;
	private Label somme;
	
	public VueDes()
	{
		setHgap(20);
		setVgap(20);
		
		de1 = new Label("Dé n° 1 :");
		de2 = new Label("Dé n° 2 :");
		somme = new Label("Somme :");
		lancer = new Button("Lancer les dés");
		ImageView imgDes = new ImageView("textures/dice.jpg");
		
		imgDes.setFitWidth(50);
		imgDes.setPreserveRatio(true);
		add(imgDes, 0, 1);
		add(lancer, 2, 1);
		add(de1, 0, 3, 2, 1);
		add(de2, 2, 3);
		add(somme, 0, 5);
	}
	
	public Button getLancer()
	{
		return lancer;
	}
	
	/*
	 * Modifie les résultats afichés en fonction
	 * du tableau d'int en parametre
	 */
	public void actualiserResultats(int[] tab)
	{
		de1.setText("Dé n° 1 : " + tab[0]);
		de2.setText("Dé n° 2 : " + tab[1]);
		somme.setText("Somme : " + (tab[0]+tab[1]));
	}
	
	/*
	 * Permet d'activer ou desactiver le lancer
	 * de dés.
	 */

	@Override
	public void desactiver() {
		
		lancer.setDisable(true);
	}

	@Override
	public void activer() {
		lancer.setDisable(false);
	}

}
