package vue;

import com.sun.istack.internal.NotNull;
import model.joueur.Invention;
import model.joueur.Ressource;

public class URL {
	private static final String texturesPath = "textures/";
	public static final String avatars[] = {texturesPath +"Avatar1.jpg", texturesPath +"Avatar2.jpg", texturesPath +"Avatar3.jpg", texturesPath +"Avatar4.jpg"};
	public static final String carte = texturesPath +"carteBiff.png";
	public static final String convecteur = texturesPath +"convecteur.gif";
	public static final String des = texturesPath +"des.png";
	public static final String des2 = texturesPath +"dice.jpg";
	public static final String icon = texturesPath +"favicon.jpg";
	public static final String bg_menu = texturesPath +"fondMenu.jpg";
	public static final String voyantVert = texturesPath +"voyantVert.png";
	public static final String voyantRouge = texturesPath +"voyantRouge.png";
	
	// hexagon
	public static final String hexAimant = texturesPath +"hexAimant.png";
	//...

	public static final String hoverboard = texturesPath +"hoverboard.jpg";
	public static final String logo = texturesPath +"logo.psd";
	public static final String radio = texturesPath +"radio.jpg";
	public static final String train = texturesPath +"train.jpg";
	public static final String village = texturesPath +"village.png";
	public static final String ville = texturesPath +"ville.png";
	public static final String voleur = texturesPath +"voleur.png";
	
	public static final String chiffresDes[] = {texturesPath + "/Des/"+"de0.png",texturesPath + "/Des/"+"de1.png",texturesPath + "/Des/"+"de2.png",texturesPath + "/Des/"+"de3.png",
			texturesPath + "/Des/"+"de4.png",texturesPath + "/Des/"+"de5.png",texturesPath + "/Des/"+"de6.png"};
	
	public static final String son_zeus = "bin/sons/nomdezeus.mp3";
	public static final String video_fin = "bin/sons/VideoFinLO.mp4";

	public static final String dispositionPlateau_ressources1 = "ressources/PlateauxInitiaux/plateau1.txt";
	public static final String dispositionPlateau_ressources2 = "ressources/PlateauxInitiaux/plateau2.txt";
	public static final String dispositionPlateau_valeurs = "ressources/PlateauxInitiaux/plateau1valeurs.txt";

	public static String url(Ressource r)
	{
		if(r==null)
			return texturesPath+"hexAutoroute.png";
		return texturesPath+"hex"+ r + ".png";
	}

	@NotNull
	public static String url(Invention i)
	{
		switch (i)
		{
			case ConvecteurTemporel:return convecteur;
			case Train:return train;
			case Radio:return radio;
			default:return hoverboard;
		}
	}
}
