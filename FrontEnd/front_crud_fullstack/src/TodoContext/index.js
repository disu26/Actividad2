import React, { useState, useEffect } from 'react';

const HOST_API = "http://localhost:9090/api/todo";

const TodoContext = React.createContext();

function TodoProvider(props) {

  const [todos, setTodos] = useState([]);

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

  const completeTodo = async (id) => {
    await fetch(`${HOST_API}/updateCompletado/${id}`, {
      method: "PATCH"
    })
  };

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
