package database;

import config.DatabaseConnector;
import helper.ReadQueryFromFile;
import lombok.SneakyThrows;

import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseQueryExecutor {

  public void deleteAll() {
    executePrepareStatement("delete from app.order_entity");
    executePrepareStatement("delete from app.payment_entity");
    executePrepareStatement("delete from app.credit_request_entity");
  }

  @SneakyThrows
  private void executePrepareStatement(String sqlStatement) {
    DatabaseConnector.connection.prepareStatement(sqlStatement).executeUpdate();
  }

  @SneakyThrows
  public String executeQueryAndGetValue(String columnName) {
    String query = ReadQueryFromFile.readFromFile(Path.of("sql-query.sql"));

    ResultSet resultSet = DatabaseConnector.connection.prepareStatement(query).executeQuery();

    if (resultSet.next()) {
      return resultSet.getString(columnName);
    } else {
      throw new SQLException("No data found in the result set with column name: " + columnName);
    }
  }

}
