package fredrixmc.permissions;

public enum Perms {
	
	OP("*"), LIVES("lives"), LIVES_OTHER("lives.other"), REVIVE("revive"), GIVE("give"), CRAFT("craft"), CONSUME("consume");
	

	private String name;
	
	private Perms(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "lifesteal.".concat(name);
	}
	
}
