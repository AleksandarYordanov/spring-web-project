package project.spring.project.admin.type.service;

import project.spring.project.admin.type.model.TypeDTO;

import java.util.List;

public interface TypeService {
    List<TypeDTO> getAll();
    void create(TypeDTO typeDTO);

    void create(TypeDTO type, List<Long> productIds);

    TypeDTO getById(Long id);

    void deleteById(Long id);

    void update(TypeDTO type, List<Long> productIds);
}
