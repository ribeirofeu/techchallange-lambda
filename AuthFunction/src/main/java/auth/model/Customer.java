package auth.model;



import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private String email;


    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
