import { useState, useRef, useEffect } from 'react'
import { StyleSheet, Text, View, Pressable } from 'react-native'

import Question from './Question'
import Styles from './Styles'
import { random, shuffle } from '../utils'

// TODO FAI UNA FRASE CON LA PAROLA
// TODO TRATTI DEI KANJI COME SCRIVERLI
// TODO RICERCA DI UN KANJI (API DI JINSHO)

// should also ignore same answers
function createChoice(kanji) {
  const correctAnswerIndex = random(kanji.length)

  function createAnswers(createAnswer) {
    const answers = [createAnswer(correctAnswerIndex)]
    while (answers.length < 6) {
      const wrongAnswerIndex = random(kanji.length)
      if (wrongAnswerIndex !== correctAnswerIndex) {
        const wrongAnswer = createAnswer(wrongAnswerIndex)
        answers.push(wrongAnswer)
      }
    }
    return answers
  }

  const answersMeaning = createAnswers(index => kanji[index].meaning.join(', '))
  const answersKun = createAnswers(index => kanji[index].kun.join(', '))
  const answersOn = createAnswers(index => kanji[index].on.join(', '))

  return {
    prompt: kanji[correctAnswerIndex],
    correctAnswer: kanji[correctAnswerIndex],
    answersMeaning: shuffle(answersMeaning),
    answersKun: shuffle(answersKun),
    answersOn: shuffle(answersOn)
  }
}

export default function Kanji({ kanji }) {
  const [choice, setChoice] = useState(createChoice(kanji))
  const [correctAnswers, setCorrectAnswers] = useState(0)

  useEffect(() => {
    if (correctAnswers === 3) {
      setChoice(createChoice(kanji))
      setCorrectAnswers(0)
    }
  }, [correctAnswers])

  return (
    <View style={styles.container}>
      <View style={Styles.body}>
        <Text style={{ ...Styles.text }, Styles.prompt}>{choice.prompt.kanji}</Text>
        <Question
          title="Meaning"
          key={1 + choice.prompt.kanji}
          answers={choice.answersMeaning}
          isCorrect={(answer) => choice.correctAnswer.meaning.join(', ') == answer}
          addCorrected={() => setCorrectAnswers(correctAnswers + 1)} />
        <View style={styles.questions}>
          <Question
            title="音読み (onyomi)"
            key={2+choice.prompt.kanji}
            answers={choice.answersOn}
            isCorrect={(answer) => choice.correctAnswer.on.join(', ') ==  answer}
            addCorrected={() => setCorrectAnswers(correctAnswers + 1)} />
          <Question
            title="訓読み (kunyomi)"
            key={3+choice.prompt.kanji}
            answers={choice.answersKun}
            isCorrect={(answer) => choice.correctAnswer.kun.join(', ') ==  answer}
            addCorrected={() => setCorrectAnswers(correctAnswers + 1)} />
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
  questions: {
    width: '100%',
    justifyContent: 'space-evenly',
    display: 'flex',
    flexDirection: 'row'
  }
})
