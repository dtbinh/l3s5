#include <stdlib.h>
#include <stdio.h>
#include "graphe.h"
#define VRAI 1
#define FAUX 0

/* Couleurs */
typedef enum {ROUGE=0, BLEU=1, VERT=2} tCouleur;
typedef tCouleur tTabCouleurs[MAX_SOMMETS];

// voir page 71 du poly pour la description du code
void parcoursEnProfondeur(tGraphe graphe, tNumeroSommet s) {
	int j;
	tNomSommet nomSommet;
	tNumeroSommet x;
	tNumeroSommet i[MAX_SOMMETS];
	tNumeroSommet voisinI;
	int trouve;
	tPileSommets pileSommets;
	tTabCouleurs tableauCouleurs;
	pileSommets = pileSommetsAlloue();
	grapheRecupNomSommet(graphe,s,nomSommet);
	printf("Parcours en profondeur avec %s comme sommet de départ :",nomSommet);
	for (j = 0 ; j < grapheNbSommets(graphe) ; j++) {
		tableauCouleurs[j] = BLEU;
		i[j] = 0;
	}
	tableauCouleurs[s] = VERT;
	pileSommetsEmpile(pileSommets,s);
	while (!pileSommetsEstVide(pileSommets)) {
		x = pileSommetsTete(pileSommets);
		//i[x] = i[x] + 1; (erreur dans le poly (je l'ai remarquée wesh j'suis bien !)
		trouve = FAUX;
		while ((i[x] < grapheNbVoisinsSommet(graphe,x)) && (trouve == FAUX)) {
			voisinI = grapheVoisinSommetNumero(graphe,x,i[x]);
			if (tableauCouleurs[voisinI] == BLEU) {
				trouve = VRAI;
			}
			else
				i[x] = i[x] + 1;
		}
		if (trouve == VRAI) {
			voisinI = grapheVoisinSommetNumero(graphe,x,i[x]);
			tableauCouleurs[voisinI] = VERT;
			pileSommetsEmpile(pileSommets,voisinI);
		}
		else {
			tableauCouleurs[x] = ROUGE;
			pileSommetsDepile(pileSommets);
			grapheRecupNomSommet(graphe,x,nomSommet);
			printf(" %s",nomSommet);
		}
	}
	printf("\n");
}



int main(int argc, char *argv[]) {
	tGraphe graphe;
	if (argc < 3) {
		halt("Usage : %s FichierGraphe sommetDepart\n", argv[0]);
  	}
	graphe = grapheAlloue();
	grapheChargeFichier(graphe,argv[1]);
	parcoursEnProfondeur(graphe,atoi(argv[2]));
	grapheLibere(graphe);
}
