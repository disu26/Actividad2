package co.com.sofka.back_crud_fullstack.repository;

import co.com.sofka.back_crud_fullstack.domain.TodoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para el todo.
 *
 * @version 1.0.0 2022-05-04
 * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
 * @since 1.0.0
 */
@Repository
public interface TodoRepository extends JpaRepository<TodoDomain, Long> {

    /**
     * Query para actualizar el campo completado de un todo.
     *
     * @param id del todo que se quiere actualizar
     * @param completado nuevo valor de completado
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query("update TodoDomain todo set todo.completado= :completado where todo.id = :id")
    void updateCompletado(
            @Param(value = "id") Long id,
            @Param(value = "completado") boolean completado
    );
}
