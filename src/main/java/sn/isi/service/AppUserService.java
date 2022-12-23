package sn.isi.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sn.isi.dto.AppUser;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.AppUserMapper;
import sn.isi.reposi.IAppUserRepository;

@Service
public class AppUserService {
	private IAppUserRepository iAppUserRepository;
	private AppUserMapper appUserMapper;
	MessageSource messageSource;

	public AppUserService(IAppUserRepository iAppUserRepository, AppUserMapper appUserMapper,MessageSource messageSource) {
		this.iAppUserRepository = iAppUserRepository;
		this.appUserMapper=appUserMapper;
		this.messageSource=messageSource;
	}
    @Transactional(readOnly = true)
    public Page<AppUser> getAppUsers(Pageable pageable) {
        return iAppUserRepository.findAll(pageable).map(appUserMapper::toAppUser);
    }
    @Transactional(readOnly = true)
    public AppUser getAppUser(int id) {
        return appUserMapper.toAppUser(iAppUserRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }
    @Transactional
    public AppUser createAppUser(AppUser appUser) {
        iAppUserRepository.findById(appUser.getId())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("client.exists", new Object[]{appUser.getId()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return appUserMapper.toAppUser(iAppUserRepository.save(appUserMapper.fromAppUser(appUser)));
    }
    @Transactional
    public AppUser updateAppUser(int id, AppUser appUser){
        return iAppUserRepository.findById(id)
                .map(entity -> {
                    appUser.setId(id);
                    return appUserMapper.toAppUser(iAppUserRepository.save(appUserMapper.fromAppUser(appUser)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }
    @Transactional
    public void deleteAppUser(int id) {
        try {
            iAppUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
}
}
