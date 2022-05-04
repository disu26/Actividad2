import React, { useState, useEffect } from 'react';

/**
 * String con la conexión al api de spring.
 */
const HOST_API = "http://localhost:9090/api/todo";

const TodoContext = React.createContext();

function TodoProvider(props) {

  const [todos, setTodos] = useState([]);

  /**
   * Se capturan los datos con un fetch que hace un get al host_api.
   */
  const mostrarTodos = async() => {
    const response = await fetch(HOST_API, {
      method: "GET",
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'same-origin'
    });
    const todosList = await response.json();
    setTodos(todosList.data);
  }

  useEffect(() => {
    mostrarTodos();
  }, [todos])
  
  const [searchValue, setSearchValue] = useState('');
  const [openModal, setOpenModal] = useState(false);

  const completedTodos = todos.filter(todo => !!todo.completado).length;
  const totalTodos = todos.length;

  let searchedTodos = [];

  if (!searchValue.length >= 1) {
    searchedTodos = todos;
  } else {
    searchedTodos = todos.filter(todo => {
      const todoText = todo.text.toLowerCase();
      const searchText = searchValue.toLowerCase();
      return todoText.includes(searchText);
    });
  }

  /**
   * Se crean usuarios con un fetch que utiliza el metodo post y envia los datos del body en formato JSON.
   * @param {} text 
   */
  const addTodo = async (text) => {
    const newTodo = {
      text: text
    }
    await fetch(HOST_API, {
      method: "POST",
      mode: 'cors',
      body: JSON.stringify(newTodo),
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'same-origin'
    })
    
  };

  /**
   * Se hace una actualización parcial para indicar que se completó el todo.
   * @param {*} id 
   */
  const completeTodo = async (id) => {
    await fetch(`${HOST_API}/updateCompletado/${id}`, {
      method: "PATCH"
    })
  };

  /**
   * Se hace uso de un fetch con metodo DELETE para eliminar un todo.
   * @param {*} id 
   */
  const deleteTodo = async (id) => {
    await fetch(HOST_API+ "/" +id, {
      method: "DELETE"
    })
  };
  
  return (
    <TodoContext.Provider value={{
      totalTodos,
      completedTodos,
      searchValue,
      setSearchValue,
      searchedTodos,
      addTodo,
      completeTodo,
      deleteTodo,
      openModal,
      setOpenModal,
    }}>
      {props.children}
    </TodoContext.Provider>
  );
}

export { TodoContext, TodoProvider };
