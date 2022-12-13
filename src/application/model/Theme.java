package application.model;

public enum Theme {
	WATERMELON("/application/resources/turn_off.png"),
	BLACKWHITE("/application/resources/turn_on.png");
	
	String urlTheme;
	
	private Theme(String urlTheme) {
		this.urlTheme = urlTheme;
	}
	
	public String getUrl() {
		return this.urlTheme;
	}
}
