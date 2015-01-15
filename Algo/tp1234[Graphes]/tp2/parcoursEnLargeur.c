#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "graphe.h"

/* Couleurs */
typedef enum {ROUGE=0, BLEU=1, VERT=2} tCouleur;
typedef tCouleur tTabCouleurs[MAX_SOMMETS];

/* fait le parcours en largeur vu en cours et affiche la liste des sommets dans l'ordre de parcours */
void parcoursEnLargeur(tGraphe graphe, tNumeroSommet sommetDepart) {
	// le tableau de couleurs et la file de sommets
	tTabCouleurs tableauCouleurs;
	tFileSommets fileSommets;
	// variable contenant les numéros des sommets défilés
	tNumeroSommet x;
	// variable contenant les voisins de x
	tNumeroSommet y;
	// variable contenant les noms des sommets affichés
	tNomSommet nomSommet;
	// indice de boucle
	int i;
	grapheRecupNomSommet(graphe,sommetDepart,nomSommet);
	printf("Parcours en largeur avec %s comme sommet de départ :",nomSommet);
	fileSommets = fileSommetsAlloue();
	// on colorie le sommet de départ en vert et tous les autres sommets en bleu
	for (i = 0 ; i < grapheNbSommets(graphe) ; i++) {
		if (i == sommetDepart)
			tableauCouleurs[i] = VERT;
		else
			tableauCouleurs[i] = BLEU;
	}
	// on enfile le sommet de départ
	fileSommetsEnfile(fileSommets,sommetDepart);
	while (!fileSommetsEstVide(fileSommets)) {
		// on défile le dernier sommet enfilé
		x = fileSommetsDefile(fileSommets);
		// on parcourt la liste des voisins de x
		for (i = 0 ; i < grapheNbVoisinsSommet(graphe,x) ; i++) {
			// on récupère le numéro du i-ème voisin de x
			y = grapheVoisinSommetNumero(graphe,x,i);
			// s'il est bleu, on le colorie en vert et on l'enfile
			if (tableauCouleurs[y] == BLEU) {
				tableauCouleurs[y] = VERT;
				fileSommetsEnfile(fileSommets,y);
			}
		}
		// on colorie x en rouge et on affiche le nom de sommet correspondant
		tableauCouleurs[x] = ROUGE;
		grapheRecupNomSommet(graphe,x,nomSommet);
		printf(" %s",nomSommet);
	}
	printf("\n");
}

int main(int argc, char *argv[]) {
	tGraphe graphe;
	if (argc < 3) {
		halt("Usage : %s FichierGraphe NumeroSommetDeDepart\n", argv[0]);
	}
	graphe = grapheAlloue();
	grapheChargeFichier(graphe,argv[1]);
	parcoursEnLargeur(graphe,atoi(argv[2]));
	// on libère l'espace mémoire occupé par le graphe car on n'en a plus besoin
	grapheLibere(graphe);
	exit(EXIT_SUCCESS);
}

