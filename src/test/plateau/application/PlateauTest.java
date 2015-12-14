package test.plateau.application;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class PlateauTest extends AnchorPane
{
	Group ViewCases;
	Group routes;
	
	public PlateauTest(int x, int y)
	{
		super();
		ViewCases = new Group();
		routes = new Group();
		//drawViewCases();

		getChildren().add(ViewCases);
		getChildren().add(routes);
		setTranslateX(x);
		setTranslateY(y);
	}


	/*
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
							ViewCases.getChildren().add(new ViewCase(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4,6));
							addRoutes(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4);
						}
						else
						{
							ViewCases.getChildren().add(new ViewCase(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4,6));
							addRoutes(3*(Constants.hexWidth/2)+j*Constants.hexWidth, i*Constants.hexWidth*3/4);
						}

					}

				}
				else if(i==1 || i==5)
				{
					for(int j=0;j<5;++j)
					{
						float x = 2*(Constants.hexWidth/2)+j*Constants.hexWidth;
						float y =  i*Constants.hexWidth*3/4;
						if(i==1)
						{
							ViewCases.getChildren().add(new ViewCase(x,y,6));
							addRoutes(x,y);
						}
						else
						{
							ViewCases.getChildren().add(new ViewCase(x,y,6));
							addRoutes(x, y);
						}

					}
				}
				else if (i==3)
				{
					for(int j=0;j<7;++j)
					{
						float x = j*Constants.hexWidth;
						float y = i*Constants.hexWidth*3/4;
						ViewCases.getChildren().add(new ViewCase(x,y ,6));
						addRoutes(x,y);
					}
				}
				else // i=2 ou 4
				{
					for(int j=0;j<6;++j)
					{
						float x = Constants.hexWidth/2+j*Constants.hexWidth;
						float y = i*Constants.hexWidth*3/4;

						if(i==2)
						{
							ViewCases.getChildren().add(new ViewCase(x, y,6));
							addRoutes(x,y);
						}
						else
						{
							ViewCases.getChildren().add(new ViewCase(x,y,6));
							addRoutes(x,y);
						}
					}
				}
			}
		}
		*/
	private void addRoutes(float x, float y)
	{
		//routes.getChildren().add(new Route(x,y,"/h"));
		//routes.getChildren().add(new Route(x,y,"/b"));
		//routes.getChildren().add(new Route(x,y,"\\h"));
		//routes.getChildren().add(new Route(x,y,"\\b"));
		//routes.getChildren().add(new Route(x,y,"|g"));
		//routes.getChildren().add(new Route(x,y,"|d"));*/
	}
	

}
