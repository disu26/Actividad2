package co.com.sofka.back_crud_fullstack.service;

import co.com.sofka.back_crud_fullstack.domain.TodoDomain;
import co.com.sofka.back_crud_fullstack.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Clase tipo Servicio para el manejo de los todo.
 *
 * @version 1.0.0 2022-05-04
 * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
 * @since 1.0.0
 */
@Service
public class TodoService {

    /**
     * Repositorio del todo.
     */
    @Autowired
    private TodoRepository repository;

    /**
     * Muestra todos los todos que hay en el sistema.
     *
     * @return Lista con todos los todos que hay en el sistema
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional(readOnly = true)
    public List<TodoDomain> list(){
        return repository.findAll();
    }

    /**
     * Crea un todo en el sistema.
     *
     * @param todo que se desea almacenar
     * @return Todo que fue creado
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional
    public TodoDomain createTodo(TodoDomain todo){
        return repository.save(todo);
    }

    /**
     * Actualiza un todo.
     *
     * @param id del todo a actualizar
     * @param todo con la nueva información a guardar
     * @return todo que fue almacenado
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional
    public TodoDomain update(Long id, TodoDomain todo){
        todo.setId(id);
        return repository.save(todo);
    }

    /**
     * Actualiza el campo completado de un todo.
     *
     * @param todo que se desea actualizar
     * @param completado nuevo valor que se desea dar
     * @return todo con la nueva información
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional
    public TodoDomain updateCompleted(TodoDomain todo, Boolean completado){
        repository.updateCompletado(todo.getId(), completado);
        var newTodo = findById(todo.getId());
        return newTodo.get();
    }

    /**
     * Elimina un todo.
     *
     * @param id del todo que se desea eliminar.
     * @return Boolean que indica si el todo fue eliminado o no.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional
    public Boolean delete(Long id){
        var todo = repository.findById(id);
        if(todo.isPresent()){
            repository.delete(todo.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Busca un todo por id.
     *
     * @param id del todo a buscar.
     * @return Objeto de tipo Optional con el todo buscado.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional(readOnly = true)
    public Optional<TodoDomain> findById(Long id){
        return repository.findById(id);
    }
}
