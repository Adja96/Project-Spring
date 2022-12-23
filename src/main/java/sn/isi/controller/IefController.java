package sn.isi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sn.isi.dto.Ief;
import sn.isi.service.IefService;

@RestController
@RequestMapping("/ief")
public class IefController {
    private IefService iefService;

    public IefController(IefService iefService){
        this.iefService = iefService;
    
    }
    @GetMapping
    public Page<Ief> getIefs(Pageable pageable) {
        return iefService.getIefs(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ief> getIef(@PathVariable("id") int id) {
        return ResponseEntity.ok(iefService.getIef(id));

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    //@IsAdmin
    public Ief createIef(@Valid @RequestBody Ief ief) {
            return iefService.createIef(ief); 
    }

    @PutMapping("{id}")
    //@IsAdmin
    public Ief updateIef(@PathVariable("id") int id, @Valid @RequestBody Ief ief) {
            return iefService.updateIef(id, ief);
        }
        
    @DeleteMapping("{id}")
    public void deleteIef(@PathVariable("id") int id) {
        iefService.deleteIef(id);
    }      
    
}
