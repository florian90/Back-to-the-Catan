package vue.jeu;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AnimatedButton extends Button
{
	Timeline tl;
	Boolean blinking;
	BoxBlur bb;
	Bloom bloom;
	
	public AnimatedButton(String text)
	{
		super(text);
		blinking = false;
		this.setId("defaultButton");

		System.out.println(styleProperty().toString());
	}
	
	public void startBlinking()
	{
		
		bb =new BoxBlur(10, 10, 10);
		bloom = new Bloom(10);
		setEffect(bb);
		setId("blinkingButton");
		//remettre le bon skin de bouton après le deuxième clic
		setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), new Insets(2))));
		
		
		tl = new Timeline();
		tl.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO,
						new KeyValue(bb.heightProperty(), 10),
						new KeyValue(bb.widthProperty(), 10)
						),
				new KeyFrame(new Duration(500),
						new KeyValue(bb.heightProperty(), 0),
						new KeyValue(bb.widthProperty(), 0)
						),
				new KeyFrame(new Duration(750),
						new KeyValue(bb.heightProperty(), 0),
						new KeyValue(bb.widthProperty(), 0)
						)
				
				);
		/*tl = new Timeline();
		tl.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO,
						new KeyValue(bloom.thresholdProperty(), 0.01)
						),
				new KeyFrame(new Duration(500),
						new KeyValue(bloom.thresholdProperty(), 1)
						),
				new KeyFrame(new Duration(750),
						new KeyValue(bloom.thresholdProperty(), 1)
						)
				
				);*/
		
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.setAutoReverse(true);
		tl.play();
		blinking = true;
		
	}
	
	public void stopBlinking()
	{
		if (tl != null)
		{
			tl.stop();
			//setSkin(createDefaultSkin());
			bb.setHeight(0);
			bb.setWidth(0);
			//setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), new Insets(2))));
			blinking = false;
			this.setId("defaultButton");
		}
	}

	public boolean isBlinking() 
	{
		return blinking;
	}
}
