import { useState } from 'react'

export function TodoListComponents() {
  const [list, setList] = useState([])

  const handleAddTodo = (text) => {
    setList([...list, { text, checked: false }])
  }

  return (
    <div className="container">
      <h2>Todo List</h2>
      <Controls onAddTodo={handleAddTodo} />
      <ToDoElements list={list} setList={setList} />
    </div>
  )
}

function Controls({ onAddTodo }) {
  const [input, setInput] = useState('')

  const handleClick = () => {
    if (input) {
      onAddTodo(input)
      setInput('')
    }
  };

  return (
    <>
      <input
        type="text"
        onChange={(e) => setInput(e.target.value)}
        value={input}
      />
      <button onClick={handleClick}>ADD</button>
    </>
  )
}

function ToDoElements({ list, setList }) {
  const tempNonFlag = list.filter((toDo) => !toDo.checked)
  const tempFlagged = list.filter((toDo) => toDo.checked)

  return (
    <>
      {tempNonFlag.map((e, i) => (
        <ToDoElement setList={setList} element={e} key={i} />
      ))}
      {tempFlagged.map((e, i) => (
        <ToDoElement setList={setList} element={e} key={i} />
      ))}
    </>
  );
}

function ToDoElement({ element, setList }) {
  const [color, setColor] = useState('black')

  const handleCheckboxChange = (e) => {
    setColor(e.target.checked ? 'grey' : 'black')
    // Create a new object with the updated checked property
    const updatedElement = { ...element, checked: e.target.checked }
    // Update the state with the new object
    setList((prevList) => {
      const index = prevList.findIndex((item) => item === element)
      if (index !== -1) {
        const newList = [...prevList]
        newList[index] = updatedElement
        return newList
      }
      return prevList
    })
  }

  return (
    <li>
      <input
        type="checkbox"
        onChange={handleCheckboxChange}
        checked={element.checked}
      />
      <span style={{ color }}>{element.text}</span>
    </li>
  )
}
