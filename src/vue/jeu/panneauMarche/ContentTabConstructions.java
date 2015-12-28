package vue.jeu.panneauMarche;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import model.jeu.Epoque;
import model.jeu.TypeArete;
import model.jeu.TypePoint;
import model.joueur.Joueur;
import model.joueur.PackRess;

public class ContentTabConstructions extends GridPane {
	Spinner spinRoute;
	Spinner spinAutoroute;
	Spinner spinVillage;
	Spinner spinVille;
	Button acheter;

	PackRess coutTotal;
	Epoque epoque;
	Joueur joueur;

	public ContentTabConstructions()
	{
		coutTotal = new PackRess();
		spinRoute = new Spinner(0, 100, 0);
		spinAutoroute = new Spinner(0, 100, 0);
		spinVillage = new Spinner(0, 100, 0);
		spinVille = new Spinner(0, 100, 0);

		acheter = new Button("Acheter");

		spinRoute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99));
		spinAutoroute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99));
		spinVillage.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99));
		spinVille.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99));

		spinRoute.valueProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue)
			{
				if(!test((int) newValue, (int) spinAutoroute.getValue(), (int) spinVillage.getValue(), (int) spinVille.getValue()))
				{
					spinRoute.getValueFactory().setValue(oldValue);
					System.out.println("pas assez de ressources");
				}
			}
		});

		spinAutoroute.valueProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue)
			{
				if(!test((int) spinRoute.getValue(), (int) newValue, (int) spinVillage.getValue(), (int) spinVille.getValue()))
				{
					spinAutoroute.getValueFactory().setValue(oldValue);
					System.out.println("pas assez de ressources");
				}
			}
		});

		spinVillage.valueProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue)
			{
				if(!test((int) spinRoute.getValue(), (int) spinAutoroute.getValue(), (int) newValue, (int) spinVille.getValue()))
				{
					spinVillage.getValueFactory().setValue(oldValue);
					System.out.println("pas assez de ressources");
				}
			}
		});

		spinVille.valueProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue)
			{
				if(!test((int) spinRoute.getValue(), (int) spinAutoroute.getValue(), (int) spinVillage.getValue(), (int) newValue))
				{
					spinVille.getValueFactory().setValue(oldValue);
					System.out.println("pas assez de ressources");
				}
			}
		});

		acheter.setOnMouseClicked((e)->{
			if((int) spinRoute.getValue() > 0)
				joueur.acheter(TypeArete.Route, (int) spinRoute.getValue());
			if((int) spinAutoroute.getValue() > 0)
				joueur.acheter(TypeArete.Autoroute, (int) spinAutoroute.getValue());
			if((int) spinVillage.getValue() > 0)
				joueur.acheter(TypePoint.Village, (int) spinVillage.getValue());
			if((int) spinVille.getValue() > 0)
				joueur.acheter(TypePoint.Ville, (int) spinVille.getValue());
		});

		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
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
		add(new Label("1xMétal + 1xRS"), 3, 0);
		add(new Label("2xMétal + 1xRS"), 3, 1);
		add(new Label("2xMétal + 2xRS"), 3, 2);
		add(new Label("4xMétal + 3xRS"), 3, 3);
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

	private boolean test(int nbRoute, int nbAutoroute, int nbVillage, int nbVille)
	{
		PackRess cout = new PackRess();
		cout.add(TypeArete.Route.cout(epoque), nbRoute);
		cout.add(TypeArete.Autoroute.cout(epoque), nbAutoroute);
		cout.add(TypePoint.Village.cout(epoque), nbVillage);
		cout.add(TypePoint.Ville.cout(epoque), nbVille);

		return joueur.possede(cout);
	}

	public void setJoueur(Joueur j)
	{
		joueur = j;
	}

	public void setEpoque(Epoque e)
	{
		epoque = e;
	}

	/*
	public class MyChangeListenr implements ChangeListener {
		Achetable m_obj;

		public MyChangeListenr(Achetable obj)
		{
			super();
			m_obj = obj;
		}

		@Override
		public void changed(ObservableValue observable, Object oldValue, Object newValue)
		{
			coutTotal.add(m_obj.cout(Epoque._1855));
		}
	}*/
}
