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
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/eventi")
    public Page<Evento> getAll(Pageable pageable){

        return eventoService.cercaTuttiGliEventi(pageable);
    }
    @GetMapping("/eventi/{id}")
    public Evento getBlogPostById(@PathVariable int id){
        return eventoService.cercaEventoPerId(id);

    }
    @PostMapping("/eventi")
    public Evento saveEvento(@RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventoService.salvaEvento(eventoRequest);
    }

    @PatchMapping("/eventi/aggiungilista/{id}")

    public  Evento updateList(@PathVariable int id,@RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return  eventoService.aggiornaListaPartecipanti(id,eventoRequest);
    }


    @PutMapping("/eventi/{id}")
    public Evento updateEvento(@PathVariable int id, @RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventoService.aggiornaEvento(id, eventoRequest);
    }

    @DeleteMapping("/eventi/{id}")
    public void deleteEvento(@PathVariable int id){
        eventoService.cancellaEvento(id);
    }
}
