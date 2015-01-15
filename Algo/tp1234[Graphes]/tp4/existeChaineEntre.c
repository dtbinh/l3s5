#include <stdlib.h>
#include <stdio.h>
#include "graphe.h"
#define VRAI 1
#define FAUX 0

/* Couleurs */
typedef enum {ROUGE=0, BLEU=1, VERT=2} tCouleur;
typedef tCouleur tTabCouleurs[MAX_SOMMETS];

tNumeroSommet sommetsParcourus[MAX_SOMMETS];

// voir page 71 du poly pour la description du code (pred = le tableau des prédécesseurs)
void parcoursEnProfondeur(tGraphe graphe, tNumeroSommet s, tNumeroSommet pred[]) {
	int j;
	tNomSommet nomSommet;
	tNumeroSommet x;
	tNumeroSommet i[MAX_SOMMETS];
	tNumeroSommet voisinI;
	int trouve;
	int k;
	tPileSommets pileSommets;
	tTabCouleurs tableauCouleurs;
	pileSommets = pileSommetsAlloue();
	k = MAX_SOMMETS - 1;
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
				pred[voisinI] = x;
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
			sommetsParcourus[k] = x;
			k--;
		}
	}
	printf("\n");
}

/* renvoie 1 s'il existe une chaîne entre sommet1 et sommet2, 0 sinon */
int existeChaineEntre(tGraphe graphe, tNumeroSommet sommet1, tNumeroSommet sommet2,tNumeroSommet pred[]) {
	parcoursEnProfondeur(graphe,sommet1,pred);
	// si pred[sommet2] est défini, cela veut dire que le parcours en profondeur passe par ce sommet
	if (pred[sommet2] < grapheNbSommets(graphe))
		return 1;
	else
		return 0;
}

int main(int argc, char *argv[]) {
	// on peut sortir d'un labyrinthe s'il existe une chaine entre l'entree (0) et la sortie (1)
	tGraphe labyrinthe1;
	tGraphe labyrinthe2;
	tNumeroSommet pred1[MAX_SOMMETS];
	tNumeroSommet pred2[MAX_SOMMETS];
	

	labyrinthe1 = grapheAlloue();
	grapheChargeFichier(labyrinthe1,"labyrinthe1.grp");
	if (existeChaineEntre(labyrinthe1,0,1,pred1) == 1) {
		printf("On peut sortir du labyrinthe 1\n");
	}
	else
		printf("On ne peut pas sortir du labyrinthe 1\n");

	printf("\n");
	grapheLibere(labyrinthe1);


	labyrinthe2 = grapheAlloue();
	grapheChargeFichier(labyrinthe2,"labyrinthe2.grp");
	if (existeChaineEntre(labyrinthe2,0,1,pred2) == 1)
		printf("On peut sortir du labyrinthe 2\n");
	else
		printf("On ne peut pas sortir du labyrinthe 2\n");

	grapheLibere(labyrinthe2);
}



