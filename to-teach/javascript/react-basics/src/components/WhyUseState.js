import { useState } from 'react'

// The following  will never show the updated count in the HTML
// because the HTML won't be rendered whenever count changes
//
// export function WhyUseState() {
//   let count = 0
//   return (
//     <div className="container">
//       <h2>Why use state</h2>
//       <button onClick={() => {
//         count++
//         console.log(count)
//       }}>Click me!</button>
//       {count}
//     </div>
//   )
// }

export function WhyUseState() {

  // 1) useState
  const [count, setCount] = useState(0)

  return (
    <div className="container">
      <h2>Why use state</h2>
      <button onClick={() => {
        // 2) invoke the setter function
        setCount(count + 1)
        console.log(count)

        // or we could use
        // setCount(prev => {
        //   let newCount = prev + 1
        //   console.log(newCount)
        //   return newCount
        // })
      }}>Click me!</button>
      {count}
    </div>
  )
}
