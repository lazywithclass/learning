import './App.css'
import './Components.css'

import { BrowserRouter, Routes, Route } from "react-router-dom"
import { Outlet, Link } from "react-router-dom"

import { SimplerTodoList } from './components/SimplerTodoList'
import { TodoList } from './components/TodoList'
import { AsyncCall } from './components/AsyncCall'
import { UseStateAsyncNature } from './components/UseStateAsyncNature'
import { UseStateFunctionCallback } from './components/UseStateFunctionCallback'
import { UseStateUseEffect } from './components/UseStateUseEffect'
import { UseStateUseEffectWhy } from './components/UseStateUseEffectWhy'
import { TooManyReRenders } from './components/TooManyReRenders'
import { DynamicInputs } from './components/DynamicInputs'

function Main() {
  return (
    <>
      <header className="App-header">
        React basics
      </header>
      <div className="sidebar">
        <nav>
          <ul>
            <li><Link to="/simplertodolist">SimplerTodoList</Link></li>
            <li><Link to="/todolist">TodoList</Link></li>
            <li><Link to="/asynccall">AsyncCall</Link></li>
            <li><Link to="/usestateasyncnature">UseStateAsyncNature</Link></li>
            <li><Link to="/usestatefunctioncallback">UseStateFunctionCallback</Link></li>
            <li><Link to="/usestateuseffect">UseStateUseEffect</Link></li>
            <li><Link to="/usestateuseffectwhy">UseStateUseEffectWhy</Link></li>
            <li><Link to="/toomanyrerenders">TooManyReRenders</Link></li>
            <li><Link to="/dynamicinputs">DynamicInputs</Link></li>
          </ul>
        </nav>
        <Outlet />
      </div>
    </>
  )
}

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />}>
          <Route index path="simplertodolist" element={<SimplerTodoList />} />
          <Route path="todolist" element={<TodoList />} />
          <Route path="asynccall" element={<AsyncCall />} />
          <Route path="usestateasyncnature" element={<UseStateAsyncNature />} />
          <Route path="usestatefunctioncallback" element={<UseStateFunctionCallback/>} />
          <Route path="usestateuseffect" element={<UseStateUseEffect />} />
          <Route path="usestateuseffectwhy" element={<UseStateUseEffectWhy />} />
          <Route path="toomanyrerenders" element={<TooManyReRenders/>} />
          <Route path="dynamicinputs" element={<DynamicInputs />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
