import { useState } from "react"

function MessageBar({ send }) {
  let [msg, setMsg] = useState("")
  let [user, setUser] = useState("")
  return (
    <div className="messageBar">
      <input className="user" value={user} onChange={(e) => setUser(e.target.value)} />
      <input className="message" value={msg} onChange={(e) => setMsg(e.target.value)} />
      <button onClick={() => send(user, msg)}>Send</button>
    </div>
  )
}

function Chat({ messages }) {
  let mess = <ol>{messages.map((obj) => (<li><span><strong>{obj.id}</strong>: </span>{obj.mex}</li>))}</ol>
  let noMess = <span className="nomsg">no messages</span>
  return (
    <div className="mainChat">{
      messages.length === 0 ? noMess : mess}
    </div>
  )
}

export function SimpleChat() {
  let [messages, setMessages] = useState([])

  function send(id, mex) {
    let newMessages = [...messages]
    newMessages.push({ id, mex })
    setMessages(newMessages)
  }

  return (
    <div className="container">
      <h1>Chat</h1>
      <div>
        <MessageBar send={send} />
        <Chat messages={messages} />
      </div>
    </div>
  )
}
