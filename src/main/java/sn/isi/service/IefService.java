package sn.isi.service;



import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sn.isi.dto.Ief;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.IefMapper;
import sn.isi.reposi.IIefRepository;

@Service
public class IefService {
    private IIefRepository iefRepository;
	private IefMapper iefMapper;
	MessageSource messageSource;

	public IefService(IIefRepository iefRepository, IefMapper iefMapper,MessageSource messageSource) {
		this.iefRepository = iefRepository;
		this.iefMapper=iefMapper;
		this.messageSource=messageSource;
	}
    @Transactional(readOnly = true)
    public Page<Ief> getIefs(Pageable pageable) {
        return iefRepository.findAll(pageable).map(iefMapper::toIef);
    }
    @Transactional(readOnly = true)
    public Ief getIef(int id) {
        return iefMapper.toIef(iefRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }
    @Transactional
    public Ief createIef(Ief ief) {
        iefRepository.findById(ief.getId())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("ief.exists", new Object[]{ief.getId()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return iefMapper.toIef(iefRepository.save(iefMapper.fromIef(ief)));
    }
    @Transactional
    public Ief updateIef(int id, Ief ief){
        return iefRepository.findById(id)
                .map(entity -> {
                    ief.setId(id);
                    return iefMapper.toIef(iefRepository.save(iefMapper.fromIef(ief)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("ief.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }
    @Transactional
    public void deleteIef(int id) {
        try {
            iefRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("ief.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
}

}
