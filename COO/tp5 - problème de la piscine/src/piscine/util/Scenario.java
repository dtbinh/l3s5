package piscine.util;

/** Classe représentant un ordonnanceur de type scénario */
public class Scenario extends Ordonnanceur {

	// l'indice de la prochaine action
	protected int indiceActionCourante;
		
	/**
	 * constructeur pour les objets de la classe Scenario
	 */
	public Scenario() {
		super();
		this.indiceActionCourante = 0;
	}
	
	@Override
	public void faire() throws ActionTermineeException {
		if (this.estTerminee()) {
			throw new ActionTermineeException();
		}
		else {
			Action a = this.lesSousActions.get(this.indiceActionCourante);
			a.faire();
			if (a.estTerminee()) {
				this.indiceActionCourante++;
				if (this.indiceActionCourante == this.nbreSousActions())
					this.etat = Etat.TERMINEE;
			}
		}
	}

}
