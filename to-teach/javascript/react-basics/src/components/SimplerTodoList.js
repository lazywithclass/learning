import { useState, useEffect } from 'react'


export function SimplerTodoList() {
  const [todo, setTodo] = useState('')
  const [todos, setTodos] = useState([])

  function onButtonClick() {
    // const newTodos = Array.from(todos)
    // const newTodos = todos.slice()
    const newTodos = [...todos]
    newTodos.push(todo)
    setTodos(newTodos)
  }

  function onLiClick(i) {
    const newTodos = [...todos]
    newTodos.splice(i, 1)
    setTodos(newTodos)
  }

  return (
    <div className="container">
      <h2>Simpler todo list</h2>
      <div>
        <input
          type="text"
          value={todo}
          onChange={(e) => setTodo(e.target.value)}
        />
        <button onClick={onButtonClick}>Add</button>
        <ol>
          {
            todos.map((t, i) =>
              <li onClick={() => onLiClick(i)} key={i}>{t}</li>)
          }
        </ol>
      </div>
    </div>
  )
}
