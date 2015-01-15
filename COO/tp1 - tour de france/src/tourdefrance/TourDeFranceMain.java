package tourdefrance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Collections;

import tourdefrance.util.InitListeCoureurs;

public class TourDeFranceMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws EtapeNonCourueException {

		List<Coureur> lesCoureurs = InitListeCoureurs.SINGLETON.init("/tdf2013/coureurs.txt");
				
		List<Etape> lesEtapes = new ArrayList<Etape>();
		for(int i = 0; i < 21; i++) {
			lesEtapes.add(new Etape(i+1,"/tdf2013/"));
		}	
		
		TourDeFrance tour = new TourDeFrance(lesEtapes,lesCoureurs);
		tour.disputeCourse();
	}

}
