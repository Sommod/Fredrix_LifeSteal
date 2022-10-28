package fredrixmc.permissions;

public enum AdminPerms {
	
	OP("*"), ADMIN_OP("*"), RELOAD("reload"), RESET("reset"), RESET_ALL("reset.all"), GIVE("give"), ALL_GIVE("all.give"), ALL_SET("all.set"), ALL_TAKE("all.take"),
	SET("set"), TAKE("take"), HEARTS_OP("*"), HEARTS_GIVE("hearts.give"), HEARTS_SET("hearts.set"), HEARTS_TAKE("hearts.take"), HEARTS_ALLSET("hearts.all.set"),
	HEARTS_ALL_TAKE("hearts.all.take"), HEARTS_ALL_GIVE("hearts.all.give");
	private String name;
	
	private AdminPerms(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		if(this == OP)
			return "lifesteal.".concat(name);
		
		return "lifesteal.admin.".concat(name);
	}

}
