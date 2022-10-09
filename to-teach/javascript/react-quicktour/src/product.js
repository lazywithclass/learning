function Product(props) {
  return (
    <div>
      <span>{props.title}</span>
      <img src={props.image} />
    </div>
  )
}

export default Product
