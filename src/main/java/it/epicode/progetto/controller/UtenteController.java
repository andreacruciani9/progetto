package it.epicode.progetto.controller;

import it.epicode.progetto.exception.BadRequestException;
import it.epicode.progetto.model.Utente;
import it.epicode.progetto.model.UtenteRequest;
import it.epicode.progetto.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtenteController {
    @Autowired
    private UtenteService utenteService;
    @GetMapping("/utenti")
    public List<Utente> getAll(){
        return utenteService.getAllUtenti();
    }

    @GetMapping("/utenti/{username}")
    public Utente getUtenteByUsername(@PathVariable String username){
        return utenteService.getUtenteByUsername(username);
    }

    @PutMapping("/utenti/{username}")
    public Utente updateUtente(@PathVariable String username, @RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.updateUtente(username, utenteRequest);

    }
    @PostMapping("/utenti")
    public Utente save(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.save(utenteRequest);
    }
    @DeleteMapping("/utenti/{username}")
    public void deleteUtente(@PathVariable String username){

        utenteService.deleteUtente(username);
    }
    @PatchMapping("/utenti/{username}")
    public Utente changeRole(@PathVariable String username, @RequestBody String role){
        return utenteService.updateRoleUtente(username, role);

    }
}
