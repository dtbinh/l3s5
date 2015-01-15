
import extensions.Extension;

public class Majuscules implements Extension {

	public String transformer(String s) {
		return s.toUpperCase();
	}

	public String getLabel() {
		return "En majuscules";
	}

	public String helpMessage() {
		return "Transforme toutes les lettres du texte en majuscules";
	}
}
