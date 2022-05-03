import React, {useState} from 'react';
import { TodoContext } from '../TodoContext';
import './TodoForm.css';
import { useForm } from 'react-hook-form'

function TodoForm() {

  const {  handleSubmit } = useForm();

  const [newTodoValue, setNewTodoValue] = useState('');

  const [errorMessage, seterrorMessage] = useState(false);

  const {
    addTodo,
    setOpenModal,
  } = React.useContext(TodoContext);
  
  const onChange = (event) => {
    setNewTodoValue(event.target.value);
    seterrorMessage(false)
  };

  const onCancel = () => {
    setOpenModal(false);
  };

  const onSubmit = (event) => {
    if(newTodoValue.length > 0){
      addTodo(newTodoValue);
      setOpenModal(false);
      return;
    }
    seterrorMessage(true);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <label>Escribe tu nuevo TO DO</label>
      <textarea
        value={newTodoValue}
        onChange={onChange}
        placeholder="Cortar la cebolla para el almuerzo"
      />
      <span className="text-danger text-small d-block mb-2">
          {errorMessage ? 'No se puede crear un TO DO vacío' : '' }
      </span>
      <div className="TodoForm-buttonContainer">
        <button
          type="button"
          className="TodoForm-button TodoForm-button--cancel"
          onClick={onCancel}
          >
          Cancelar
        </button>
        <button
          type="submit"
          className="TodoForm-button TodoForm-button--add"
        >
          Añadir
        </button>
      </div>
    </form>
  );
}

export { TodoForm };
