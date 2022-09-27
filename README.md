# react-native-audio-remove

(Android Only)

This library is a React Native bridge around native Video Editing libraries. It allows you to edit any video by providing below set of features:


* _**Video Trimming**_
* _**Merge Video And Audio**_
* _**Audio Trimming**_
* _**Remove Audio From Video**_
* _**FastMotion Video**_
* _**SlowMotion Video**_


## 📖 Getting started

`$ yarn add  react-native-audio-remove`


- **Android**

- Please add below script in your build.gradle

```java

android {
//2 files found for path lib/arm64-v8a/libc++\_shared.so. This version of the Android Gradle Plugin chooses the f
packagingOptions {
pickFirst 'lib/x86/libc++\_shared.so'
pickFirst 'lib/x86_64/libc++\_shared.so'
pickFirst 'lib/armeabi-v7a/libc++\_shared.so'
pickFirst 'lib/arm64-v8a/libc++\_shared.so'
}
}
```

## 💻 Usage

```js
import * as React from 'react';

import { StyleSheet, View, Text, Button } from 'react-native';
import {
  mergeVideoAndAudio,
  removeAudio,
  fastAndSlow,
  trimVideo,
  cropAudio,
} from 'react-native-audio-remove';
import Video from 'react-native-video';
export default function App() {
  const [result, setResult] = React.useState<String | undefined>();
  const [result2, setResult2] = React.useState<String | undefined>();
  const [result3, setResult3] = React.useState<String | undefined>();
  const [result4, setResult4] = React.useState<String | undefined>();
  const [result5, setResult5] = React.useState<String | undefined>();

  const removeAudioFromVideo = () => {
    removeAudio({
      path: 'http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4',
    })
      .then((res) => {
        console.log(res);

        setResult(res);
      })
      .catch((err) => console.log(err));
  };

  const mergeVideo = () => {
    mergeVideoAndAudio({
      videoPath:
        'http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4',
      audioPath: 'https://samplelib.com/lib/preview/mp3/sample-15s.mp3',
    })
      .then((res) => {
        setResult2(res);
      })
      .catch((err) => console.log(err));
  };

  const fastAndSlowVideo = () => {
    fastAndSlow({
      videoPath:
        'http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4',
      setPts: 0.5,
      tempo: 4.0,
    })
      .then((res) => {
        setResult3(res);
      })
      .catch((err) => console.log(err));
  };

  const videoTrim = () => {
    trimVideo({
      videoPath:
        'http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4',
      startTime: "00:1:00'",
      endingTime: '00:02:10',
    })
      .then((res) => {
        setResult4(res);
      })
      .catch((err) => console.log(err));
  };

  const AudioTrim = () => {
    cropAudio({
      audioPath: 'https://samplelib.com/lib/preview/mp3/sample-15s.mp3',
      startTime: "00:1:00'",
      endingTime: '00:05:10',
    })
      .then((res) => {
        setResult5(res);
      })
      .catch((err) => console.log(err));
  };

  return (
    <View style={styles.container}>
      <Button title="Remove Audio" onPress={() => removeAudioFromVideo()} />
      <Text>Result: {result}</Text>
      {typeof result == 'string' && (
        <Video source={{ uri: result }} style={{ height: 200, width: 200 }} />
      )}
      <Button title="Merge Audio And Video" onPress={() => mergeVideo()} />
      <Text>Result: {result2}</Text>
      {typeof result2 == 'string' && (
        <Video source={{ uri: result2 }} style={{ height: 250, width: 250 }} />
      )}
      <Button title="fastAnd Slow Video" onPress={() => fastAndSlowVideo()} />
      <Text>Result: {result3}</Text>
      {typeof result3 == 'string' && (
        <Video source={{ uri: result3 }} style={{ height: 250, width: 250 }} />
      )}
      <Button title="Trim Video" onPress={() => videoTrim()} />
      <Text>Result: {result4}</Text>
      {typeof result4 == 'string' && (
        <Video source={{ uri: result4 }} style={{ height: 250, width: 250 }} />
      )}

      <Button title="Crop Audio" onPress={() => AudioTrim()} />
      <Text>Result: {result5}</Text>
      {typeof result5 == 'string' && (
        <Video source={{ uri: result5 }} style={{ height: 250, width: 250 }} />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});


```

## ✨ Credits

Thank you for Contribution [Akshy K](https://instagram.com/_akshay119?igshid=YmMyMTA2M2Y=).


## 📜 License

This library is provided under the Apache 2 License.\

## 💖 Support my projects
I open-source almost everything I can, and I try to reply everyone needing help using these projects. Obviously, this takes time. You can integrate and use these projects in your applications for free! You can even change the source code and redistribute (even resell it).

However, if you get some profit from this or just want to encourage me to continue creating stuff, there are few ways you can do it:
* Starring and sharing the projects you like 🚀
* If you're feeling especially charitable, please follow [abhi3691](https://github.com/abhi3691) on GitHub.

<a href=" https://github.com/sponsors/abhi3691">
  <img align="right" width="500" alt="This library helped you? Consider sponsoring!" src=".github/sponsership.png">
</a>

  Thanks! ❤️
  <br/>
  [abhi3691.github.io]https://github.com/abhi3691)
  <br/>
  </ Abhinand Vk >
