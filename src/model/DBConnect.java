package model;

import com.sun.rowset.CachedRowSetImpl;
import org.sqlite.JDBC;

import java.sql.*;

/**
 * Класс для работы с базой данных
 */
public class DBConnect implements DAO{
    /**
     * Переменная: для подключения к базе данных
     */
    private static final String DB_CONNECTION ="jdbc:sqlite::resource:ChemicalDatabase" ;
    private Connection dbConnection = null;

    public DBConnect() {
        try {
            DriverManager.registerDriver(new JDBC());
            this.dbConnection = DriverManager.getConnection(DB_CONNECTION);
        } catch (SQLException throwables) {
            System.out.println("no");
        }
    }

    /**
     * метод для выполнения запросов к базе данных
     * @param query
     * @return
     * @throws SQLException
     */
    // выполнение запроса к БД с получением данных
    public ResultSet dbExecuteQuery(String query) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = new CachedRowSetImpl();

        try {
            System.out.println("Select statement: " + query + "\n");
            statement = this.dbConnection.createStatement();
            resultSet = statement.executeQuery(query);
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            /*if (dbConnection != null) {
                dbConnection.close();
            }*/
        }
        return crs;
    }

    /**
     * метод для обновления базы данных
     * @param sqlStmt
     * @throws SQLException
     */
    // обновление БД
    public void dbExecuteUpdate(String sqlStmt) throws SQLException{
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    @Override
    public Connection dbConnect() {
        return dbConnection;
    }

    @Override
    public void dbDisconnect() throws SQLException {
        dbConnection.close();
    }

    @Override
    public ResultSet dbExecuteQueryDao(String queryStmt) throws SQLException {
        return dbExecuteQuery(queryStmt);
    }

    @Override
    public void dbExecuteUpdateDao(String sqlStmt) throws SQLException {
        dbExecuteUpdate(sqlStmt);
    }

}
