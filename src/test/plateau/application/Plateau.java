package test.plateau.application;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class Plateau extends AnchorPane
{
	Group viewCases;
	Group routes;
	
	public Plateau(int x, int y)
	{
		super();
		viewCases = new Group();
		routes = new Group();
		drawViewCases();
		
		getChildren().add(viewCases);
		getChildren().add(routes);
		setTranslateX(x);
		setTranslateY(y);
	}
	

	
	private void drawViewCases()
	{
		for(int i=0; i<7;++i)
		{
			if(i==0 || i==6)
			{
				for(int j=0;j<4;++j)
				{
					if(i==0)
					{
						viewCases.getChildren().add(new ViewCase(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4,6));
						addRoutes(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4);
					}
					else
					{
						viewCases.getChildren().add(new ViewCase(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4,6));
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
						viewCases.getChildren().add(new ViewCase(x,y,6));
						addRoutes(x,y);
					}
					else
					{
						viewCases.getChildren().add(new ViewCase(x,y,6));
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
					viewCases.getChildren().add(new ViewCase(x,y ,6));
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
						viewCases.getChildren().add(new ViewCase(x, y,6));
						addRoutes(x,y);
					}
					else
					{
						viewCases.getChildren().add(new ViewCase(x,y,6));
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
