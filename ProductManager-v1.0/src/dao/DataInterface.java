package dao;

public interface DataInterface {

	int testLogin(String _user_name, String user_password);
	int registe(String _user_name, String user_password, int position);
}
