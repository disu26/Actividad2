package co.com.sofka.back_crud_fullstack.controller;

import co.com.sofka.back_crud_fullstack.domain.TodoDomain;
import co.com.sofka.back_crud_fullstack.service.TodoService;
import co.com.sofka.back_crud_fullstack.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para el todo.
 *
 * @version 1.0.0 2022-05-04
 * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
 * @since 1.0.0
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.PATCH})
@RequestMapping("/api/todo")
public final class TodoController {

    /**
     * Servicio para el manejo del todo.
     */
    @Autowired
    TodoService service;

    /**
     * Variable para el manejo de las respuestas de las API
     */
    private final Response response = new Response();

    /**
     * Variable para el manejo del código HTTP que se responde en las API
     */
    private HttpStatus httpStatus = HttpStatus.OK;

    /**
     * Devuelve un listado con los todos creados.
     *
     * @return Objeto Response en formato JSON
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @GetMapping()
    public ResponseEntity<Response> getTodos(){
        response.restart();
        try {
            response.data = service.list();
            httpStatus = HttpStatus.OK;
        }catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Devuelve un todo de acuerdo a un id.
     *
     * @param id del todo que se desea buscar.
     * @return Objeto Response en formato JSON
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
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
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Creación de un nuevo todo.
     *
     * @param todo Objeto todo que se desea almacenar.
     * @return Objeto Response en formato JSON
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Response> createTodo(@RequestBody TodoDomain todo){
        response.restart();
        try{
            response.data = service.createTodo(todo);
            httpStatus = HttpStatus.CREATED;
        }catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Actualización total de un todo, permite modificar el texto y si está completado o no.
     *
     * @param id del todo que se desea actualizar
     * @param todo objeto con la nueva información del todo.
     * @return Objeto Response en formato JSON
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Response> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDomain todo){
        response.restart();
        try {
            response.data = service.update(id, todo);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Actualización parcial del todo, actualiza el valor de completado.
     *
     * @param id del todo que se desea actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @PatchMapping(path = "/updateCompletado/{id}")
    public ResponseEntity<Response> updateCompletado(@PathVariable("id") Long id){
        response.restart();
        try {
            var todo = service.findById(id);
            if(todo.isPresent()){
                response.data = service.updateCompleted(todo.get(), Boolean.TRUE);
                httpStatus = HttpStatus.OK;
            }else {
                response.message = "Todo no encontrado";
                httpStatus = HttpStatus.NOT_FOUND;
            }
        }catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Eliminación de un todo.
     *
     * @param id del todo que se desea eliminar
     * @return Objeto Response en formato JSON
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response> deleteTodo(@RequestBody @PathVariable("id") Long id){
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
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Adminnistrador para las excepciones del sistema.
     *
     * @param exception Objeto con la excepción.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception){
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
