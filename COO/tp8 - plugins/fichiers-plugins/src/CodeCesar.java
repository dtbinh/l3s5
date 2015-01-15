import extensions.Extension;

public class CodeCesar implements Extension {

	private int decalage;

	public CodeCesar() {
		this(1);
	}

	public CodeCesar(int decalage) {
		this.decalage = decalage;
	}

	public String transformer(String s) {
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isLetter(c)) {
				if (Character.isLowerCase(c)) {
					c = (char) ('a' + (((c - 'a') + this.decalage) % 26));
				} else {
					c = (char) ('A' + (((c - 'A') + this.decalage) % 26));
				}
			}
			res = res + c;
		}
		return res;
	}

	public String getLabel() {
		return "Code César " + this.decalage;
	}

	public String helpMessage() {
		return "Applique un code de césar au texte";
	}
}
