package co.com.sofka.back_crud_fullstack.repository;

import co.com.sofka.back_crud_fullstack.domain.TodoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoDomain, Long> {
}
