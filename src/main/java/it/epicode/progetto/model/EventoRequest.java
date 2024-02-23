package it.epicode.progetto.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlogPostRequest {
    @NotNull(message = "contenuto obbligatorio")
    @NotEmpty(message = "contenuto obbligatorio")
    private String contenuto;

    @NotNull(message = "titolo obbligatorio")
    @NotEmpty(message = "titolo obbligatorio")
    private String titolo;
    private String categoria;
    private int tempoLettura;

    @NotNull(message = "Autore obbligatorio")
    private Integer idAutore;
}
