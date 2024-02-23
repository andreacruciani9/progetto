package it.epicode.progetto.model;

import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descrizione;
    private String titolo;
    private Date data;
    private String location;
    @Column(name = "posti_disponibili")
    private int postiDisponibili;

    @ManyToMany
    @JoinTable(name = "Utente")
    private List <Utente> utente;

    public Evento(String descrizione, String titolo, Date data, String location, int postiDisponibili) {
        this.descrizione = descrizione;
        this.titolo = titolo;
        this.data = data;
        this.location = location;
        this.postiDisponibili = postiDisponibili;
        this.utente=new ArrayList<>();
    }

    public Evento(){

    }

public  void  aggiungiUtente(Utente utente){
       this.utente.add(utente);
}
public  void  cancellaUtente(Utente utente){
        this.utente.remove(utente);
}

}












