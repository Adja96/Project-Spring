package sn.isi.controller;

import java.util.List;

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

import lombok.AllArgsConstructor;
import sn.isi.dto.AppRoles;
import sn.isi.service.AppRolesService;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class AppRolesController {
	  AppRolesService appRolesService;
	  

	    @GetMapping
	    public List<AppRoles> getAppRoles() {
	        return appRolesService.getAppRoles();
	    }


	    @PostMapping
	    @ResponseStatus(code = HttpStatus.CREATED)
	   // @IsAdmin
	    public AppRoles createAppRoles(@Valid @RequestBody AppRoles appRoles) {
	        return appRolesService.createAppRoles(appRoles);
	    }
	    
	    @PutMapping("/{id}")
	    //@IsAdmin
	    public AppRoles updateAppRoles(@PathVariable("id") int id, @Valid @RequestBody AppRoles appRoles) {
	        return appRolesService.updateAppRoles(id, appRoles);
	    }
	    
	    
	    
	    @GetMapping("/{id}")
	    public AppRoles getAppRoles(@PathVariable("id") int id) {
	        return appRolesService.getAppRole(id);
	    }


	    @DeleteMapping("/{id}")
	    //@IsAdmin
	    public void deleteAppRoles(@PathVariable("id") int id) {
	        appRolesService.deleteAppRoles(id);
	    }
}
