#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h> 
#include <unistd.h>
#include "sys/wait.h"
#include "graphe.h"

/* Couleurs */
typedef enum {ROUGE=0, BLEU=1, VERT=2} tCouleur;
typedef tCouleur tTabCouleurs[MAX_SOMMETS];

/* renvoie une chaîne de caractères décrivant la couleur en tc */ 
char* enumToString(tCouleur tc) {
	switch (tc) {
		case 0 : return "red";
		case 1 : return "blue";
		default : return "green";
	}
}

/* timer de 2 secondes, permettant de bien voir le changement de couleur dans le fichier .ps */
void timer(int temps) {
	sleep(temps);
	return;
}

/* affiche le graphe avec les sommets colorés */
void graphe2visuCouleurs(tGraphe graphe, char *outfile, tTabCouleurs tabCouleurs) {
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
		case 1 : fprintf(fic,"digraph {\n"); break;
		default : fprintf(fic,"graph {\n"); break;
	}
	tNomSommet nomSommet;
	// écrit dans le fichier la couleur des sommets à cette étape
	for (int i = 0; i < grapheNbSommets(graphe); i++) {
		grapheRecupNomSommet(graphe, i, nomSommet);
		fprintf(fic,"%s [color=%s];\n",nomSommet,enumToString(tabCouleurs[i]));
	}
	printf("\n");
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
			case 1 : fprintf(fic,"%s -> %s\n",nomSommetOrig,nomSommetDest); break;
			default : fprintf(fic,"%s -- %s\n",nomSommetOrig,nomSommetDest); break;
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
	// on exécute la commande
	ret = system(commande);
	
	if (WEXITSTATUS(ret)) {
		halt("La commande suivante a échoué\n%s\n", commande);
	}
	/*sprintf(commande2,"evince %s.ps",outfile);
	printf("%s\n",commande2);
	ret = system(commande2);*/
	timer(2);
}

/* fait le parcours en largeur vu en cours et affiche la liste des sommets dans l'ordre de parcours */

void parcoursEnLargeurCouleurs(tGraphe graphe, tNumeroSommet sommetDepart, tTabCouleurs tableauCouleurs) {
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
	fileSommets = fileSommetsAlloue();
	// on colorie le sommet de départ en vert et tous les autres sommets en bleu
	for (i = 0 ; i < grapheNbSommets(graphe) ; i++) {
		tableauCouleurs[i] = BLEU;
	}
	for (i = 0 ; i < grapheNbSommets(graphe) ; i++) {
		if (i == sommetDepart) {
			tableauCouleurs[i] = VERT;
			// on affiche le graphe au départ (quand tous les sommets) sont bleus et le sommet de départ est vert
			graphe2visuCouleurs(graphe,"visuCouleurs",tableauCouleurs);
		}
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
				// on affiche le graphe car il y a eu un changement de couleur
				graphe2visuCouleurs(graphe,"visuCouleurs",tableauCouleurs);
				fileSommetsEnfile(fileSommets,y);
			}
		}
		// on colorie x en rouge et on affiche le nom de sommet correspondant
		tableauCouleurs[x] = ROUGE;
		// on affiche le graphe car il y a eu un changement de couleur
		graphe2visuCouleurs(graphe,"visuCouleurs",tableauCouleurs);
		grapheRecupNomSommet(graphe,x,nomSommet);
	}
	printf("\n");
	// on libère l'espace mémoire occupé par le graphe car on n'en a plus besoin
	grapheLibere(graphe);
}

int main(int argc, char *argv[]) {
	tGraphe graphe;
	tTabCouleurs tableauCouleurs;
	if (argc < 3) {
		halt("Usage : %s FichierGraphe NumeroSommetDeDepart\n", argv[0]);
  	}
  	graphe = grapheAlloue();
  	grapheChargeFichier(graphe,argv[1]);
  	parcoursEnLargeurCouleurs(graphe,atoi(argv[2]),tableauCouleurs);
	exit(EXIT_SUCCESS);
}

// ouvrir le fichier visuCouleurs.ps pour voir le graphe à chaque changement de couleur









