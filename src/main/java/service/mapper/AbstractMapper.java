package service.mapper;

import org.mapstruct.Mapper;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public interface AbstractMapper<E, D> {

    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntityList(List<D> listDTO);

    List<D> toDTOList(List<E> entityList);
}
