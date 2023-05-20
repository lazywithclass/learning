import './App.css'
import './Components.css'

import { BrowserRouter, Routes, Route } from "react-router-dom"
import { Outlet, Link } from "react-router-dom"

import { RefactoringFetch } from './components/RefactoringFetch'
import { WhyLoopingWithMap } from './components/WhyLoopingWithMap'
import { SimplerTodoList } from './components/SimplerTodoList'
import { TodoList } from './components/TodoList'
import { AsyncCall } from './components/AsyncCall'
import { WhyUseState } from './components/WhyUseState'
import { UseStateAsyncNature } from './components/UseStateAsyncNature'
import { UseStateFunctionCallback } from './components/UseStateFunctionCallback'
import { UseStateUseEffect } from './components/UseStateUseEffect'
import { UseStateUseEffectWhy } from './components/UseStateUseEffectWhy'
import { TooManyReRenders } from './components/TooManyReRenders'
import { DynamicInputs } from './components/DynamicInputs'
import { SimpleChat } from './components/SimpleChat'
import { ProductColumns } from './components/ProductColumns'
import { ChatGpt } from './components/ChatGpt'

function Main() {
  return (
    <>
      <header className="App-header">
        React basics
      </header>
      <div className="sidebar">
        <nav>
          <ul>
            <li><Link to="/refactoringfetch">RefactoringFetch</Link></li>
            <li><Link to="/whyloopingwithmap">WhyLoopingWithMap</Link></li>
            <li><Link to="/simplertodolist">SimplerTodoList</Link></li>
            <li><Link to="/todolist">TodoList</Link></li>
            <li><Link to="/asynccall">AsyncCall</Link></li>
            <li><Link to="/whyusestate">WhyUseState</Link></li>
            <li><Link to="/usestateasyncnature">UseStateAsyncNature</Link></li>
            <li><Link to="/usestatefunctioncallback">UseStateFunctionCallback</Link></li>
            <li><Link to="/usestateuseffect">UseStateUseEffect</Link></li>
            <li><Link to="/usestateuseffectwhy">UseStateUseEffectWhy</Link></li>
            <li><Link to="/toomanyrerenders">TooManyReRenders</Link></li>
            <li><Link to="/dynamicinputs">DynamicInputs</Link></li>
            <li><Link to="/simplechat">SimpleChat</Link></li>
            <li><Link to="/productcolumns">ProductColumns</Link></li>
            <li><Link to="/chat-gpt">Chat GPT</Link></li>
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
          <Route index element={<TodoList/>} />
          <Route path="refactoringfetch" element={<RefactoringFetch />} />
          <Route path="whyloopingwithmap" element={<WhyLoopingWithMap />} />
          <Route path="simplertodolist" element={<SimplerTodoList />} />
          <Route path="todolist" element={<TodoList />} />
          <Route path="asynccall" element={<AsyncCall />} />
          <Route path="whyusestate" element={<WhyUseState />} />
          <Route path="usestateasyncnature" element={<UseStateAsyncNature />} />
          <Route path="usestatefunctioncallback" element={<UseStateFunctionCallback/>} />
          <Route path="usestateuseffect" element={<UseStateUseEffect />} />
          <Route path="usestateuseffectwhy" element={<UseStateUseEffectWhy />} />
          <Route path="toomanyrerenders" element={<TooManyReRenders/>} />
          <Route path="dynamicinputs" element={<DynamicInputs />} />
          <Route path="simplechat" element={<SimpleChat/>} />
          <Route path="productcolumns" element={<ProductColumns/>} />
          <Route path="chat-gpt" element={<ChatGpt/>} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
