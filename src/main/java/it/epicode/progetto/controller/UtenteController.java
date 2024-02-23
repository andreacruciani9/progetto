package it.epicode.w7d1t.controller;

import it.epicode.w7d1t.exception.BadRequestException;
import it.epicode.w7d1t.model.Role;
import it.epicode.w7d1t.model.Utente;
import it.epicode.w7d1t.model.UtenteRequest;
import it.epicode.w7d1t.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
