cd ../classes

jar cvf ../Simulation1.jar facteur

jar cvfm ../Simulation1.jar ../manifest1 facteur

jar cvfm ../Simulation1.jar ../manifest1 facteur -C classes -C doc

jar cvf ../Simulation2.jar facteur

jar cvfm ../Simulation2.jar ../manifest2 facteur

jar cvfm ../Simulation2.jar ../manifest2 facteur -C classes C doc

cd ../compil

