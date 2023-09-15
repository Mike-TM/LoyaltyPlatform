//package it.unicam.loyaltyplatform.tessera;
//
//import it.unicam.loyaltyplatform.cliente.Cliente;
//import jakarta.persistence.*;
//
//@Entity @Table
//public class Tessera {
//
//    @Id @GeneratedValue
//    private Long id;
//
//    @OneToOne
//    private Cliente titolareTessera;
//
//    public Tessera() {
//    }
//
//    public Tessera(Cliente cliente, Long id ) {
//        this.id=id;
//        this.titolareTessera = cliente;
//    }
//
//    public Tessera(Cliente cliente){
//        this.titolareTessera=cliente;
//    }
//
//    public Cliente getTitolareTessera() {
//        return titolareTessera;
//    }
//
//    public void setTitolareTessera(Cliente nuovoTitolare){
//        this.titolareTessera=nuovoTitolare;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//
//}
