package co.com.sofka.back_crud_fullstack.ServiceTest;

import co.com.sofka.back_crud_fullstack.domain.TodoDomain;
import co.com.sofka.back_crud_fullstack.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class TodoServiceTest {
    @Autowired
    TodoRepository repository;

    @Test
    void testCreateTodo(){
        TodoDomain newTodo = new TodoDomain();
        TodoDomain todoCreated = repository.save(newTodo);
        assertNotNull(todoCreated);
    }

    @Test
    void getTodos(){
        var todosList = repository.findAll();
        assertThat(todosList).size().isPositive();
    }

    @Test
    void getTodoById(){
        Long idBuscar = 46L;
        var todoBuscado = repository.findById(idBuscar);
        assertThat(todoBuscado.get().getId()).isEqualTo(idBuscar);
    }

}
