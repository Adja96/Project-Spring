package sn.isi.mapping;

import org.mapstruct.Mapper;
import sn.isi.dto.Ief;
import sn.isi.entities.IefEntity;

@Mapper
public interface IefMapper {
	Ief toIef(IefEntity iefEntity);
    IefEntity fromIef(Ief ief);
}
