import { useState } from 'react'

export function DynamicInputs({ n = 5 }) {
  const tmpState = Array(n).fill('')
  const [values, setValues] = useState(tmpState)
  const filtered = values.filter(d => d != '')

  return (
    <div className="container">
      <h2>Dynamic inputs</h2>
      <div>
        {
          values.map((d, i) =>
            <input
              onChange={(e) => {
                let newValues = [...values]
                newValues[i] = e.target.value
                setValues(newValues)
              }}
              key={i} />)
        }
      </div>
      Non empty cells: {filtered.length}
      <br />
      Cells contents: {filtered.toString()}
    </div>
  )
}
