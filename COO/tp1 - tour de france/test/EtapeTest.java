package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tourdefrance.Coureur;
import tourdefrance.Etape;
import tourdefrance.EtapeNonCourueException;
import tourdefrance.Resultat;
import tourdefrance.Temps;
import tourdefrance.util.InitListeCoureurs;

public class EtapeTest {

	private static Etape etape;
	private static Coureur c1;
	private static Coureur c4;
	private static Coureur c5;
	
	/**
	 * On fait disputer l'étape 1 systématiquement, les autres méthodes n'ayant d'effet qu'une fois cette méthos exécutée (et donc validée)
	 * Le test se base donc sur l'existence et les contenus des fichiers coureurs.txt et etape1.txt 
	 */
	@BeforeClass
	public static void before()  {
		etape = new Etape(1,"/data/");
		List<Coureur> lesCoureurs =  InitListeCoureurs.SINGLETON.init("/data/coureurs.txt");
		c1 = lesCoureurs.get(0); // licence001
		c4 = lesCoureurs.get(3); // licence004
		c5 = lesCoureurs.get(4); // licence005
		etape.disputer(lesCoureurs);
	}

	@Test
	public void testGetNumero() {
		assertEquals("numero 1",1,etape.getNumero());
	}

	@Test
	public void testDisputer() {
		// le test réussi si la méthode before() a été réussie
		assertTrue(true);
	}

	@Test
	public void testResultats() throws EtapeNonCourueException {

		Map<Coureur,Resultat> res = etape.resultats();
		
		assertEquals("seulement 4 coureurs à l'arrivée ?",res.size(),4);
		
		// on ne teste ici que la bonne validation des résultats de c1 et c5		
		Resultat r1 = res.get(c1);
		assertEquals("resultat valide",new Resultat(new Temps(1,15,23),0,1),r1);
		Resultat r5 = res.get(c5);
		assertEquals("resultat valide",new Resultat(new Temps(1,18,00),0,5),r5);
	}

	@Test(expected=EtapeNonCourueException.class) 
	public void testEtapeNonCourueResultats() throws EtapeNonCourueException {
		Etape etape2 = new Etape(2,"/data/");
		etape2.resultats();
	}
	
	@Test
	public void testAbandons() throws EtapeNonCourueException {
		List<Coureur> abandons = etape.abandons();
		assertEquals("seulement 1 abandon ?",1,abandons.size());
		// uniquement c4 abandonné
		assertEquals("abandon de c4",abandons.get(0),c4);
	}

	@Test(expected=EtapeNonCourueException.class) 
	public void testEtapeNonCourueAbandons() throws EtapeNonCourueException {
		Etape etape2 = new Etape(2,"/data/");
		etape2.abandons();
	}

	
	// 
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("test.EtapeTest");
	}
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(test.EtapeTest.class);
	}
	
}
