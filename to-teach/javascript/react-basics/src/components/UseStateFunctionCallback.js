import { useEffect, useState } from 'react'


export function UseStateFunctionCallback() {
  const [content, setContent] = useState('')
  const [sum, setSum] = useState(0)
  const [ok, setOk] = useState(false)
  const [clickedCount, setClickedCount] = useState(0)

  function onChangeMadness(e) {
    setContent(previousContent =>
      (e.target.value+previousContent)
        .split('')
        .filter(l => Math.random() > 0.5 ? l : '')
        .join('')
    )
  }

  function onChangeSum(e) {
    console.log(e.target.value)
    setSum(previousSum => previousSum + parseInt(e.target.value, 10))
  }

  function onChangeCheckbox() {
    setOk(prev => !prev)
  }

  return (
    <div className="container">
      <h2>useState function callback</h2>
      <input
        value={content}
        onChange={onChangeMadness}/>This is madness!
      <br />
      <br />
      <input
        value={sum}
        onChange={onChangeSum}/>This is "sum".
      <input
        type="checkbox"
        value={ok}
        onChange={onChangeCheckbox}
      />
      <br />
      <br />
      {clickedCount}
      <ChildComponent setClickedCount={setClickedCount} />
    </div>
  )
}

function ChildComponent({ setClickedCount }) {
  // in this scenario we don't have access to clickedCount here
  // so we use the callback version of the set state function
  function callback(count) {
    return count + 1
  }

  return (
    <button
      onClick={() => setClickedCount(callback)}
    >Click me</button>
  )
}
