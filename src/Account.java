public class Account {
	private String id;
	private String password;
	
	public Account(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	
	public String getEncryptPassword(){
		return CryptWithMD5.cryptWithMD5(password);
	}
	
}
