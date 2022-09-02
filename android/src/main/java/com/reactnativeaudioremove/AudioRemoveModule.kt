package com.reactnativeaudioremove

import com.facebook.react.bridge.*
import com.simform.videooperations.*
import com.facebook.react.bridge.ReactApplicationContext


class AudioRemoveModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {
  var reactContext = reactContext;
  var ffmpegQueryExtension = FFmpegQueryExtension()


  override fun getName(): String {
    return "AudioRemove"
  }


  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  fun removeAudio(path: String, promise: Promise) {

    removeAudioProcess(path, promise)


  }

  @ReactMethod
  fun mergeVideoAndAudio(videoPath: String, audioPath: String, promise: Promise) {
    mergeProcess(videoPath, audioPath, promise)

  }


  @ReactMethod
  fun trimVideo(videoPath: String, startTime: String, endingTime: String, promise: Promise) {
    trimVideoProcess(videoPath, startTime, endingTime, promise)

  }

  @ReactMethod
  fun fastAndSlow(videoPath: String, setpts: Double, atempo: Double, promise: Promise) {
    speedAndSlowProcess(videoPath, setpts, atempo, promise)

  }

//  @ReactMethod
//  fun combineVideo(videoPath: ReadableArray, width: Int?, height: Int?, promise: Promise) {
//    val pathList = new  ArrayList<Paths>()
//    val path = arrayOf(
//      "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
//      "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
//      "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
//    )
//
//    val metadata = Arguments.createMap()
//   for(i in 0 until  videoPath.size() ){
//     val singleValue = videoPath.getString(i)
//       metadata.putString(singleValue,"")
//     }
//
//   }

  @ReactMethod
  fun cropAudio(audioPath: String, startTime: String, endingTime: String, promise: Promise) {
    cropAudioProcess(audioPath, startTime, endingTime, promise)

  }


  private fun removeAudioProcess(path: String, promise: Promise) {


    val outputPath = Common.getFilePath(reactContext, Common.VIDEO)
    val query = ffmpegQueryExtension.removeAudioFromVideo(path, outputPath)
    CallBackOfQuery().callQuery(query, object : FFmpegCallBack {
      override fun process(logMessage: LogMessage) {
//        promise.resolve(logMessage.text)
      }

      override fun success() {
        promise.resolve(outputPath)
      }

      override fun cancel() {
        promise.reject("Canceled", "Canceled audio remove Process")

      }

      override fun failed() {
        promise.reject(Error("failed"))
      }
    })
  }

  private fun mergeProcess(videoPath: String, audioPath: String, promise: Promise) {
    val outPutPath = Common.getFilePath(reactContext, Common.VIDEO)
    val query = ffmpegQueryExtension.mergeAudioVideo(videoPath, audioPath, outPutPath)
    CallBackOfQuery().callQuery(query, object : FFmpegCallBack {
      override fun process(logMessage: LogMessage) {
        //    promise.resolve(logMessage.text)
      }

      override fun success() {
        promise.resolve(outPutPath)
      }

      override fun cancel() {
        promise.reject("Canceled", "Canceled audio and video merge Process")
      }

      override fun failed() {
        promise.reject("failed", "failed audio and video merge Process")
      }
    })

  }

  private fun trimVideoProcess(
    videoPath: String,
    startTime: String,
    endingTime: String,
    promise: Promise
  ) {
    val outPutPath = Common.getFilePath(reactContext, Common.VIDEO)
    val query = ffmpegQueryExtension.cutVideo(videoPath, startTime, endingTime, outPutPath)
    CallBackOfQuery().callQuery(query, object : FFmpegCallBack {
      override fun process(logMessage: LogMessage) {

      }

      override fun success() {
        promise.resolve(outPutPath)
      }

      override fun cancel() {
        promise.reject("Canceled", "Canceled video trim Process")
      }

      override fun failed() {
        promise.reject("failed", "failed video trim Process")
      }

    })
  }

  private fun speedAndSlowProcess(
    videoPath: String,
    setpts: Double,
    atempo: Double,
    promise: Promise
  ) {
    val outPutPath = Common.getFilePath(reactContext, Common.VIDEO)
    val query = ffmpegQueryExtension.videoMotion(videoPath, outPutPath, setpts, atempo)
    CallBackOfQuery().callQuery(query, object : FFmpegCallBack {
      override fun process(logMessage: LogMessage) {

      }

      override fun success() {
        promise.resolve(outPutPath)
      }

      override fun cancel() {
        promise.reject("Canceled", "Canceled video slow or fast motion Process")
      }

      override fun failed() {
        promise.reject("failed", "failed video slow or fast motion Process")
      }

    })

  }


  // private fun combineVideoProcess(
  //   videoPath: ArrayList<Paths>,
  //   width: Int?,
  //   height: Int?,
  //   promise: Promise
  // ) {
  //   promise.resolve(videoPath.toString())
  //   val outPutPath = Common.getFilePath(reactContext, Common.VIDEO)
  //   val query = ffmpegQueryExtension.combineVideos(videoPath, width, height, outPutPath)
  //   CallBackOfQuery().callQuery(query, object : FFmpegCallBack {
  //     override fun process(logMessage: LogMessage) {

  //     }

  //     override fun success() {
  //       promise.resolve(outPutPath)
  //     }

  //     override fun cancel() {
  //       promise.reject("Canceled", "Canceled video merge Process")
  //     }

  //     override fun failed() {
  //       promise.reject("failed", "failed video merge Process")
  //     }

  //   })

  // }

  private fun cropAudioProcess(
    audioPath: String,
    startTime: String,
    endingTime: String,
    promise: Promise
  ) {
    val outPutPath = Common.getFilePath(reactContext, Common.MP3)
    val query = ffmpegQueryExtension.cutAudio(audioPath, startTime, endingTime, outPutPath)
    CallBackOfQuery().callQuery(query, object : FFmpegCallBack {
      override fun process(logMessage: LogMessage) {

      }

      override fun success() {
        promise.resolve(outPutPath)
      }

      override fun cancel() {
        promise.reject("Canceled", "Canceled Audio Crop Process")
      }

      override fun failed() {
        promise.reject("failed", "failed Audio Crop Process")
      }

    })
  }



  }









