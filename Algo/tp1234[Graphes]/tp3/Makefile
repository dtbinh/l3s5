CC	= gcc
CFLAGS	= -Wall
CFLAGS	+= -g -std=c99

PLUSCOURTECHAINE = plusCourteChaine
GRAPHE2VISUPLUSCOURTSCHEMINS = graphe2visuPlusCourtsChemins
ALL = plusCourteChaine graphe2visuPlusCourtsChemins

all: $(ALL)

plusCourteChaine: $(PLUSCOURTECHAINE)
	$(CC) $(CFLAGS) $(PLUSCOURTECHAINE).c graphe.c -o $(PLUSCOURTECHAINE)

graphe2visuPlusCourtsChemins: $(GRAPHE2VISUPLUSCOURTSCHEMINS)
	$(CC) $(CFLAGS) $(GRAPHE2VISUPLUSCOURTSCHEMINS).c graphe.c -o $(GRAPHE2VISUPLUSCOURTSCHEMINS)

.PHONY:	clean realclean
clean:
	$(RM) $(ALL) *~
realclean: clean 
	$(RM) plusCourteChaine graphe2visuPlusCourtsChemins




