package project.spring.project.admin.type.service;

import project.spring.project.admin.type.model.TypeChildDTO;
import project.spring.project.admin.type.model.TypeDTO;
import project.spring.project.admin.type.model.TypeSelfDTO;

import java.util.List;

public interface TypeService {
    List<TypeChildDTO> getAll();
    void create(TypeSelfDTO type);
    void create(TypeSelfDTO type, List<Long> productIds);
    TypeChildDTO getById(Long id);
    void deleteById(Long id);
    void update(TypeSelfDTO typeSelfDTO, List<Long> categoryIds);
}
