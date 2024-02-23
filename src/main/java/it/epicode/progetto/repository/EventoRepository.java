package it.epicode.progetto.repository;

import it.epicode.progetto.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer>, PagingAndSortingRepository<Evento, Integer> {
}
