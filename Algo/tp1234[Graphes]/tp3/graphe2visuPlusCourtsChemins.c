#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "graphe.h"
#include "sys/wait.h"

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

void graphe2visuPlusCourtsChemins(tGraphe graphe, char *outfile, tNumeroSommet sommetDepart, tNumeroSommet pred[MAX_SOMMETS]) {
	int d[MAX_SOMMETS];
	plusCourteChaine(graphe,sommetDepart,d,pred);
	// le canal d'écriture
	FILE *fic;
	// la commande de transformation du fichier .dot en fichier .ps
	char commande[80];
	// le fichier .dot servant à créer le .ps
	char dotfile[80];
	// permettra d'exécuter la commande
	int ret;
	// on va créer un fichier pour graphviz, dans le fichier "outfile".dot
	strcpy(dotfile, outfile);
	strcat(dotfile, ".dot");
	fic = fopen(dotfile, "w");
	// on déclenche une exception si le fichier n'est pas valide
	if (fic == NULL) {
		halt ("Ouverture du fichier %s en écriture impossible\n", dotfile);
	}
	// on vérifie l'orientation du graphe et on fait l'affichage correspondant
	switch (grapheEstOriente(graphe)) {
		case 1 : fprintf(fic,"digraph {\n");
		default : fprintf(fic,"graph {\n");
	}
	// variables qui vont contenir les noms de sommets origine et destination, et leurs numéros, et enfin l'arc qui les relie
	tNumeroSommet numSommetOrig;
	tNumeroSommet numSommetDest;
	tNomSommet nomSommetOrig;
	tNomSommet nomSommetDest;
	tArc arc;
	int i;
	for (i = 0 ; i < grapheNbSommets(graphe) ; i++) {
		grapheRecupNomSommet(graphe,i,nomSommetOrig);
		if (i == sommetDepart)
			fprintf(fic,"%s [color=blue];\n",nomSommetOrig);
		else
			fprintf(fic,"%s [color=black];\n",nomSommetOrig);
	}
	// on parcourt la liste des arcs
	for (i = 0 ; i < grapheNbArcs(graphe) ; i++) {
		// on récupère le numéro de l'arc ainsi que le numéro du sommet origine et celui du sommet destination
		arc = grapheRecupArcNumero(graphe,i);
		numSommetOrig = arc.orig;
		numSommetDest = arc.dest;
		// on récupère les noms des sommets et on les affiche selon l'orientation du graphe
		grapheRecupNomSommet(graphe,numSommetOrig,nomSommetOrig);
		grapheRecupNomSommet(graphe,numSommetDest,nomSommetDest);
		switch (grapheEstOriente(graphe)) {
			case 1 : 
				if (numSommetDest > -1)
					fprintf(fic,"%s -> %s [color=blue]\n",nomSommetOrig,nomSommetDest);
				else
					fprintf(fic,"%s -> %s [color=black]\n",nomSommetOrig,nomSommetDest);
			default :
				if (numSommetDest > -1)
					fprintf(fic,"%s -- %s [color=blue]\n",nomSommetOrig,nomSommetDest);
				else
					fprintf(fic,"%s -- %s [color=black]\n",nomSommetOrig,nomSommetDest);	
		}
	}
	// on cherche les sommets qui n'ont pas de voisins et on les affiche
	for (i = 0 ; i < grapheNbSommets(graphe) ; i++) {
		if (grapheNbVoisinsSommet(graphe,i) == 0) {
			grapheRecupNomSommet(graphe,i,nomSommetOrig);
			fprintf(fic,"%s\n",nomSommetOrig);
		}
	}
	fprintf(fic,"}");
	// on ferme le canal d'écriture et on met à jour la commande
	fclose(fic);
	sprintf(commande, "dot -Tps %s -o %s.ps", dotfile, outfile);
	ret = system(commande);
	if (WEXITSTATUS(ret))
		halt("La commande suivante a échoué\n%s\n", commande);
}

int main(int argc, char *argv[]) {
	if (argc < 3) {
		halt("Usage : %s FichierGraphe NumeroSommetDeDepart\n", argv[0]);
	}
	tGraphe graphe;
	tNumeroSommet pred[MAX_SOMMETS];
	graphe = grapheAlloue();
	grapheChargeFichier(graphe,argv[1]);
	graphe2visuPlusCourtsChemins(graphe,"visuPCC",atoi(argv[2]),pred);
	grapheLibere(graphe);
}
	
