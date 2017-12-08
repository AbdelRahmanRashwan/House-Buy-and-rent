package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public abstract class Model<Entity> {
	static Connection conn;

	Model() {
		conn = DBConnection.getConn();
	}

	public abstract Entity select(int id);

	public abstract List<Entity> selectAll();

	public abstract List<Entity> selectWhere(String columns, String where);

	public abstract boolean update(Entity entity);

	public abstract boolean delete(int id);

	public abstract boolean insert(Entity entity);

	abstract Entity parse(ResultSet s);

}
