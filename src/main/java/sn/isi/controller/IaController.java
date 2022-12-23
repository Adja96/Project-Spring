package sn.isi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sn.isi.dto.Ia;
import sn.isi.service.IaService;

@RestController
@RequestMapping("/ia")
public class IaController {
    IaService iaService;
	  

    @GetMapping
    public List<Ia> getIas() {
        return iaService.getIas();
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
   // @IsAdmin
    public Ia createIa(@Valid @RequestBody Ia ia) {
        return iaService.createIa(ia);
    }
    
    @PutMapping("/{id}")
    //@IsAdmin
    public Ia updateIa(@PathVariable("id") int id, @Valid @RequestBody Ia ia) {
        return iaService.updateIa(id, ia);
    }
    
    
    
    @GetMapping("/{id}")
    public Ia getIa(@PathVariable("id") int id) {
        return iaService.getIa(id);
    }


    @DeleteMapping("/{id}")
    //@IsAdmin
    public void deleteIa(@PathVariable("id") int id) {
        iaService.deleteIa(id);
    }
}