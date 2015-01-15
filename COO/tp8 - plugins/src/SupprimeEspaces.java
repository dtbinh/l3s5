public class SupprimeEspaces implements extensions.Extension {

	@Override
	public String transformer(String s) {
		String[] tab = s.split(" ");
		String chaineSansEspaces = "";
		for (int i = 0 ; i < tab.length ; i++) {
			chaineSansEspaces += tab[i];
		}
		return chaineSansEspaces;
	}

	@Override
	public String getLabel() {
		return "Supprime Espaces";
	}

	@Override
	public String helpMessage() {
		return "Supprime les espaces du texte";
	}

}

