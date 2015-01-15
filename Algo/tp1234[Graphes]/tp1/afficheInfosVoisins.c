#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "graphe.h"

/* Affiche la liste des sommets qui n'ont pas de voisins et la liste des sommets qui ont le plus de voisins */
void etudeVoisins(char *fileName) {
	// on définit le graphe, lui alloue de l'espace mémoire et le charge depuis le fichier en paramètre
	tGraphe graphe;
	graphe = grapheAlloue();
	grapheChargeFichier(graphe,fileName);
	// variable qui récupérera le nom de chaque sommet pour l'afficher
	tNomSommet nomSommet;
	// maximum de voisins d'un sommet
	int maxVoisins = 0;
	// indice de boucle
	int i;
	// on parcourt le graphe (= on parcourt tous ses sommets)
	printf("Sommets qui n'ont pas de voisins :");
	for (i = 0 ; i < grapheNbSommets(graphe) ; i++) {
		// on récupère le nombre de voisins du sommet de numéro i
		int nbVoisins = grapheNbVoisinsSommet(graphe,i);
		// si le sommet de numero i n'a pas de voisins, on récupère son nom et on l'affiche
		if (nbVoisins == 0) {
			grapheRecupNomSommet(graphe,i,nomSommet);
			printf(" %s",nomSommet);
		}
		// on compare le nombre de voisins du sommet de numéro i au maximum de voisins
		if (nbVoisins > maxVoisins)
			maxVoisins = nbVoisins;
	}
	printf("\n");
	// on reparcourt le graphe pour afficher le ou les sommets qui ont le plus de voisins (dont le nombre de voisins = maxVoisins)
	printf("Sommets qui ont le plus de voisins :");
	for (i = 0 ; i < grapheNbSommets(graphe) ; i++) {
		if (grapheNbVoisinsSommet(graphe,i) == maxVoisins) {
			// si le sommet de numéro i a maxVoisins voisins, on récupère son nom et on l'affiche
			grapheRecupNomSommet(graphe,i,nomSommet);
			printf(" %s",nomSommet);
		}
	}
	printf("\n");
	// on libère l'espace mémoire occupé par le graphe
	grapheLibere(graphe);
}

int main(int argc, char *argv[]) {
	if (argc < 2) {
   		halt("Usage : %s FichierGraphe\n", argv[0]);
  	}
  	etudeVoisins(argv[1]);
	exit(EXIT_SUCCESS);
}
