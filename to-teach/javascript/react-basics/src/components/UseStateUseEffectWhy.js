// This component shows how useEffect is called with regard to its dependencies

import { useState, useEffect } from "react"

export function UseStateUseEffectWhy() {
  const [inputA, setInputA] = useState('')
  const [inputB, setInputB] = useState('')
  const [ok, setOk] = useState()

  // useful for data fetching, no dependencies
  useEffect(() => {
    console.log("Triggers only on load")
  }, [])

  // both states changed
  useEffect(() => {
    setOk(inputA && inputB ? 'both changed' : '')
  }, [inputA, inputB])

  // only inputA changed
  useEffect(() => {
    console.log(`InputA changed to: ${inputA}`)
  }, [inputA])

  // only inputB changed
  useEffect(() => {
    console.log(`InputB changed to: ${inputB}`)
  }, [inputB])

  return (
    <div className="container">
      <h2>useState and useEffect Why</h2>
      <input value={inputA} onChange={(e) => setInputA(e.target.value)} ></input>
      <input value={inputB} onChange={(e) => setInputB(e.target.value)} ></input>
      <span>{ok}</span>
    </div>
  )
}
