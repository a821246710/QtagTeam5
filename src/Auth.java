import javax.management.relation.RoleUnresolved;

public class Auth {
	private Role role;
	private Account account;
	private String WHITE_LIST[][][] = new String[][][]{
				{ // managers account
					{"file_manager", "fd2cc6c54239c40495a0d3a93b6380eb"} // password: zxcv 
				},
				{ // designers account
					{"designer","962012d09b8170d912f0669f6d7d9d07"} // password: qwer
				}
			};     
	
	
	public boolean Auth(Account account){
		return login(account);
	}
	
	public boolean login(Account account){
		
		int index = 0;
		for(String[][] accs: WHITE_LIST){
			for(String[] acc: accs){
				String id = account.getId();
				String encryptStr = account.getEncryptPassword();
				if(id.equals(acc[0]) && encryptStr.equals(acc[1])){
					if(index == 0)
						role = Role.FILE_MANAGER;
					else
						role = Role.DESIGNER;
					
					return true;
				}
			}
			index++;
		}

		return false;
	}
	
	public boolean logout() {
		role = null;
		account = null;
		
		return true;
	}
	
	public Account getAccount(){
		return account;
	}
	
	public Role getRole(){
		return role;
	}
}
