package grafica;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import progettoPoker.Carta;

public final class MazzoGrafico {
     

	   static File Immagini = new File("Immagini");
	   
	   static File Carte = new File(Immagini.getAbsolutePath() + "\\Carte");

	
		public static ImageIcon uno = new ImageIcon(Carte.getAbsolutePath() + "\\1.png");// 2 di cuori
		public static ImageIcon due = new ImageIcon(Carte.getAbsolutePath() + "\\2.png");// 3 di cuori
		public static ImageIcon tre = new ImageIcon(Carte.getAbsolutePath() + "\\3.png");// 4 di cuori
		public static ImageIcon quattro = new ImageIcon(Carte.getAbsolutePath() + "\\4.png");// 5 di cuori
		public static ImageIcon cinque = new ImageIcon(Carte.getAbsolutePath() + "\\5.png");// 6 di cuori
		public static ImageIcon sei = new ImageIcon(Carte.getAbsolutePath() + "\\6.png");// 7 di cuori
		public static ImageIcon sette = new ImageIcon(Carte.getAbsolutePath() + "\\7.png");// 8 di cuori
		public static ImageIcon otto = new ImageIcon(Carte.getAbsolutePath() + "\\8.png");// 9 di cuori
		public static ImageIcon nove = new ImageIcon(Carte.getAbsolutePath() + "\\9.png");// 10 di cuori
		public static ImageIcon dieci = new ImageIcon(Carte.getAbsolutePath() + "\\10.png");// J di cuori
		public static ImageIcon undici = new ImageIcon(Carte.getAbsolutePath() + "\\11.png");// Q di cuori
		public static ImageIcon dodici = new ImageIcon(Carte.getAbsolutePath() + "\\12.png");// K di cuori
		public static ImageIcon tredici = new ImageIcon(Carte.getAbsolutePath() + "\\13.png");// Asso di cuori
		public static ImageIcon quattordici = new ImageIcon(Carte.getAbsolutePath()+ "\\14.png"); // 2 di quadri
		public static ImageIcon quindici = new ImageIcon(Carte.getAbsolutePath() + "\\15.png"); // 3 di quadri
		public static ImageIcon sedici = new ImageIcon(Carte.getAbsolutePath() + "\\16.png"); // 4 di quadri
		public static ImageIcon diciassette = new ImageIcon(Carte.getAbsolutePath()+ "\\17.png");// 5 di quadri
		public static ImageIcon diciotto = new ImageIcon(Carte.getAbsolutePath() + "\\18.png");// 6 di quadri
		public static ImageIcon diciannove = new ImageIcon(Carte.getAbsolutePath()+ "\\19.png");// 7 di quadri
		public static ImageIcon venti = new ImageIcon(Carte.getAbsolutePath() + "\\20.png");// 8 di quadri
		public static ImageIcon ventuno = new ImageIcon(Carte.getAbsolutePath() + "\\21.png");// 9 di quadri
		public static ImageIcon ventidue = new ImageIcon(Carte.getAbsolutePath() + "\\22.png");// 10 di quadri
		public static ImageIcon ventitre = new ImageIcon(Carte.getAbsolutePath() + "\\23.png");// J di quadri
		public static ImageIcon ventiquattro = new ImageIcon(Carte.getAbsolutePath()+ "\\24.png");// Q di quadri
		public static ImageIcon venticinque = new ImageIcon(Carte.getAbsolutePath()+ "\\25.png");// K di quadri
		public static ImageIcon ventisei = new ImageIcon(Carte.getAbsolutePath() + "\\26.png");// Asso di quadri
		public static ImageIcon ventisette = new ImageIcon(Carte.getAbsolutePath()+ "\\27.png"); // 2 di fiori
		public static ImageIcon ventotto = new ImageIcon(Carte.getAbsolutePath() + "\\28.png"); // 3 di fiori
		public static ImageIcon ventinove = new ImageIcon(Carte.getAbsolutePath()+ "\\29.png"); // 4 di fiori
		public static ImageIcon trenta = new ImageIcon(Carte.getAbsolutePath() + "\\30.png");// 5 di fiori
		public static ImageIcon trentuno = new ImageIcon(Carte.getAbsolutePath() + "\\31.png");// 6 di fiori
		public static ImageIcon trentadue = new ImageIcon(Carte.getAbsolutePath()+ "\\32.png");// 7 di fiori
		public static ImageIcon trentatre = new ImageIcon(Carte.getAbsolutePath()+ "\\33.png");// 8 di fiori
		public static ImageIcon trentaquattro = new ImageIcon(Carte.getAbsolutePath()+ "\\34.png");// 9 di fiori
		public static ImageIcon trentacinque = new ImageIcon(Carte.getAbsolutePath()+ "\\35.png");// 10 di fiori
		public static ImageIcon trentasei = new ImageIcon(Carte.getAbsolutePath()+ "\\36.png");// J di fiori
		public static ImageIcon trentasette = new ImageIcon(Carte.getAbsolutePath()+ "\\37.png");// Q di fiori
		public static ImageIcon trentotto = new ImageIcon(Carte.getAbsolutePath()+ "\\38.png");// K di fiori
		public static ImageIcon trentanove = new ImageIcon(Carte.getAbsolutePath()+ "\\39.png");// Asso di fiori
		public static ImageIcon quaranta = new ImageIcon(Carte.getAbsolutePath() + "\\40.png"); // 2 di picche
		public static ImageIcon quarantuno = new ImageIcon(Carte.getAbsolutePath()+ "\\41.png"); // 3 di picche
		public static ImageIcon quarantadue = new ImageIcon(Carte.getAbsolutePath()+ "\\42.png"); // 4 di picche
		public static ImageIcon quarantatre = new ImageIcon(Carte.getAbsolutePath()+ "\\43.png");// 5 di picche
		public static ImageIcon quarantaquattro = new ImageIcon(Carte.getAbsolutePath()+ "\\44.png");// 6 di picche
		public static ImageIcon quarantacinque = new ImageIcon(Carte.getAbsolutePath()+ "\\45.png");// 7 di picche
		public static ImageIcon quarantasei = new ImageIcon(Carte.getAbsolutePath()+ "\\46.png");// 8 di picche
		public static ImageIcon quarantasette = new ImageIcon(Carte.getAbsolutePath()+ "\\47.png");// 9 di picche
		public static ImageIcon quarantotto = new ImageIcon(Carte.getAbsolutePath()+ "\\48.png");// 10 di picche
		public static ImageIcon quarantanove = new ImageIcon(Carte.getAbsolutePath()+ "\\49.png");// J di picche
		public static ImageIcon cinquanta = new ImageIcon(Carte.getAbsolutePath()+ "\\50.png");// Q di picche
		public static ImageIcon cinquantuno = new ImageIcon(Carte.getAbsolutePath()+ "\\51.png");// K di picche
		public static ImageIcon cinquantadue = new ImageIcon(Carte.getAbsolutePath()+ "\\52.png");// Asso di picche
		

		public static ImageIcon getCarta(int i)
		{
			switch(i)
			{
			case 1: return uno;
			case 2: return due;
			//TODO
			default: return cinquantadue;
			}
		}
}
