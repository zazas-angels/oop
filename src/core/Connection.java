package core;

import java.sql.ResultSet;

public interface Connection {
	/**
	 * This method returns all results form table
	 */
	public ResultSet getResults(String tableName);
	/**
	 * This method returns all results form table with specified id
	 */
	public ResultSet getResults(String tableName, int id);
}
