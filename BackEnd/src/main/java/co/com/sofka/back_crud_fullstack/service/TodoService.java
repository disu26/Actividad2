package co.com.sofka.back_crud_fullstack.service;

import co.com.sofka.back_crud_fullstack.domain.TodoDomain;
import co.com.sofka.back_crud_fullstack.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    @Transactional(readOnly = true)
    public List<TodoDomain> list(){
        return repository.findAll();
    }

    @Transactional
    public TodoDomain createTodo(TodoDomain todo){
        return repository.save(todo);
    }

    @Transactional
    public TodoDomain update(Long id, TodoDomain todo){
        todo.setId(id);
        return repository.save(todo);
    }

    @Transactional
    public TodoDomain updateCompleted(TodoDomain todo, Boolean completado){
        repository.updateCompletado(todo.getId(), completado);
        var newTodo = findById(todo.getId());
        return newTodo.get();
    }

    @Transactional
    public Boolean delete(Long id){
        var todo = repository.findById(id);
        if(todo.isPresent()){
            repository.delete(todo.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Transactional(readOnly = true)
    public Optional<TodoDomain> findById(Long id){
        return repository.findById(id);
    }
}
