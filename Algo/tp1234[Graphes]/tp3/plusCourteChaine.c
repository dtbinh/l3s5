#include <stdlib.h>
#include <stdio.h>
#include "graphe.h"

/* Couleurs */
typedef enum {ROUGE=0, BLEU=1, VERT=2} tCouleur;
typedef tCouleur tTabCouleurs[MAX_SOMMETS];

/* calcule la plus courte chaine entre s et tout autre sommet du graphe graphe */
// d[i] = distance entre le sommet de numéro s et le sommet de numéro i
// pred[i] = le numéro du sommet qui précède i
void plusCourteChaine(tGraphe graphe, tNumeroSommet s, int d[], int pred[]) {
	// indice de boucle
	int i;
	// le tableau de couleurs
	tTabCouleurs tableauCouleurs;
	// variables : x contenant le numéro de sommet défilé et y contenant un numéro de sommet voisin de x
	tNumeroSommet x, y;
	// nomSommetS contenant le nom du sommet de départ et nomSommetVoisin contenant le nom d'un sommet voisin du sommet de départ
	tNomSommet nomSommetS, nomSommetVoisin;
	// on initialise une file de numéros de sommets et on lui alloue de l'espace mémoire
	tFileSommets fileSommets;
	fileSommets = fileSommetsAlloue();
	// on colorie tous les sommets en bleu sauf s
	for (i = 0 ; i < grapheNbSommets(graphe) ; i++) {
		if (i != s)
			tableauCouleurs[i] = BLEU;
	}
	// d[s] = 0 et pred[s] est indéfini
	d[s] = 0;
	// on colorie s en vert et on l'enfile
	tableauCouleurs[s] = VERT;
	fileSommetsEnfile(fileSommets,s);
	while (!fileSommetsEstVide(fileSommets)) {
		x = fileSommetsDefile(fileSommets);
		// pour tout voisin bleu y de x, on le colorie en vert on l'enfile, et on met à jour d[y] et pred[y]
		for (i = 0 ; i < grapheNbVoisinsSommet(graphe,x) ; i++) {
			y = grapheVoisinSommetNumero(graphe,x,i);
			if (tableauCouleurs[y] == BLEU) {
				tableauCouleurs[y] = VERT;
				fileSommetsEnfile(fileSommets,y);
				// la distance entre s et y = (la distance entre s et x) + 1
				d[y] = d[x] + 1;
				// x est le prédécesseur de y (vu que y est voisin de x)
				pred[y] = x;
			}
		}
		// on colorie x en rouge
		tableauCouleurs[x] = ROUGE;
	}
	// on affiche tous les sommets et la longueur de la chaîne la plus courte la reliant à s
	grapheRecupNomSommet(graphe,s,nomSommetS);
	printf("Plus courtes chaînes (PCC) entre %s et tout autre sommet du graphe :\n",nomSommetS);
	for (i = 0 ; i < grapheNbSommets(graphe) ; i++) {
		grapheRecupNomSommet(graphe,i,nomSommetVoisin);
		// -1 s'il n'existe pas de chaîne entre s et un sommet donné
		if (d[i] > MAX_ARCS) {
			pred[i] = -1;
			printf("PCC(%s,%s) = -1\n",nomSommetS,nomSommetVoisin);
		}
		else {
			printf("PCC(%s,%s) = %d\n",nomSommetS,nomSommetVoisin,d[i]);
		}
	}
	pred[s] = -1;
	// on libère les espaces mémoires occupés par la file
	fileSommetsLibere(fileSommets);
}

int main(int argc, char *argv[]) {
	tGraphe graphe;
	int d[MAX_SOMMETS];
	tNumeroSommet pred[MAX_SOMMETS];
	if (argc < 3) {
		halt("Usage : %s FichierGraphe NumeroSommetDeDepart\n", argv[0]);
	}
	graphe = grapheAlloue();
	grapheChargeFichier(graphe,argv[1]);
	plusCourteChaine(graphe,atoi(argv[2]),d,pred);
	grapheLibere(graphe);
	exit(EXIT_SUCCESS);
}
