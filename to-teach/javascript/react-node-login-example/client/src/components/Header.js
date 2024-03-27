export default function Header() {

  async function logout() {
    const res = await fetch("http://localhost:3001/sessions", {
      method: "DELETE"
    })
    // TODO gestire logout
  }

  return (
    <div className="header-wrapper">
      <div>
        <span>lazywithclass</span>
        <button onClick={logout}>Logout</button>
      </div>
    </div>
  )
}
