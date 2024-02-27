import React, { useState, useEffect  } from 'react';
import Product from './product'

function Products() {

  let [products, setProducts] = useState([])

  useEffect(() => {
    async function call() {
      let res = await fetch('https://fakestoreapi.com/products', {
	      method: 'GET'
      })
      let json = await res.json()
      setProducts(json)
    }

    call()
  }, [])

  return (
    <div>
      <div>PRODUCTS <span>({products.length})</span></div>
      {products.map((p, i) => <Product title={p.title} image={p.image} key={i} />)
      }
    </div>
  );
}

export default Products;
