package auth.dao;

import auth.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {

  private final Connection conn;

  public CustomerDao(Connection connection) {
    this.conn = connection;
  }

  public Customer findByCpf(String cpf) {
    String sql = "SELECT * FROM customer WHERE cpf = ? ";

    PreparedStatement ps;
    ResultSet resultSet;
    Customer customer = null;
    try {
      ps = conn.prepareStatement(sql);
      ps.setString(1, cpf);
      resultSet = ps.executeQuery();

      while (resultSet.next()) {
        Long id = resultSet.getLong(1);
        String cpfResult = resultSet.getString(2);
        String email = resultSet.getString(3);
        String name = resultSet.getString(4);

        System.out.println(email);

        customer = new Customer(id, cpfResult, email, name);
      }
      resultSet.close();
      ps.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return customer;
  }
}
