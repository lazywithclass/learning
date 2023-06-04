import { useState } from 'react'
import { Text, View, Pressable, StyleSheet } from 'react-native'

import Styles from './Styles'


export default function Question({ title, answers, isCorrect, addCorrected }) {
  return (
    <View style={styles.question}>
      <Text>{title}</Text>
      {
        answers.map((a, i) =>
          <Answer
            key={i}
            answer={a}
            addCorrected={addCorrected}
            isCorrect={isCorrect} />)
      }
    </View >
  )
}

function Answer({ answer, isCorrect, addCorrected }) {
  const [wasClicked, setWasClicked] = useState(false)

  let bgColor = 'white'
  if (wasClicked && isCorrect(answer)) {
    bgColor = 'green'
  } else if (wasClicked && !isCorrect(answer)) {
    bgColor = 'red'
  }
  return (
    <Pressable
      onPress={() => {
        if (isCorrect(answer) && !wasClicked) {
          addCorrected()
        }
        setWasClicked(true)
      }}>
      <Text style={{ ...styles.option, ...Styles.text, backgroundColor: bgColor }}>{answer != '' ? answer : ' '}</Text>
    </Pressable>
  )
}

const styles = StyleSheet.create({
  question: {
    display: 'flex',
    flexDirection: 'column'
  },
  option: {
    borderWidth: 1,
    padding: 5,
    margin: 5
  }
})
