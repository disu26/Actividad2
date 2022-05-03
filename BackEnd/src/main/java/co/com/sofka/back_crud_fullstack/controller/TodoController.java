package co.com.sofka.back_crud_fullstack.controller;

import co.com.sofka.back_crud_fullstack.domain.TodoDomain;
import co.com.sofka.back_crud_fullstack.service.TodoService;
import co.com.sofka.back_crud_fullstack.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/todo")
public final class TodoController {

    @Autowired
    TodoService service;

    private Response response = new Response();

    private HttpStatus httpStatus = HttpStatus.OK;

    @GetMapping()
    public ResponseEntity<Response> getTodos(){
        response.restart();
        try {
            response.data = service.list();
            httpStatus = HttpStatus.OK;
        }catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response> getTodoById(@PathVariable("id") Long id){
        response.restart();
        var todo = service.findById(id);
        try {
            if(todo.isPresent()){
                response.data = todo.get();
                httpStatus = HttpStatus.OK;
            }else {
                httpStatus = HttpStatus.NOT_FOUND;
            }
        }catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PostMapping()
    public ResponseEntity<Response> createTodo(@RequestBody TodoDomain todo){
        response.restart();
        try{
            response.data = service.createTodo(todo);
            httpStatus = HttpStatus.CREATED;
        }catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Response> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDomain todo){
        response.restart();
        try {
            response.data = service.update(id, todo);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response> deleteTodo(@PathVariable("id") Long id){
        response.restart();
        try {
            response.data = service.delete(id);
            if(Boolean.TRUE.equals(response.data)){
                response.message = "Todo eliminado correctamente";
                httpStatus = HttpStatus.OK;
            }else {
                response.message = "Todo no encontrado";
                httpStatus = HttpStatus.NOT_FOUND;
            }
        }catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    private void getErrorMessageInternal(Exception exception){
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
