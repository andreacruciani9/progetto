package it.epicode.progetto.service;

import it.epicode.progetto.exception.NotFoundException;
import it.epicode.progetto.model.Evento;
import it.epicode.progetto.model.EventoRequest;
import it.epicode.progetto.model.Utente;
import it.epicode.progetto.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteService utenteService;

    public Page<Evento> cercaTuttiGliEventi(Pageable pageable){
        return  eventoRepository.findAll(pageable);
    }

    public Evento cercaEventoPerId(int id) throws NotFoundException{
        return eventoRepository.findById(id).
                orElseThrow(()->new NotFoundException("Evento con id="+id+" non trovato"));
    }

    public Evento salvaEvento(EventoRequest eventoRequest) throws NotFoundException{
       // Utente utente = utenteService.getUtenteById(eventoRequest.getId_utente());

        Evento evento = new Evento(eventoRequest.getDescrizione(), eventoRequest.getTitolo(),eventoRequest.getData(),
                eventoRequest.getLocation(), eventoRequest.getPostiDisponibili());
      //  evento.setUtente(List.of(utente));

        return eventoRepository.save(evento);

    }

    public Evento aggiornaEvento(int id, EventoRequest eventoRequest) throws NotFoundException{
        Evento evento = cercaEventoPerId(id);

        Utente utente = utenteService.getUtenteById(eventoRequest.getId_utente());

        evento.setDescrizione(eventoRequest.getDescrizione());
        evento.setPostiDisponibili(eventoRequest.getPostiDisponibili());
        evento.setTitolo(eventoRequest.getTitolo());
        evento.setData(eventoRequest.getData());
        evento.setLocation(eventoRequest.getLocation());

        return eventoRepository.save(evento);
    }
    public  Evento aggiornaListaPartecipanti(int id,EventoRequest eventoRequest )throws NotFoundException{

        Evento evento = cercaEventoPerId(id);
        Utente utente =utenteService.getUtenteById(eventoRequest.getId_utente());

        evento.aggiungiUtente(utente);
        return  eventoRepository.save(evento);

    }
    public  void cancellaListaUtente(int id,EventoRequest eventoRequest )throws NotFoundException{
        Evento evento = cercaEventoPerId(id);
        Utente utente =utenteService.getUtenteById(eventoRequest.getId_utente());
        evento.cancellaUtente(utente);

    }

    public void cancellaEvento(int id) throws NotFoundException{
        Evento post = cercaEventoPerId(id);
        eventoRepository.delete(post);
    }


}
