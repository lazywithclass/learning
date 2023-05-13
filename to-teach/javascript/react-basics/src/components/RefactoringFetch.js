// Example of a possible refactoring on fetch as a component
// not really useful but as an example

export function RefactoringFetch() {

  return (
    <div className="container">
      <h2>Refactoring fetch</h2>
      <Fetch
        url="https://fakestoreapi.com/products/categories"
        onFetched={(products) => console.log(products)}
      />
    </div>
  )
}

function Fetch({ url, onFetched }) {
  async function call() {
    const res = await fetch(url)
    const json = await res.json()
    onFetched(json)
  }

  call()
}
