package co.com.sofka.back_crud_fullstack.util;

/**
 * Clase utilizada para un mejor manejo de las respuestas API
 *
 * @version 1.0.0 2022-05-04
 * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
 * @since 1.0.0
 */
public class Response {
    /**
     * Indica si existe un error o no en la respuesta de la API.
     */
    public Boolean error = false;

    /**
     * Mensaje de la API cuando es utilizada.
     */
    public String message = "";

    /**
     * Información que retorna la API.
     */
    public Object data;

    /**
     * Restaura la clase a los valores iniciales.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    public void restart() {
        error = false;
        message = "";
        data = null;
    }
}
