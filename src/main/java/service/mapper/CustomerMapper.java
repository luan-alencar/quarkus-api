package service.mapper;

import dto.CustomerDTO;
import entity.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CustomerMapper extends AbstractMapper<CustomerEntity, CustomerDTO> {

    @Override
    CustomerEntity toEntity(CustomerDTO dto);

    @Override
    CustomerDTO toDTO(CustomerEntity entity);

    @Override
    List<CustomerEntity> toEntityList(List<CustomerDTO> listDTO);

    @Override
    List<CustomerDTO> toDTOList(List<CustomerEntity> entityList);
}
