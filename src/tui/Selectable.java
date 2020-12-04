package tui;

public interface Selectable {
	/**
	 * A standard command to be called by others using this interface for displaying options.
	 */
	public void start();
	
	/**
	 * A command to retrieve the description of the given implementation of IOption.
	 * @return The description of this option.
	 */
	public String getDescription();
}
 


