# VRIX_ANDROID

[![License][license-image]][license-url]
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

VMAP, VAST VRiX

## Features

- [x] VMAP, VAST supported.
- [x] Preroll 광고 지원

## Requirements

- Android API 17+
- Installed "Google Play Service"

## Installation

#### Manually
1. Download and drop ```androidvrixlib.jar``` in your project libs folder.  
2. add dependencies in your app.gradle

dependencies{

...

    compile 'com.google.android.gms:play-services-ads:10.2.0'
    implementation files('libs/androidvrixlib.jar')
    compile 'com.github.bumptech.glide:glide:3.7.0'
...

}

3. add permission in your AndroidManifest.xml
```objc
<uses-permission android:name="android.permission.INTERNET" />
```

## Usage example

#### init
```objc
private init(Context context){
this.context = context;
setOsInfo();                    //  infomations of OS
checkGooglePlayServices();      //  extraction of ADID
}

```
#### Fetch
1. FetchVrix

```objc
public static void fetchVRiX(String url, ReturnCompletionListener listener) {

        if (context == null)  {
            return;
        }

        if (player != null) {
            player = null;
        }

        player = new VastPlayerHandler(context);
        player.requestVrix(url, listener);
    }
```
2. Preroll
```objc
public static void prerollAtView(ViewGroup view, CompletionListener listener) {
        if (player != null) {
            player.prerollAtView(view, listener);
        }
    }
```

#### VRiX Handling methods
```objc

@method			fetchVRiX:completionHandler:
@param				url VRiX주소
@param				handler fetch 완료 호출될 block
@discussion		브릭스서비스에서 광고 정보를 fetch한다..
*/
- (void) fetchVRiX(String url, ReturnCompletionListener listener)

/*!
@method			stopCurrentAD
@discussion		현재 재생중인 광고를 중지 시킨다.
*/
- (void) stopCurrentAD;

/*!
@method			prerollAtView:completionHandler
@param				targetView 광고가 재생될 뷰
@param				handler 광고재생 완료 후 호출될 block
@discussion		프리롤 광고를 해당뷰에 재생시킨다.
*/
- (void) prerollAtView(ViewGroup view, CompletionListener listener)

/*!
@method			prerollCount
@discussion		프리롤광고의 곗수를 리턴한다.
*/
- (int) prerollCount;

```
## License

Gomcorp – (https://www.gomcorp.com/) – pjm3019@gomcorp.com

Copyright © 2017 Gomcorp.

[license-image]: https://img.shields.io/badge/License-MIT-blue.svg
[license-url]: LICENSE
