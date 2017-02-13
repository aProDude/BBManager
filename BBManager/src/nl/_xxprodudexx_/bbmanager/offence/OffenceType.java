package nl._xxprodudexx_.bbmanager.offence;

public enum OffenceType {

	KILLAURA(Category.HACK, "Kill-Aura", 50),
	KILLAURA_HS(Category.HACK, "Kill-Aura (HeadSpin)", 50),
	REACH(Category.HACK, "Reach", 50),
	ANTIKNOCKBACK(Category.HACK, "AntiKnockback", 50),
	
	SPAM(Category.CHAT, "Spam", 15),
	
	
	BAN_EVADE(Category.ACCOUNT, "Ban-Evade", 100); 

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
		HACK, CHAT, ACCOUNT;
	}
	
	public enum Punishment {
		KICK, MUTE, TEMPBAN, PERMBAN;
	}

}
