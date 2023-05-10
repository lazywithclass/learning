import { useState, useEffect } from "react"

export function AsyncCall() {
  const [products, setProducts] = useState([])

  useEffect(() => {
    async function fetchproduct() {
      let res = await fetch('https://fakestoreapi.com/products')
      let json = await res.json()
      setProducts(json)
    }

    fetchproduct()
  }, [])

  return (
    <div className="container">
      <h2>Fetch</h2>
      <div className="products">
        {
          products.map((el) => (
            <div className="product" key={el.id}>
              <h4>{el.title}</h4>
              <img src={el.image}></img>
              <p>{el.price}</p>
            </div>
          ))
        }
      </div>
    </div>
  )
}
