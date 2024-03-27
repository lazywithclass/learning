import './App.css';
import Login from './components/Login.js'
import Header from './components/Header.js'

function App() {
  return (
    <div className="App">
      <Header />
      <Login />
      <Temp />
    </div>
  );
}

function Temp() {
  function getUser() {
    fetch("http://localhost:3001/sessions", {
      credentials: "include",
    })
  }

  return (
    <>
      <button onClick={getUser}>Get user</button>
    </>
  )

}

export default App;
