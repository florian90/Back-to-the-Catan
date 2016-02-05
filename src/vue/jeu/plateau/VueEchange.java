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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.joueur.Joueur;
import model.joueur.PackRess;
import model.joueur.Ressource;
import vue.jeu.ContentJoueur;
import vue.jeu.DefaultButton;

public class VueEchange extends TitledPane{
	
	private Joueur jGauche, jDroite;
	private DefaultButton fermer, echanger;
	private AfficheJoueur aj1, aj2;
	private ContentJoueur ctj;
	
	
	public VueEchange(ContentJoueur p_ctj)
	{
		aj1 = new AfficheJoueur();
		aj2 = new AfficheJoueur();
		ctj = p_ctj;
		
		setVisible(false);
		setText("Echange");
		setCollapsible(false);
		setMaxHeight(450);
		setMaxWidth(1000);
		setId("popup");
		
		fermer = new DefaultButton("Fermer");
		echanger = new DefaultButton("Echanger");
		
		HBox contenu = new HBox(aj1,new Separator(Orientation.VERTICAL),aj2);
		contenu.setSpacing(100);
		contenu.setAlignment(Pos.CENTER);
		
		fermer.setAlignment(Pos.BASELINE_RIGHT);
		echanger.setAlignment(Pos.BASELINE_RIGHT);
		HBox boutons = new HBox(fermer,echanger);
		VBox vbox = new VBox(contenu,boutons);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER_RIGHT);
		
		/********************************************\
		**          Gestion des Evenements			**
		\********************************************/
		
		fermer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				hide();
				
			}
		});
		
		echanger.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				PackRess donne = new PackRess();
				PackRess recois = new PackRess();
				
				for(Entry<Ressource, Spinner<Integer>> e : aj1.getQuantites().entrySet())
				{
					donne.add(e.getKey(),e.getValue().getValue());
				}
				
				for(Entry<Ressource, Spinner<Integer>> e : aj2.getQuantites().entrySet())
				{
					recois.add(e.getKey(),e.getValue().getValue());
				}
				
				jGauche.echangerRessources(jDroite, donne, recois);
				
				show(jGauche,jDroite);
				ctj.update(false);
				
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
		private ImageView imgAvatar;
		
		public  AfficheJoueur()
		{
			int i=0;
			quantites = new HashMap<Ressource, Spinner<Integer>>();
			disponibles = new HashMap<Ressource,Label>();
			imgAvatar = new ImageView();
			setHgap(20);
			setVgap(20);
			
			for(Ressource r : Ressource.values())
			{
				if(!(r.equals(null)))
				{
					//Ajout des spinners
					Spinner<Integer> spinTemp = new Spinner<Integer>(0,99,0);
					spinTemp.setPrefWidth(100);
					quantites.put(r,spinTemp);
					add(spinTemp,2,i+3);
					
					//Ajout des labels de type
					add(new Label(Ressource.toString(r)+" :"),0,i+3);
					
					//Ajout des labels de ressources disponibles
					Label lTemp = new Label("0");
					disponibles.put(r, lTemp);
					add(lTemp,1,i+3);
					
					i++;
				}
				
			}
			
			imgAvatar.setFitWidth(50);
			imgAvatar.setPreserveRatio(true);
			add(imgAvatar,0,0);
			
			nomJoueur = new Label("");
			nomJoueur.setId("divisions");
			nomJoueur.setAlignment(Pos.CENTER);
			add(nomJoueur,1,0,3,1);
			
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
		
		public HashMap<Ressource, Spinner<Integer>> getQuantites() {
			return quantites;
		}

		public void updateFields(Joueur j)
		{
			nomJoueur.setText(j.getNom());
			imgAvatar.setImage(new Image(j.getAvatar()));
			
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
