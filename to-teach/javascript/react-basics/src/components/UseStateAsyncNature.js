// This component shows the async nature of useState

import { useState } from "react"

export function UseStateAsyncNature() {
  const [checked, setChecked] = useState(false)

  // use useEffect to avoid this problem

  return (
    <div className="container">
      <h2>useState async nature</h2>
      <input
        type="checkbox"
        value={checked}
        onChange={() => {
          setChecked(prev => !prev)
          // look at how this logged value changes
          // and compare to what it's shown on the page
          console.log(checked)
        }}/>
      {checked.toString()}
    </div>
  )
}
