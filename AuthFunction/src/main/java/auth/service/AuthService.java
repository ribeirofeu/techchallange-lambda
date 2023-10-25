package auth.service;

import auth.dao.CustomerDao;
import auth.model.Customer;
import auth.model.Request;
import auth.utils.JPAUtil;
import auth.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;

public class AuthService {

    public String getToken (String body) throws JsonProcessingException {

        ObjectMapper oMapper = new ObjectMapper();
        Request request = oMapper.readValue(body, Request.class);

        EntityManager em = JPAUtil.getEntityManager();
        CustomerDao dao = new CustomerDao(em);
        Customer customer = dao.findByCpf(request.getCpf());

        if (customer == null ) {
            throw new RuntimeException("Not Found");
        }
        return JwtUtils.genereateToken(customer.getId().toString());
    }
}
