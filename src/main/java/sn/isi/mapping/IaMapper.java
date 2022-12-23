package sn.isi.mapping;

import org.mapstruct.Mapper;

import sn.isi.dto.Ia;
import sn.isi.entities.IaEntity;

@Mapper
public interface IaMapper {
	Ia toIa(IaEntity iaEntity);
    IaEntity fromIa(Ia ia);
}
