package vue.jeu.plateau;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import model.jeu.Case;
import model.jeu.Jeu;
import model.jeu.Point;
import model.jeu.coordonnee.CoordPoint;
import model.joueur.Ressource;
import vue.URL;
import vue.jeu.Constants;

public class ViewCase extends Group {

	private Case m_case;
	private ImageView imgRessource;
	private Group rondNum;
	private Jeu m_jeu;

	public ViewCase(float p_x, float p_y, Case p_case, Jeu jeu)
	{
		super();
		m_jeu = jeu;
		m_case = p_case;
		rondNum = new Group();
		setTranslateX(p_x + Constants.roadWidth/2);
		setTranslateY(p_y + Constants.roadWidth/2);

		/*Affichage de l'image correspondant à la ressource*/
		imgRessource = new ImageView(URL.url(m_case.getRessource()));
		imgRessource.setFitHeight(Constants.hexHeight - Constants.roadWidth);
		imgRessource.setFitWidth(Constants.hexWidth - Constants.roadWidth);
		setImageVoleur(m_case.isVoleurPresent());

		getChildren().add(imgRessource);

		if (m_case.getRessource() != null)//on ajoute un numéro que s'il ne s'agit pas d'une case autoroute
		{

			/*Affichage du numéro*/

				double milieuX = imgRessource.getFitWidth()/2;
				double milieuY = imgRessource.getFitHeight()/2;
				Circle cercleNoir = new Circle(milieuX, milieuY + 15, 10, Color.BLACK);
				Circle cercleBlanc = new Circle(milieuX, milieuY + 15, 9, Color.WHITE);
				Label numero = new Label("" + m_case.getValeur());
				numero.setTranslateX(milieuX - numero.getWidth()/2);
				numero.setTranslateY(milieuY + numero.getHeight()/2);
				numero.setTextAlignment(TextAlignment.CENTER);
				numero.setId("labelNum");

				//rondNum.getChildren().add(cercleNoir);
				//rondNum.getChildren().add(cercleBlanc);
				rondNum.getChildren().add(numero);// modifier le fichier .css pour changer l'aspect des chiffres (bloc #labelNum)
				
				getChildren().add(rondNum);
			
		}
		
		 /****************************/
		/***Gestion des evenements***/
	   /****************************/
		setOnMouseClicked(e->{m_jeu.clicCase(m_case, (ViewCase)e.getSource());});

		setOnMouseEntered(e->{
			
			if(m_case.isVoleurPresent())
			{
				setImageVoleur(false);
			}
			rondNum.setVisible(false);
		});
		
		setOnMouseExited(e->{
			
			if(m_case.isVoleurPresent())
			{
				setImageVoleur(true);
			}
			rondNum.setVisible(true);
			
		});
	}
	
	public void setImageVoleur(boolean voleurPresent)
	{
		if(voleurPresent)
			imgRessource.setImage(new Image(URL.voleur));
		else
			imgRessource.setImage(new Image(URL.url(m_case.getRessource())));
	}

	public double getCenterX()
	{
		return getTranslateX() + imgRessource.getFitWidth()/2;
	}

	public double getCenterY()
	{
		return getTranslateY() + imgRessource.getFitHeight()/2;
	}

	/**
	 * Crée un annimation sur la tuile affichant la ressource qui se déplace vers le village / ville du joueur
	 * @param point : VuePoint vers lequel on se dirige
	 */
	public void animerCase(VuePoint point)
	{
		if(!m_case.getCoo().getEpoque().equals(m_jeu.getEpoqueActuelle()))
			return;// Animation quei ne serait pas affichée
		// Crée une nouvelle image
		Scale scale = new Scale();
		Translate translate = new Translate();
		ImageView imageView = new ImageView(imgRessource.getImage());
		imageView.setFitWidth(Constants.hexWidth);
		imageView.setFitHeight(Constants.hexHeight);
		imageView.getTransforms().addAll(translate, scale);
		getChildren().add(imageView);

		// Calcul des points d'arrivée
		double x = point.getCenterX() - getCenterX() + Constants.hexWidth/2;
		double y = point.getCenterY() - getCenterY() + Constants.hexHeight/2;

		// Crée l'animation
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(translate.xProperty(), 0)),
				new KeyFrame(Duration.ZERO, new KeyValue(translate.yProperty(), 0)),
				new KeyFrame(Duration.ZERO, new KeyValue(scale.xProperty(), .6)),
				new KeyFrame(Duration.ZERO, new KeyValue(scale.yProperty(), .6)),
				new KeyFrame(new Duration(1000), new KeyValue(translate.xProperty(), x)),
				new KeyFrame(new Duration(1000), new KeyValue(translate.yProperty(), y)),
				new KeyFrame(new Duration(1000), new KeyValue(scale.xProperty(), .1)),
				new KeyFrame(new Duration(1000), new KeyValue(scale.yProperty(), .1))
		);
		timeline.play();
	}
}
