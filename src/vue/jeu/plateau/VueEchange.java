package vue.jeu.plateau;

import java.util.HashMap;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.joueur.Joueur;
import model.joueur.Ressource;

public class VueEchange extends TitledPane{
	
	private Joueur jGauche, jDroite;
	private Button annuler, echanger;
	AfficheJoueur aj1, aj2;
	
	public VueEchange()
	{
		aj1 = new AfficheJoueur();
		aj2 = new AfficheJoueur();
		
		setVisible(false);
		setText("Echange");
		setCollapsible(false);
		setMaxHeight(450);
		setMaxWidth(1000);
		setId("popup");
		
		annuler = new Button("Annuler");
		echanger = new Button("Echanger");
		
		HBox contenu = new HBox(aj1,new Separator(Orientation.VERTICAL),aj2);
		contenu.setSpacing(100);
		contenu.setAlignment(Pos.CENTER);
		
		annuler.setAlignment(Pos.BASELINE_RIGHT);
		echanger.setAlignment(Pos.BASELINE_RIGHT);
		HBox boutons = new HBox(annuler,echanger);
		VBox vbox = new VBox(contenu,boutons);
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER_RIGHT);
		
		/********************************************\
		**          Gestion des Evenements			**
		\********************************************/
		
		annuler.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				hide();
				
			}
		});
		
		echanger.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				
			}
		});
		
		setContent(vbox);

	}
	
	private void setJoueurs(Joueur p_jGauche, Joueur p_jDroite)
	{
		jGauche = p_jGauche;
		jDroite = p_jDroite;
	}
	
	public void show(Joueur j1, Joueur j2)
	{
		setJoueurs(j1,j2);
		aj1.updateFields(j1);
		aj2.updateFields(j2);
		System.out.println(j1.getNom()+j2.getNom());
		setVisible(true);
	}
	
	private void hide()
	{
		setVisible(false);
	}
	
	
	private class AfficheJoueur extends GridPane
	{
		private HashMap<Ressource,Spinner<Integer>> quantites;
		private HashMap<Ressource,Label> disponibles;
		private Label nomJoueur;
		private Joueur joueur;
		
		public  AfficheJoueur()
		{
			int i=0;
			quantites = new HashMap<Ressource, Spinner<Integer>>();
			disponibles = new HashMap<Ressource,Label>();
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
					
					//Ajout des labels de type
					add(new Label(r.toString()+" :"),0,i+3);
					
					//Ajout des labels de ressources disponibles
					Label lTemp = new Label("0");
					disponibles.put(r, lTemp);
					add(lTemp,1,i+3);
					
					i++;
				}
				
			}
			
			nomJoueur = new Label("");
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
		
		public void updateFields(Joueur j)
		{
			nomJoueur.setText(j.getNom());
			
			for(Entry<Ressource,Label> e : disponibles.entrySet())
			{
				e.getValue().setText(""+j.nbRessource(e.getKey()));
			}
			
			for(Entry<Ressource,Spinner<Integer>> e : quantites.entrySet())
			{
				e.getValue().setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.parseInt(disponibles.get(e.getKey()).getText())));
			}
			
		}
	}

}
