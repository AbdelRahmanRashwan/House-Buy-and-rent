package Models;

import java.sql.Connection;
import java.util.List;

public abstract class Model<Entity> {
	static Connection conn;

	Model() {
		conn = DBConnection.getConn();
	}

	abstract Entity select(int id);

	abstract List<Entity> selectAll();

	abstract List<Entity> selectWhere(List<String> columns, String where);

	abstract boolean update(Entity entity);

	abstract boolean delete(int id);

	abstract boolean insert(Entity entity);
}
