package application;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class Plateau extends AnchorPane
{
	Group hexagons;
	Group routes;
	
	public Plateau(int x, int y)
	{
		super();
		hexagons = new Group();
		routes = new Group();
		drawHexagons();
		
		getChildren().add(hexagons);
		getChildren().add(routes);
		setTranslateX(x);
		setTranslateY(y);
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
						hexagons.getChildren().add(new Hexagon(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4,6));
						addRoutes(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4);
					}
					else
					{
						hexagons.getChildren().add(new Hexagon(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4,6));
						addRoutes(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4);
					}
					
				}
				
			}
			else if(i==1 || i==5)
			{
				for(int j=0;j<5;++j)
				{
					int x = 2*(Constants.hexWidth/2)+j*Constants.hexWidth;
					int y =  i*Constants.hexWidth*3/4;
					if(i==1)
					{
						hexagons.getChildren().add(new Hexagon(x,y,6));
						addRoutes(x,y);
					}
					else
					{
						hexagons.getChildren().add(new Hexagon(x,y,6));
						addRoutes(x, y);
					}
					
				}
			}
			else if (i==3)
			{
				for(int j=0;j<7;++j)
				{
					int x = j*Constants.hexWidth;
					int y = i*Constants.hexWidth*3/4;
					hexagons.getChildren().add(new Hexagon(x,y ,6));
					addRoutes(x,y);
				}
			}
			else // i=2 ou 4
			{
				for(int j=0;j<6;++j)
				{
					int x = Constants.hexWidth/2+j*Constants.hexWidth;
					int y = i*Constants.hexWidth*3/4;
					
					if(i==2)
					{
						hexagons.getChildren().add(new Hexagon(x, y,6));
						addRoutes(x,y);
					}
					else
					{
						hexagons.getChildren().add(new Hexagon(x,y,6));
						addRoutes(x,y);
					}
				}
			}
		}
	}
	
	private void addRoutes(int x,int y)
	{
		//routes.getChildren().add(new Route(x,y,"/h"));
		//routes.getChildren().add(new Route(x,y,"/b"));
		//routes.getChildren().add(new Route(x,y,"\\h"));
		//routes.getChildren().add(new Route(x,y,"\\b"));
		//routes.getChildren().add(new Route(x,y,"|g"));
		//routes.getChildren().add(new Route(x,y,"|d"));*/
	}
	
	
}
