package service;

import dto.CustomerDTO;
import entity.CustomerEntity;
import lombok.AllArgsConstructor;
import repository.CustomerRepository;
import service.mapper.CustomerMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    private final CustomerMapper customerMapper;


    public List<CustomerDTO> findAllCustomers() {
        List<CustomerEntity> customerEntities = this.customerRepository.listAll();
        return this.customerMapper.toDTOList(customerEntities);
    }

    public void updateCustomer(Long id) {
        CustomerDTO customerDTO = this.findByID(id);
        CustomerEntity customerEntity = this.customerMapper.toEntity(customerDTO);
        this.createCustomer(this.customerMapper.toDTO(customerEntity));
        this.customerMapper.toDTO(customerEntity);
    }

    public void createCustomer(CustomerDTO customerDTO) {

        CustomerEntity customerEntity = convertToCustomerEntity(customerDTO);
        if (Objects.isNull(customerEntity.getId())) {
            this.customerRepository.persist(customerEntity);
        }

        this.customerRepository.persist(this.customerMapper.toEntity(customerDTO));
    }

    public CustomerDTO findByID(Long id) {
        return this.customerMapper.toDTO(this.customerRepository.findById(id));
    }

    private CustomerEntity convertToCustomerEntity(CustomerDTO customerDTO) {
        return this.customerMapper.toEntity(customerDTO);
    }

    public void delete(Long id) {
        CustomerDTO entity = this.findByID(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        this.customerRepository.delete(this.customerMapper.toEntity(entity));
    }
}
