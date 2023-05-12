// cleaner implementation with map over for loop

export function WhyLoopingWithMap() {
  const arr = [1, 2, 3, 4, 5, 6]

  // 1st way
  // const divs = []
  // for (let i = 0; i < arr.length; i++) {
  //   divs.push(<div>{i}</div>)
  // }

  return (
    <div className="container">
      <h2>Why looping with map</h2>
      {/* {divs} */}
      {arr.map((n, i) => <div key={i}>{n}</div>)}
    </div>
  )
}
