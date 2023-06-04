import { useState, useEffect } from 'react'
import { StyleSheet, Text, View } from 'react-native'


import Question from './Question'
import Styles from './Styles'
import { random, shuffle } from '../utils'


// should also ignore same answers
function createChoice(words) {
  const correctAnswerIndex = random(words.length)

  function createAnswers(createAnswer) {
    const answers = [createAnswer(correctAnswerIndex)]
    while (answers.length < 6) {
      const wrongAnswerIndex = random(words.length)
      if (wrongAnswerIndex !== correctAnswerIndex) {
        const wrongAnswer = createAnswer(wrongAnswerIndex)
        answers.push(wrongAnswer)
      }
    }
    return answers
  }

  const answersMeaning = createAnswers(index => words[index].meaning.join(', '))
  const answersYomikata = createAnswers(index => words[index].yomikata)

  return {
    prompt: words[correctAnswerIndex],
    correctAnswer: words[correctAnswerIndex],
    answersMeaning: shuffle(answersMeaning),
    answersYomikata: shuffle(answersYomikata)
  }
}

export default function Words({ words }) {
  const [choice, setChoice] = useState(createChoice(words))
  const [correctAnswers, setCorrectAnswers] = useState(0)

  useEffect(() => {
    if (correctAnswers == 2) {
      setChoice(createChoice(words))
      setCorrectAnswers(0)
    }
  }, [correctAnswers])

  return (
    <View style={styles.container}>
      <View style={Styles.body}>
        <Text>{choice.prompt.furigana}</Text>
        <Text style={{ ...Styles.text }, Styles.prompt}>{choice.prompt.kanji}</Text>
        <View style={styles.questions}>
          <Question
            key={1+choice.prompt.kanji}
            title="Meaning"
            answers={choice.answersMeaning}
            isCorrect={(answer) => choice.correctAnswer.meaning.join(', ') == answer}
            addCorrected={() => setCorrectAnswers(correctAnswers + 1)} />
          <Question
            key={2+choice.prompt.kanji}
            title="読み方 (yomikata)"
            answers={choice.answersYomikata}
            isCorrect={(answer) => choice.correctAnswer.yomikata == answer}
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
