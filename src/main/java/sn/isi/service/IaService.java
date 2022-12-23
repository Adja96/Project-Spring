package sn.isi.service;

import java.util.List;
import java.util.Locale;
import java.util.stream.StreamSupport;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import sn.isi.dto.Ia;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.IaMapper;
import sn.isi.reposi.IIaRepository;

@Service
public class IaService {
    private IIaRepository iaRepository;
	private IaMapper iaMapper;
	MessageSource messageSource;

	public IaService(IIaRepository iaRepository, IaMapper iaMapper,MessageSource messageSource) {
		this.iaRepository = iaRepository;
		this.iaMapper= iaMapper;
		this.messageSource=messageSource;
	}
	
	 @Transactional(readOnly= true)
	    public List<Ia> getIas() {
		 return StreamSupport.stream(iaRepository.findAll().spliterator(), false)
	                .map(iaMapper::toIa).toList();
	               // .collect(Collectors.toList());
	 }
	 
	    @Transactional(readOnly = true)
	    public Ia getIa(int id) {
	        return iaMapper.toIa(iaRepository.findById(id).orElseThrow(() ->
	                new EntityNotFoundException(messageSource.getMessage("role.notfound", new Object[]{id},
	                        Locale.getDefault()))));
	    }
	    @Transactional
	    public Ia createIa(Ia ia) {
	       
	        return iaMapper.toIa(iaRepository.save(iaMapper.fromIa(ia)));
	    }
	    
	    @Transactional
	    public  Ia  updateIa(int id,  Ia ia){
	        return iaRepository.findById(id)
	                .map(entity -> {
	                    ia.setId(id);
	                    return iaMapper.toIa(iaRepository.save(iaMapper.fromIa(ia)));
	                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("role.notfound",
	                        new Object[]{id},
	                        Locale.getDefault())));
}
	    @Transactional
	    public void deleteIa(int id) {
	        try {
	            iaRepository.deleteById(id);
	        } catch (Exception e) {
	            throw new RequestException(messageSource.getMessage("role.errordeletion", new Object[]{id},
	                    Locale.getDefault()),
	                    HttpStatus.CONFLICT);
	        }
	    }

}
