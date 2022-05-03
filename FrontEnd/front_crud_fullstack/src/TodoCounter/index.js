import React from 'react';
import { TodoContext } from '../TodoContext';
import './TodoCounter.css';
import { FcApproval } from "react-icons/fc";

function TodoCounter() {
  const { totalTodos, completedTodos } = React.useContext(TodoContext);
  
  return (
    <h2 className="TodoCounter">
      Has completado 
      <small className="text-muted"> {completedTodos} de {totalTodos} </small>
      TO DOs <FcApproval />
    </h2>
  );
}

export { TodoCounter };
