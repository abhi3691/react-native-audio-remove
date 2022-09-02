import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-audio-remove' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const AudioRemove = NativeModules.AudioRemove
  ? NativeModules.AudioRemove
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );
interface removeAudioprops {
  path: String;
}

// videoPath: String;
//   audioPath: String;
//   startTime: String;
//   endingTime: String;
//   setPts: Number;
//   tempo: Number;

export const removeAudio = ({ path }: removeAudioprops): Promise<String> => {
  return AudioRemove.removeAudio(path);
};

interface mergeVideoAndAudioprops {
  videoPath: String;
  audioPath: String;
}

export const mergeVideoAndAudio = ({
  videoPath,
  audioPath,
}: mergeVideoAndAudioprops): Promise<String> => {
  return AudioRemove.mergeVideoAndAudio(videoPath, audioPath);
};

interface trimVideoProps {
  videoPath: String;
  startTime: String;
  endingTime: String;
}

export const trimVideo = ({
  videoPath,
  startTime,
  endingTime,
}: trimVideoProps): Promise<String> => {
  return AudioRemove.trimVideo(videoPath, startTime, endingTime);
};

interface fastAndSlowProps {
  videoPath: String;
  setPts: Number;
  tempo: Number;
}

export const fastAndSlow = ({
  videoPath,
  setPts,
  tempo,
}: fastAndSlowProps): Promise<String> => {
  return AudioRemove.fastAndSlow(videoPath, setPts, tempo);
};

// export const combineVideo = (
//   videoPath: Array<String>,
//   width: Number,
//   height: Number
// ) => {
//   return AudioRemove.combineVideo(videoPath, width, height);
// };

interface cropAudioProps {
  audioPath: String;
  startTime: String;
  endingTime: String;
}

export const cropAudio = ({
  audioPath,
  startTime,
  endingTime,
}: cropAudioProps): Promise<String> => {
  return AudioRemove.cropAudio(audioPath, startTime, endingTime);
};
