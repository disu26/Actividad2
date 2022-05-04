package co.com.sofka.back_crud_fullstack.ControllerServiceTest;

import co.com.sofka.back_crud_fullstack.repository.TodoRepository;
import co.com.sofka.back_crud_fullstack.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public final class TodoServiceMockitoTest {

    @MockBean
    TodoRepository repository;

    @Autowired
    TodoService service;

    @Test
    public void testTodoMock(){
        when(repository.findAll()).thenReturn(new ArrayList<>());
        assertThat(service.list().isEmpty());
        verify(repository).findAll();
    }
}
