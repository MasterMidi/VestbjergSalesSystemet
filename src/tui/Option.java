package tui;

public class Option implements Selectable {
	private String description;
	
	public Option(String description) {
		this.description = description;
	}
	
	@Override
	public void start() {
	}

	@Override
	public String getDescription() {
		return description;
	}

}
