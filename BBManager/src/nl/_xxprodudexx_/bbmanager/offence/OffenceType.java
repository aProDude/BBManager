package nl._xxprodudexx_.bbmanager.offence;

public enum OffenceType {

	KILLAURA(Category.HACK, "Kill-Aura", 50);

	private String name;
	private int points;

	OffenceType(Category category, String name, int points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public int getBBPoints() {
		return points;
	}

	public enum Category {
		HACK, CHAT;
	}

}
