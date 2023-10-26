package auth.dao;

import auth.model.Customer;

import javax.persistence.EntityManager;

public class CustomerDao {

  private EntityManager em;

  public CustomerDao(EntityManager em) {
    this.em = em;
  }

  public Customer findByCpf(String cpf) {
    String jpql = "SELECT c FROM Customer c WHERE c.cpf = :cpf";
    return em.createQuery(jpql, Customer.class).setParameter("cpf", cpf).getSingleResult();
  }

}
