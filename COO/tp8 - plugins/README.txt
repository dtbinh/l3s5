Auteur : DIAGNE Salla

Listing des fichiers et répertoires édités du projet :
	- classes : répertoire de fichiers .class
	- disponibles : répertoire des extensions disponibles (j'en ai rajouté un qui supprime les espaces)
	- doc : répertoire des liens hypertextes vers la Javadoc du projet
	- extensions : répertoire des extensions présentes (pour les exos 4 et 5)
	- repertoireExos2-3 : répertoire de divers fichiers (pour les exos 2 et 3)
	- src : répertoire des fichiers source
	- manifest_exo1 : manifest de l'exercice 1
	- manifest_exo2 : manifest de l'exercice 2
	- manifest_exo3 : manifest de l'exercice 3
	- manifest_exo4 : manifest de l'exercice 4
	- manifest_exo5 : manifest de l'exercice 5


Compte-rendu : Tous les exercices ont été entièrement traités et avec succès. Pas besoin de fichiers de test à mon goût parce que le bon fonctionnement des classes de test de chaque exercice garantissent la validité du projet.


Commandes de tests : se placer à la racine du projet (dossier diagne_tp8/plugins) et exécuter les commandes :
	java -jar exercice1.jar -> pour tester la classe javax.swing.Timer qui affiche la date et l'heure d'aujourd'hui à chaque seconde
	java -jar exercice2.jar dirName -> pour afficher la liste des fichiers du répertoire dirName, ceux dont le nom commence par C et ceux qui sont d'extension .class
	java -jar exercice3.jar dirName -> pour "surveiller" le répertoire dirName, c'est-à-dire vérifier son contenu toutes les demi-secondes, et afficher un message 			lorsqu'un fichier d'extension .class a été ajouté ou supprimé du répertoire
	java -jar exercice4.jar -> pour "surveiller" le répertoire "extensions" (ajouté au classpath à la fabrication du jar, voir fichier manifest_exo4), c'est-à-dire 		vérifier son contenu toutes les demi-secondes, et afficher un message lorsqu'une extension a été ajoutée ou supprimée du répertoire
	java -jar exercice5.jar -> pour lancer l'éditeur extensible qui manipule les plugins
