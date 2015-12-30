package vue.jeu.plateau;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

public class VueEchange extends TitledPane{
	
	public VueEchange()
	{
		setText("Echange");
		setCollapsible(false);
		setMaxHeight(750);
		setMaxWidth(1000);
		
		GridPane grid = new GridPane();
		
		
		grid.setHgap(20);
		grid.setVgap(20);
		
	//	setAlignment(Pos.CENTER);
		
		Label titre = new Label("Echanges :");
		titre.setId("divisions");
		
		grid.add(titre,0,0);
		grid.add(new Button("Echanger"),0,1);
		
		getChildren().add(grid);
	}

}
