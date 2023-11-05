package auth.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

  public Connection getConnection() {
    try {
      return createDataSource().getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private HikariDataSource createDataSource() {

    String url = System.getenv("DB_URL");
    String dbUser = System.getenv("DB_USER");
    String dbPassword = System.getenv("DB_PASSWORD");
    String database = System.getenv("DB_DATABASE");

    System.out.println("Vars " + url + dbUser + dbPassword + database);

    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(getUrlConnection(url, database));
    config.setUsername(dbUser);
    config.setPassword(dbPassword);
    config.setMaximumPoolSize(10);

    return new HikariDataSource(config);
  }

  public String getUrlConnection(String url, String database) {
    StringBuffer sb = new StringBuffer();
    sb.append("jdbc:mysql://");
    sb.append(url);
    sb.append("/");
    sb.append(database);

    return sb.toString();
  }
}
