package vue.jeu.panneauMarche;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import vue.URL;
import vue.jeu.Desactivable;
import vue.jeu.DefaultButton;

public class VueDes extends GridPane implements Desactivable {
	private DefaultButton lancer;
	//private Label de1;
	//private Label de2;
	private ImageView de1;
	private Image imDe1;
	private ImageView de2;
	private Image imDe2;
	private Label somme;
	
	public VueDes()
	{
		setHgap(20);
		setVgap(20);
		
		//de1 = new Label("Dé n° 1 :");
		//de1.setId("divisions");
		//de2 = new Label("Dé n° 2 :");
		//de2.setId("divisions");
		
		
		de1 = new ImageView(URL.chiffresDes[0]);
		de1.setFitWidth(50);
		de1.setPreserveRatio(true);
		de2 = new ImageView(URL.chiffresDes[0]);
		de2.setFitWidth(50);
		de2.setPreserveRatio(true);
		somme = new Label("Somme :");
		somme.setId("divisions");
		lancer = new DefaultButton("Lancer les dés");
		ImageView imgDes = new ImageView(URL.des2);
		
		imgDes.setFitWidth(50);
		imgDes.setPreserveRatio(true);
		add(imgDes, 0, 1);
		add(lancer, 2, 1);
		add(de1, 0, 3, 2, 1);
		add(de2, 2, 3);
		add(somme, 0, 5);
	}
	
	public DefaultButton getLancer()
	{
		return lancer;
	}
	
	/*
	 * Modifie les résultats afichés en fonction
	 * du tableau d'int en parametre
	 */
	public void actualiserResultats(int[] tab)
	{
		de1.setImage(new Image(URL.chiffresDes[tab[0]]));
		de2.setImage((new Image(URL.chiffresDes[tab[1]])));
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
