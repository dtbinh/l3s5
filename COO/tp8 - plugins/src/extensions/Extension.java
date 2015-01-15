package extensions;

/** Interface representing an extension */
public interface Extension {

	/**
	 * transform the string s following the principle of the extension
	 * @param s the string to be transformed
	 * @return the string obtained following the principle of the extension
	 */
	public String transformer(String s) ;

	/**
	 * returns the label of the extension
	 * @return the label of the extension
	 */
	public String getLabel() ;
	
	/**
	 * returns a string representing a message that helps the user to know what the extension is
	 * @return a string representing a message that helps the user to know what the extension is
	 */
	public String helpMessage();

}
