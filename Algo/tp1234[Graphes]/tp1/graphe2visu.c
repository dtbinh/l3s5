#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "graphe.h"
#include "sys/wait.h"

/* crée le fichier nomm outfile.dot de type postscript, le transformer en .ps que l'on pourra visualiser avec evince */
void graphe2visu(tGraphe graphe, char *outfile) {
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
			case 1 : fprintf(fic,"%s -> %s\n",nomSommetOrig,nomSommetDest);
			default : fprintf(fic,"%s -- %s\n",nomSommetOrig,nomSommetDest);	
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
	if (argc < 2) {
   		halt("Usage : %s FichierGraphe\n", argv[0]);
  	}
	// on crée un graphe, lui alloue de l'espace mémoire et le charge dans le fichier passé en paramètre
  	tGraphe graphe;
  	graphe = grapheAlloue();
  	grapheChargeFichier(graphe,argv[1]);
  	graphe2visu(graphe,"visu");
	// on libère l'espace mémoire occupé par le graphe
	grapheLibere(graphe);
	exit(EXIT_SUCCESS);
}
