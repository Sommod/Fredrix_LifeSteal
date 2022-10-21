package fredrixmc.permissions;

public enum AdminPerms {
	
	SUPER_OP("*"), OP("*"), RELOAD("reload"), RESET("reset"), RESET_OTHER("reset.other"), RESET_ALL("reset.all"), GIVE("give"), GIVE_ALL("giveall"), SET("set"), SET_ALL("setall"), TAKE("take"), TAKE_ALL("takeall"), TAKE_ALL_OTHER("takeall.other"),
	HEARTS_GIVE("hearts.give"), HEARTS_GIVE_ALL("hearts.giveall"), HEARTS_SET("hearts.set"), HEARTS_SET_ALL("hearts.setall"), HEARTS_TAKE("hearts.take"), HEARTS_TAKE_ALL("hearts.takeall"), HEARTS_OP("hearts.*");
	
	private String name;
	
	private AdminPerms(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		if(this == SUPER_OP)
			return "lifesteal.".concat(name);
		
		return "lifesteal.admin.".concat(name);
	}

}
