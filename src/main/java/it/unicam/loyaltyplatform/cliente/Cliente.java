package it.unicam.loyaltyplatform.cliente;

public class Cliente {
    private Long id;
    private String name;
    private String email;

    public Cliente(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Cliente(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
