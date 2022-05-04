package co.com.sofka.back_crud_fullstack.repository;

import co.com.sofka.back_crud_fullstack.domain.TodoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoDomain, Long> {

    @Modifying
    @Query("update TodoDomain todo set todo.completado= :completado where todo.id = :id")
    void updateCompletado(
            @Param(value = "id") Long id,
            @Param(value = "completado") boolean completado
    );
}
