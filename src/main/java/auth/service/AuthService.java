package auth.service;


public class AuthService {

    public boolean validateAccess (String document) {
//        EntityManager em = JPAUtil.getEntityManager();
//        CustomerDao dao = new CustomerDao(em);
//        Customer customer = dao.findByCpf(request.getCpf());

//        if (customer == null ) {
//            throw new RuntimeException("Not Found");
//        }
        return document.equals("123");
    }
}
