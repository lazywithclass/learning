// This component shows the link between useState and useEffect

import { useState, useEffect } from "react"

export function UseStateUseEffect() {
  const [count, setCount] = useState(0)

  function onClick() {
    setCount(count + 1)
    // count here has the "old" value
  }

  useEffect(() => {
    console.log(`useEffect: ${count}`);
    // count's value is up to date
  }, [count])

  return (
    <div className="container">
      <h2>useState and useEffect link</h2>
      <div>
        <button onClick={onClick}>Click me!</button>
        <span>{count}</span>
      </div>
    </div>
  )
}
