CC	= gcc
CFLAGS	= -Wall
CFLAGS	+= -g -std=c99

TESTANALYSEUR = testAnalyseur
GRAPHE2VISU = graphe2visu
ALL = testAnalyseur graphe2visu

all: testAnalyseur graphe2visu

testAnalyseur: $(TESTANALYSEUR)
	$(CC) $(CFLAGS) $(TESTANALYSEUR).c graphe.c -o $(TESTANALYSEUR)

graphe2visu: $(GRAPHE2VISU)
	$(CC) $(CFLAGS) graphe.c  $(GRAPHE2VISU).c -o $(GRAPHE2VISU)

.PHONY:	clean realclean
clean:
	$(RM) $(ALL) *~
realclean: clean 
	$(RM) testAnalyseur graphe2visu




