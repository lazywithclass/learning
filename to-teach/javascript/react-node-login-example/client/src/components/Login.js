import { useState } from "react"

export default function Login() {

  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")

  const [error, setError] = useState(false)
  const [errorMessage, setErrorMessage] = useState("")

  async function login() {
    const res = await fetch("http://localhost:3001/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username, password })
    })
    const json = await res.json()
    console.log(json)
    if (res.status == 401) {
      setError(true)
      setErrorMessage(json.msg)
    } else {
      setError(false)
      setErrorMessage("")
    }
  }

  return (
    <div className="login">
      <input type="text" placeholder="username"
             value={username} onChange={(e) => setUsername(e.target.value)} />
      <input type="text" placeholder="password"
             value={password} onChange={(e) => setPassword(e.target.value)} />
      <button onClick={login}>Login</button>
      <span className={error && "error"}>{error && errorMessage}</span>
    </div>
  )
}
