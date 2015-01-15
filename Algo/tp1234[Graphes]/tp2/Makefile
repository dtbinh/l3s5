CC	= gcc
CFLAGS	= -Wall
CFLAGS	+= -g -std=c99

PARCOURSENLARGEUR = parcoursEnLargeur
PARCOURSENLARGEURCOULEURS = parcoursEnLargeurCouleurs
ALL = parcoursEnLargeur parcoursEnLargeurCouleurs

all: $(ALL)

parcoursEnLargeur: $(PARCOURSENLARGEUR)
	$(CC) $(CFLAGS) $(PARCOURSENLARGEUR).c graphe.c -o $(PARCOURSENLARGEUR)

parcoursEnLargeurCouleurs: $(PARCOURSENLARGEURCOULEURS)
	$(CC) $(CFLAGS) $(PARCOURSENLARGEURCOULEURS).c graphe.c -o $(PARCOURSENLARGEURCOULEURS)

.PHONY:	clean realclean
clean:
	$(RM) $(ALL) *~
realclean: clean 
	$(RM) parcoursEnLargeur parcoursEnLargeurCouleurs




