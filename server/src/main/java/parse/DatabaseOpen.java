package parse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Класс - парсер CSV-файлов.
 * @version 2.0
 */
public final class DatabaseOpen {
    public static Connection open(String nameDatabase,Properties properties) throws SQLException {
        Connection connection =DriverManager.getConnection(nameDatabase,properties);

        Statement statement=connection.createStatement();
        statement.execute("CREATE TABLE  IF NOT EXISTS users (userLogin  VARCHAR(80) UNIQUE  , userPassword  VARCHAR(350) NOT NULL , saul VARCHAR(80) NOT NULL); ");
        statement.execute("CREATE TABLE  IF NOT EXISTS coordinates (coordinateId BIGINT Primary Key, x BIGINT NOT NULL, y REAL NOT NULL); ");
        statement.execute("CREATE TABLE IF NOT EXISTS intLocations (intLocationId BIGINT Primary Key, x INT NOT NULL, y BIGINT NOT NULL, locname VARCHAR(350) NOT NULL); ");
        statement.execute("CREATE TABLE IF NOT EXISTS floatLocations (floatLocationId BIGINT PRIMARY KEY, x REAL NOT NULL, y REAL NOT NULL, z INT NOT NULL); ");
        statement.execute("CREATE TABLE IF NOT EXISTS routes (" +
                "                                      routeId BIGINT primary key," +
                "                                      routeName varchar(80) not null ," +
                "                                      routeDate date not null ," +
                "                                      intFrom bool," +
                "                                      itTo bool not null," +
                "                                      distance bigint not null," +
                "                                      userLogin varchar(80) not null); " );
        return connection;
    }

}
