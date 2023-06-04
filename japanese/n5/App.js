import { useState, useEffect, useCallback } from 'react'
import { StatusBar } from 'expo-status-bar'
import { StyleSheet, View } from 'react-native'
import { NavigationContainer  } from '@react-navigation/native'
import { createBottomTabNavigator  } from '@react-navigation/bottom-tabs'
import * as SplashScreen from 'expo-splash-screen'


import Kana from './components/Kana'
import Kanji from './components/Kanji'
import Words from './components/Words'


const Tab = createBottomTabNavigator()
SplashScreen.preventAutoHideAsync()


export default function App() {
  SplashScreen.preventAutoHideAsync()

  const [appIsReady, setAppIsReady] = useState(false)
  const [kana, setKana] = useState([])
  const [kanji, setKanji] = useState([])
  const [words, setWords] = useState([])

  useEffect(() => {
    async function getAll() {
      const resH = await fetch('https://api.npoint.io/1e1238bec6086a817d7c')
      setKana(await resH.json())

      const resK = await fetch('https://api.npoint.io/4da0650ce36be0bd5475')
      setKanji(await resK.json())

      const resW = await fetch('https://api.npoint.io/56e35c19f35f44e88704')
      setWords(await resW.json())

      setAppIsReady(true)
    }

    getAll()
  }, [])

  const onLayoutRootView = useCallback(async () => {
    if (appIsReady) {
      await SplashScreen.hideAsync()
    }
  }, [appIsReady])

  if (!appIsReady) {
    return null
  }

  return (
    <View style={styles.container} onLayout={onLayoutRootView}>
      <StatusBar style="auto" />
      <Tabs kanji={kanji} kana={kana} words={words} />
    </View>
  )
}

function Tabs({ kanji, kana, words }) {
  return (
    <NavigationContainer>
      <Tab.Navigator
        initialRouteName='Kanji'
        screenOptions={({ route }) => ({
          unmountOnBlur: true,
          tabBarShowLabel: true,
          headerShown: true,
          tabBarStyle: {
            backgroundColor: '#FFF',
            borderTopColor: 'black',
            alignContent: 'center',
            textAlignVertical: 'center',
          },
          tabBarItemStyle: {
            marginBottom: 15
          },
          tabBarLabelStyle: {
            fontSize: 15
          },
          tabBarIconStyle: { display: 'none' }
        })}>
        <Tab.Screen name="Hiragana" children={() => <Kana kana={kana.hiragana} romaji={kana.romaji} />} />
        <Tab.Screen name="Katakana" children={() => <Kana kana={kana.katakana} romaji={kana.romaji} />} />
        <Tab.Screen name="Kanji" children={() => <Kanji kanji={kanji} />} />
        <Tab.Screen name="Words" children={() => <Words words={words} />} />
      </Tab.Navigator>
    </NavigationContainer>
  )
}

const styles = StyleSheet.create({
  container: {
    height: '100%',
    width: '100%'
  }
})
