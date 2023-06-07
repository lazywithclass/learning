function Product({ title, image }) {

  let answer = 42

  return (
    <div>
      <span>{title}</span>
      <img className="product-image" src={image} />
      <span>{answer}</span>
    </div>
  )
}

export default Product
