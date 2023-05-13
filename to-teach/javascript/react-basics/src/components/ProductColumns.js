import { useState, useEffect } from 'react'

const CATEGORIES_URL = 'https://fakestoreapi.com/products/categories'
const CATEGORY_URL = 'https://fakestoreapi.com/products/category/'

function Category({ category }) {
  return (
    <div>
      <h3>{category.category}</h3>
      <ul>
        {category.products.map((p, i) => <li key={i}>{p.title}</li>)}
      </ul>
    </div>
  )
}

export function ProductColumns() {
  const [categories, setCategories] = useState([])

  useEffect(() => {
    async function getAllCategories() {
      const res = await fetch(CATEGORIES_URL)
      const json = await res.json()

      let categories = json.map(async category => {
        const resProducts = await fetch(CATEGORY_URL+category)
        const products = await resProducts.json()
        return { category, products }
      })
      categories = await Promise.all(categories)
      setCategories(categories)
    }
    getAllCategories()
  }, [])

  return (
    <div className="container">
      <h2>Product columns</h2>
      {categories.map((c, i) => <Category key={i} category={c} />)}
    </div>
  )
}
