package test.plateau.application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class VueDes extends GridPane
{
	private Button lancer;
	private Label de1;
	private Label de2;
	private Label somme;
	
	public VueDes()
	{
		setHgap(20);
		setVgap(20);
		
		de1 = new Label("D� n� 1 :");
		de2 = new Label("D� n� 2 :");
		somme = new Label("Somme :");
		lancer = new Button("Lancer les d�s");
		ImageView imgDes = new ImageView("textures/dice.jpg");
		
		imgDes.setFitWidth(50);
		imgDes.setPreserveRatio(true);
		add(imgDes,0,1);
		add(lancer,2,1);
		add(de1,0,3,2,1);
		add(de2,2,3);
		add(somme,0,5);
	}
	
	public Button getLancer()
	{
		return lancer;
	}
	
	/*
	 * Modifie les r�sultats afich�s en fonction
	 * du tableau d'int en parametre
	 */
	public void actualiserResultats(int[] tab)
	{
		de1.setText("D� n� 1 : "+tab[0]);
		de2.setText("D� n� 2 : "+tab[1]);
		somme.setText("Somme : "+tab[2]);
	}
	
	/*
	 * Permet d'activer ou desactiver le lancer
	 * de d�s.
	 */
	public void setEnabled(boolean b)
	{
		lancer.setDisable(!b);
	}

}
