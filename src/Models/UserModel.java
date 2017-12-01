package Models;

import java.util.List;
import Entities.User;

public class UserModel extends Model<User> {

	@Override
	User select(String id) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	List<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	List<User> selectWhere(List<String> columns, String where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean insert(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
