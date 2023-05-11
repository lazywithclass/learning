// In this component we show
// * how to make an HTTP call and deal with the results
// * how to deal with checkboxes

import { useState, useEffect } from 'react'

export function TodoList() {
  const [todos, setTodos] = useState([])

  useEffect(() => {
    async function call() {
      const res = await fetch("https://jsonplaceholder.typicode.com/todos")
      const json = await res.json()
      setTodos(json.slice(0, 5))
    }
    call()
  }, [])

  const handleCheckboxClick = (id) => {
    setTodos((prevTodos) =>
      prevTodos.map((todo) =>
        todo.id === id ? {
          ...todo,
          completed: !todo.completed
        } : todo
      )
    )
  }

  const completedTodos = todos.filter((todo) => todo.completed)
  const incompleteTodos = todos.filter((todo) => !todo.completed)
  const reorderedTodos = incompleteTodos.concat(completedTodos)

  console.log(reorderedTodos)

  return (
    <div className="container">
      <h2>Todo List</h2>
      <ul className="todo">
        {reorderedTodos.map((todo) => (
          <li key={todo.id} style={{ opacity: todo.completed ? 0.5 : 1 }}>
            <input
              type="checkbox"
              checked={todo.completed}
              onChange={() => handleCheckboxClick(todo.id)}
            />
            {todo.title}
          </li>
        ))}
      </ul>
    </div>
  )
}
