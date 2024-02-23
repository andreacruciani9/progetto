package it.epicode.progetto.service;

import it.epicode.progetto.exception.NotFoundException;
import it.epicode.progetto.model.Evento;
import it.epicode.progetto.model.EventoRequest;
import it.epicode.progetto.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AutoreService autoreService;

    public Page<Evento> cercaTuttiIBlogPosts(Pageable pageable){
        return  blogPostRepository.findAll(pageable);
    }

    public Evento cercaPostPerId(int id) throws NotFoundException{
        return blogPostRepository.findById(id).
                orElseThrow(()->new NotFoundException("BlogPost con id="+id+" non trovato"));
    }

    public Evento salvaBlogPost(EventoRequest eventoRequest) throws NotFoundException{
        Autore autore = autoreService.cercaAutorePerId(eventoRequest.getIdAutore());

        Evento evento = new Evento(eventoRequest.getContenuto(), eventoRequest.getTitolo(),
                eventoRequest.getCategoria(), eventoRequest.getTempoLettura(), autore);

        return blogPostRepository.save(evento);

    }

    public Evento aggiornaBlogPost(int id, EventoRequest eventoRequest) throws NotFoundException{
        Evento post = cercaPostPerId(id);

        Autore autore = autoreService.cercaAutorePerId(eventoRequest.getIdAutore());

        post.setCategoria(eventoRequest.getCategoria());
        post.setContenuto(eventoRequest.getContenuto());
        post.setTitolo(eventoRequest.getTitolo());
        post.setTempoLettura(eventoRequest.getTempoLettura());
        post.setAutore(autore);

        return blogPostRepository.save(post);
    }

    public void cancellaBlogPost(int id) throws NotFoundException{
        Evento post = cercaPostPerId(id);
        blogPostRepository.delete(post);
    }

    public Evento uploadCover(int id, String url){
        Evento evento = cercaPostPerId(id);
        evento.setCover(url);
        return blogPostRepository.save(evento);
    }
}
