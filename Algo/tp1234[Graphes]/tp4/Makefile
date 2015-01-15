CC	= gcc
CFLAGS	= -Wall
CFLAGS	+= -g -std=c99

PARCOURSENPROFONDEUR = parcoursEnProfondeur
EXISTECHAINEENTRE = existeChaineEntre
ALL = parcoursEnProfondeur existeChaineEntre

all: $(ALL)

parcoursEnProfondeur: $(PARCOURSENPROFONDEUR)
	$(CC) $(CFLAGS) $(PARCOURSENPROFONDEUR).c graphe.c -o $(PARCOURSENPROFONDEUR)

existeChaineEntre: $(EXISTECHAINEENTRE)
	$(CC) $(CFLAGS) $(EXISTECHAINEENTRE).c graphe.c -o $(EXISTECHAINEENTRE)

.PHONY:	clean realclean
clean:
	$(RM) $(ALL) *~
realclean: clean 
	$(RM) parcoursEnProfondeur existeChaineEntre




