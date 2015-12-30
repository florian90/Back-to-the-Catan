package vue.jeu.plateau;

import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.joueur.Joueur;
import model.joueur.Ressource;

public class VueEchange extends TitledPane{
	
	private Joueur jGauche, jDroite;
	
	public VueEchange()
	{
		setText("Echange");
		setCollapsible(false);
		setMaxHeight(750);
		setMaxWidth(1000);
		setId("popup");
		
		HBox hbox = new HBox(new AfficheJoueur(jGauche),new AfficheJoueur(jDroite));
		hbox.setSpacing(100);
		hbox.setAlignment(Pos.CENTER);
		setContent(hbox);

	}
	
	private void setJoueurs(Joueur p_jGauche, Joueur p_jDroite)
	{
		jGauche = p_jGauche;
		jDroite = p_jDroite;
	}
	
	public void show(Joueur j1, Joueur j2)
	{
		setJoueurs(j1,j2);
		updateFields();
		//setVisible(true);
	}
	
	private void updateFields()
	{
		
	}
	
	private class AfficheJoueur extends GridPane
	{
		private HashMap<Ressource,Spinner<Integer>> quantites;
		
		public  AfficheJoueur(Joueur j)
		{
			int i=0;
			quantites = new HashMap<Ressource, Spinner<Integer>>();
			setHgap(20);
			setVgap(20);
			
			for(Ressource r : Ressource.values())
			{
				if(!(r.equals(Ressource.Autoroute)))
				{
					//Ajout des spinners
					Spinner<Integer> spinTemp = new Spinner<Integer>(0,99,0);
					quantites.put(r,spinTemp);
					add(spinTemp,2,i+3);
					
					//Ajout des labels
					add(new Label(r.toString()+" :"),0,i+3);
					
					i++;
				}
				
			}
			
			Label nomJoueur = new Label("Coucou");
			nomJoueur.setId("divisions");
			nomJoueur.setAlignment(Pos.CENTER);
			add(nomJoueur,0,0,3,1);
			
			Label lType = new Label("Type");
			lType.setId("divisions");
			add(lType,0,1);
			
			Label lDispo = new Label("Disponibles");
			lDispo.setId("divisions");
			add(lDispo,1,1);
			
			Label lEchange = new Label("A Echanger");
			lEchange.setId("divisions");
			add(lEchange,2,1);
			
			
			
			
			
			
			
			
		}
	}

}
