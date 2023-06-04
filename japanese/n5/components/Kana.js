import { useState } from 'react'
import { StyleSheet, Text, View, Pressable } from 'react-native'

import { random, shuffle } from '../utils'
import Styles from './Styles'


// should also ignore same answers
function createChoice(toRomaji, kana, romaji) {
  const correctAnswerIndex = random(kana.length)
  const possibleAnswers = [toRomaji ? kana[correctAnswerIndex] : romaji[correctAnswerIndex]]
  while (possibleAnswers.length < 6) {
    const wrongAnswerIndex = random(kana.length)
    if (wrongAnswerIndex !== correctAnswerIndex) {
      const wrongAnswer = toRomaji ? kana[wrongAnswerIndex] : romaji[wrongAnswerIndex]
      possibleAnswers.push(wrongAnswer)
    }
  }

  return {
    prompt: toRomaji ? romaji[correctAnswerIndex] : kana[correctAnswerIndex],
    correctAnswer: toRomaji ? kana[correctAnswerIndex] : romaji[correctAnswerIndex],
    possibleAnswers: shuffle(possibleAnswers)
  }
}

export default function Kana({ kana, romaji}) {
  const [toRomaji, setToRomaji] = useState(true)
  const [choice, setChoice] = useState(createChoice(toRomaji, kana, romaji))
  const [score, setScore] = useState(0)
  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text>Score: {score}</Text>
        <Pressable onPress={() => {
          setToRomaji(toRomaji => !toRomaji)
          setChoice(createChoice(toRomaji, kana, romaji))
        }}>
          <Text> - Toggle</Text>
        </Pressable>
      </View>
      <View style={styles.body}>
        <Text style={{ ...Styles.text }}>{choice.prompt}</Text>
        <View style={styles.options}>
          {
            choice.possibleAnswers.map((a, i) => (
              <Pressable key={i} onPress={() => {
                let correct = choice.correctAnswer == a ? 1 : 0
                setScore(prevScore => prevScore + correct)
                setChoice(createChoice(toRomaji, kana, romaji))
              }}>
                <Text style={{ ...styles.option, ...Styles.text }}>{a}</Text>
              </Pressable>
            ))
          }
        </View>
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    display: 'flex',
    height: '100%',
    alignItems: 'center',
    justifyContent: 'center'
  },
  header: {
    display: 'flex',
    flexDirection: 'row',
    marginTop: 50,
    marginBottom: 15
  },
  options: {
    display: 'flex',
  },
  option: {
    borderWidth: 1,
    padding: 5,
    margin: 5
  }
})
