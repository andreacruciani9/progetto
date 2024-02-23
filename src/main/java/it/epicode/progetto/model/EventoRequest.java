package it.epicode.progetto.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EventoRequest {
    @NotNull(message = "descrizione obbligatorio")
    @NotEmpty(message = "descrizione obbligatorio")
    private String descrizione;

    @NotNull(message = "titolo obbligatorio")
    @NotEmpty(message = "titolo obbligatorio")
    private String titolo;
    @NotBlank(message = "location obbligatoria")
    private String location;

    private Date data;

    private int postiDisponibili ;

    @NotNull(message = " utente obbligatorio")
    private Integer id_utente;
}
