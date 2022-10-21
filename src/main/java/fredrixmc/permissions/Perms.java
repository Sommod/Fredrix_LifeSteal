package fredrixmc.permissions;

public enum Perms {
	
	SUPER_OP("*"), LIVES("lives"), LIVES_OTHER("lives.other"), REVIVE("revive"), GIVEREVIVE("giverevive"), GIVEREVIVE_FREE("giverevive.free"), CRAFT("craft"), CONSUME("consume");
	

	private String name;
	
	private Perms(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "lifesteal.".concat(name);
	}
	
}
