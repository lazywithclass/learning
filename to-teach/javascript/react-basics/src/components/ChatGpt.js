import { useState, useEffect } from 'react'

export function ChatGpt() {
  const [response, setResponse] = useState('')


  useEffect(() => {
    async function openAiCall() {

      const res = await fetch("https://api.openai.com/v1/completions", {
        method: "POST",
        body: JSON.stringify({
          prompt: "Mario: Hi, how are you?",
          temperature: 0.5,
          model: "text-davinci-003" // try 3
        }),
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          Authorization:
            "Bearer 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"
        }
      })
      const json = await res.json()

      console.log(json)
      console.log(json.choices[0].text)
      setResponse(json.choices[0].text)
    }

    openAiCall()
  }, [])

  function inputChanged(e) {
    console.log(e.target.value)
  }

  return (
    <div className="container">
      <h2>Chat Gpt</h2>
      <input onChange={inputChanged} />
      <div>
        {response}
      </div>
    </div>
  )
}
