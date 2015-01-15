package generics;

import scanner.*;
import java.util.*;

public class ListChoserLegume {

    // methode chose
    // elle est similaire a celle de ListChoser simplement elle ne fonctionne
    // qu'avec des listes d'objets de type Legume (cf. interface Legume)
    // quel changement apporter a la methode de ListChoser  ?
    
    public <T extends Legume> T chose(String s, List<T> l) {
    	System.out.println("0 -> aucun");
    	for (int i = 1 ; i <= l.size() ; i++) {
    		System.out.println(i+" -> "+l.get(i-1));
    	}
    	int j = ScannerInt.saisieEntier(l.size() + 1);
    	if (j == 0)
    		return null;
    	else
    		return l.get(j - 1);
	}


    public static void main(String[] args) {
	List<Carotte> lCarottes = new ArrayList<Carotte>();
	lCarottes.add(new Carotte(1));
	lCarottes.add(new Carotte(2));
	lCarottes.add(new Carotte(3));

	List<Pomme> lPommes = new ArrayList<Pomme>();
	lPommes.add(new Pomme(1));
	lPommes.add(new Pomme(2));
	lPommes.add(new Pomme(3));

	ListChoserLegume lcl = new ListChoserLegume();

	Carotte choixCarotte = lcl.chose("quelle carotte ? ",lCarottes);
	System.out.println("vous avez choisi : "+choixCarotte);

	// NE COMPILE PAS :  
	// Pomme choixPomme = lcl.chose("quelle pomme ? ",lPommes);
	

    }
}
