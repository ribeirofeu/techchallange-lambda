package auth.service;


import auth.dao.CustomerDao;
import auth.model.Customer;
import auth.utils.JPAUtil;

import javax.persistence.EntityManager;

public class AuthService {

    public boolean validateAccess (String document) {
        EntityManager em = JPAUtil.getEntityManager();
        CustomerDao dao = new CustomerDao(em);
        Customer customer = dao.findByCpf(document);

        if (customer == null ) {
            throw new RuntimeException("Not Found");
        }
        return true;
    }
}
