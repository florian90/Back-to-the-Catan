package application;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class Plateau extends AnchorPane
{
	Group hexagons; 
	
	public Plateau()
	{
		super();
		hexagons = new Group();
		drawHexagons();
		getChildren().add(hexagons);
		setTranslateX(200);
		setTranslateY(150);
	}
	

	
	private void drawHexagons()
	{
		for(int i=0; i<7;++i)
		{
			if(i==0 || i==6)
			{
				for(int j=0;j<4;++j)
				{
					if(i==0)
					{
						hexagons.getChildren().add(new Hexagon(3*(Constants.hexWidth/2)+j*Constants.hexWidth, 0,6));
					}
					else
					{
						hexagons.getChildren().add(new Hexagon(3*(Constants.hexWidth/2)+j*Constants.hexWidth, 540,6));

					}
					
				}
				
			}
			else if(i==2 || i==5)
			{
				for(int j=0;j<5;++j)
				{
					if(i==2)
					{
						hexagons.getChildren().add(new Hexagon(2*(Constants.hexWidth/2)+j*Constants.hexWidth, 90,6));
					}
					else
					{
						hexagons.getChildren().add(new Hexagon(2*(Constants.hexWidth/2)+j*Constants.hexWidth, 450,6));
					}
					
				}
			}
			else if (i==3)
			{
				for(int j=0;j<7;++j)
				{
					hexagons.getChildren().add(new Hexagon(j*Constants.hexWidth, 270,6));
				}
			}
			else
			{
				for(int j=0;j<6;++j)
				{
					
					if(i==1)
					{
						hexagons.getChildren().add(new Hexagon(1*(Constants.hexWidth/2)+j*Constants.hexWidth, 180,6));
					}
					else
					{
						hexagons.getChildren().add(new Hexagon(1*(Constants.hexWidth/2)+j*Constants.hexWidth, 360,6));
					}
				}
			}
		}
	}
}
