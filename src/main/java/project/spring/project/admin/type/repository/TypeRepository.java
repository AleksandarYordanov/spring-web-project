package project.spring.project.admin.type.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.type.model.TypeEntity;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {



}
