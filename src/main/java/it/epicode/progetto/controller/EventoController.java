package it.epicode.progetto.controller;

import it.epicode.progetto.exception.BadRequestException;
import it.epicode.progetto.model.Evento;
import it.epicode.progetto.model.EventoRequest;
import it.epicode.progetto.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogPostController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/post")
    public Page<Evento> getAll(Pageable pageable){

        return eventoService.cercaTuttiIBlogPosts(pageable);
    }
    @GetMapping("/post/{id}")
    public Evento getBlogPostById(@PathVariable int id){
        return eventoService.cercaPostPerId(id);

    }
    @PostMapping("/post")
    public Evento saveBlogPost(@RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventoService.salvaBlogPost(eventoRequest);
    }
    @PutMapping("/post/{id}")
    public Evento updateBlogPost(@PathVariable int id, @RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventoService.aggiornaBlogPost(id, eventoRequest);
    }

    @DeleteMapping("/post/{id}")
    public void deleteBlogPost(@PathVariable int id){
        eventoService.cancellaBlogPost(id);
    }
}
