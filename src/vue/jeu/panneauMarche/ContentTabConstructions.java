package vue.jeu.panneauMarche;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import model.jeu.Epoque;
import model.jeu.Jeu;
import model.jeu.TypeArete;
import model.jeu.TypePoint;
import model.joueur.Joueur;
import model.joueur.Ressource;
import vue.jeu.ContentJoueur;
import vue.jeu.Desactivable;

public class ContentTabConstructions extends GridPane implements Desactivable {
	private Spinner<Integer> spinRoute; 
	private Spinner <Integer> spinAutoroute;
	private Spinner <Integer> spinVillage;
	private Spinner <Integer> spinVille;
	private Button  acheter;
	private Jeu m_jeu;
	private	Epoque epoque;
	private	Joueur joueur;

	Label coutRoute;
	Label coutAutoroute;
	Label coutVillage;
	Label coutVille;
	
	public ContentTabConstructions(Jeu jeu,ContentJoueur ctj)
	{
		m_jeu = jeu; 
		spinRoute = new Spinner<Integer>(0, 100, 0);
		spinAutoroute = new Spinner <Integer>(0, 100, 0);
		spinVillage = new Spinner <Integer>(0, 100, 0);
		spinVille = new Spinner <Integer>(0, 100, 0);

		coutAutoroute = new Label("- 2 x Métal\n- 1 x "+Ressource.toString(Epoque.getR1(m_jeu.getEpoqueActuelle())));
		coutRoute = new Label("- 1 x Métal\n- 1 x "+Ressource.toString(Epoque.getR2(m_jeu.getEpoqueActuelle())));
		coutVillage = new Label("- 2 x Métal\n- 2 x "+Ressource.toString(Epoque.getR2(m_jeu.getEpoqueActuelle())));
		coutVille = new Label("- 4 x Métal\n- 3 x "+Ressource.toString(Epoque.getR2(m_jeu.getEpoqueActuelle())));
		
		acheter = new Button("Acheter");


		spinRoute.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {

				if(!(m_jeu.testAchatConstructions(newValue, spinAutoroute.getValue(), spinVillage.getValue(), spinVille.getValue(),epoque,joueur)))
				{
					spinRoute.getValueFactory().setValue(oldValue);
				}

			}
		});

		spinAutoroute.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue)
			{
				if(!(m_jeu.testAchatConstructions(spinRoute.getValue(),newValue, spinVillage.getValue(),spinVille.getValue(),epoque,joueur)))
				{
					spinAutoroute.getValueFactory().setValue(oldValue);
				}
			}
		});

		spinVillage.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				if(!(m_jeu.testAchatConstructions(spinRoute.getValue(), spinAutoroute.getValue(), newValue,  spinVille.getValue(),epoque,joueur)))
				{
					spinVillage.getValueFactory().setValue(oldValue);
				}

			}
		});

		spinVille.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				if(!(m_jeu.testAchatConstructions(spinRoute.getValue(), spinAutoroute.getValue(), spinVillage.getValue(), newValue,epoque,joueur)))
				{
					spinVille.getValueFactory().setValue(oldValue);
				}
			}
		});

		acheter.setOnMouseClicked((e)->{
			if( spinRoute.getValue() > 0)
				joueur.acheter(TypeArete.Route, spinRoute.getValue());
			if( spinAutoroute.getValue() > 0)
				joueur.acheter(TypeArete.Autoroute, spinAutoroute.getValue());
			if( spinVillage.getValue() > 0)
				joueur.acheter(TypePoint.Village, spinVillage.getValue());
			if( spinVille.getValue() > 0)
				joueur.acheter(TypePoint.Ville,  spinVille.getValue());

			ctj.update();
			reset();
		});

		setPadding(new Insets(20,5,20,5));
		setHgap(5);
		setVgap(15);

		add(new Label("Route"), 0, 0);
		add(new Label("Autoroute"), 0, 1);
		add(new Label("Village"), 0, 2);
		add(new Label("Ville"), 0, 3);

		spinRoute.setPrefWidth(50);
		spinAutoroute.setPrefWidth(50);
		spinVillage.setPrefWidth(50);
		spinVille.setPrefWidth(50);
		add(spinRoute, 2, 0);
		add(spinAutoroute, 2, 1);
		add(spinVillage, 2, 2);
		add(spinVille, 2, 3);
		add(coutRoute, 3, 0);
		add(coutAutoroute, 3, 1);
		add(coutVillage, 3, 2);
		add(coutVille, 3, 3);
		add(acheter, 3, 4);

		reset();
	}

	public void reset()
	{
		spinRoute.getValueFactory().setValue(0);
		spinAutoroute.getValueFactory().setValue(0);
		spinVillage.getValueFactory().setValue(0);
		spinVille.getValueFactory().setValue(0);
	}

	

	public void setJoueur(Joueur j)
	{
		joueur = j;
	}

	public void setEpoque(Epoque e)
	{
		epoque = e;
	}

	@Override
	public void desactiver() {

		acheter.setDisable(true);

	}

	@Override
	public void activer() {

		acheter.setDisable(false);

	}
	
	public void update()
	{
		coutAutoroute.setText("- 2 x Métal\n- 1 x "+Ressource.toString(Epoque.getR1(m_jeu.getEpoqueActuelle())));
		coutRoute.setText("- 1 x Métal\n- 1 x "+Ressource.toString(Epoque.getR2(m_jeu.getEpoqueActuelle())));
		coutVillage.setText("- 2 x Métal\n- 2 x "+Ressource.toString(Epoque.getR2(m_jeu.getEpoqueActuelle())));
		coutVille.setText("- 4 x Métal\n- 3 x "+Ressource.toString(Epoque.getR2(m_jeu.getEpoqueActuelle())));
	}

}
