package auth.service;


import auth.config.ConnectionFactory;
import auth.dao.CustomerDao;
import auth.model.Customer;

import java.sql.Connection;
import java.sql.SQLException;


public class AuthService {

    public ConnectionFactory connection;

    public AuthService() {
        this.connection = new ConnectionFactory();
    }
    public boolean validateAccess (String cpf) throws SQLException {
        try (Connection conn = connection.getConnection()) {
            Customer customer = new CustomerDao(conn).findByCpf(cpf);

            if (customer == null) {
                throw new RuntimeException("Not Found");
            }
            return true;
        }

    }
}
