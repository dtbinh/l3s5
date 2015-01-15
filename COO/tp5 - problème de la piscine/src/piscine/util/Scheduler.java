package piscine.util;

/** Classe représentant un ordonnanceur de type scheduler */
public class Scheduler extends Ordonnanceur {
	
	/**
	 * constructeur pour les objets de la classe Scheduler
	 */
	public Scheduler() {
		super();
	}
	
	public boolean schedulerTermine() {
		for (Action a : this.lesSousActions) {
			if (!a.estTerminee())
				return false;
		}
		return true;
	}
	
	public Action premiereActionNonTerminee() {
		int i = 0;
		while (this.lesSousActions.get(i).estTerminee()) {
			i++;
		}
		return this.lesSousActions.get(i);
	}
	
	@Override
	public void faire() throws ActionTermineeException {
		if (this.estTerminee()) {
			throw new ActionTermineeException();
		}
		else {
			Action a = this.premiereActionNonTerminee();
			a.faire();
			this.lesSousActions.remove(a);
			this.lesSousActions.add(a);
			if (this.schedulerTermine()) {
				this.etat = Etat.TERMINEE;
			}
		}
	}

}
