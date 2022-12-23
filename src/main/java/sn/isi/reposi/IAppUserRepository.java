package sn.isi.reposi;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.isi.entities.AppUserEntity;

public interface IAppUserRepository extends JpaRepository<AppUserEntity, Integer> {
 //AppUser findByEmail(String email);
	
}
