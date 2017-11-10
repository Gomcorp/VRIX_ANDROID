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

## Sample APK Location
https://www.dropbox.com/s/0kkv7w1w2qf2920/AndroidVrix_20171110.apk?dl=0

## Usage example
```objc
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        player = (FrameLayout) findViewById(R.id.player);
        start = (Button) findViewById(R.id.start);
        
        .
        .
        .

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vrixManager = new VrixManager();

                if (vrixManager != null) {
                    vrixManager.prerollAtView(getApplicationContext(), VRIX_URL, player, new CompletionListener() {
                        @Override
                        public void onSuccess() {
                            //TODO Preroll 완료 후 동작

                        }

                        @Override
                        public void onFail() {
                            //TODO Preroll 실패 후 동작
                        }
                    });
                }
            }
        });
        .
        .
        .
        .
        
    }
    
    .
    .
    .
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vrixManager != null) {
            vrixManager.stopCurrentAD();
            vrixManager = null;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (vrixManager != null) {
            // app을 벗어났다가 돌아왔을경우
            vrixManager.resume();
        }
    }
    .
    .
    .

```

#### VRiX Handling methods
```objc

/*!
@method			prerollAtView
@param				context
@param				vrix_url
@param				targetView 광고가 재생될 뷰
@param				handler 광고재생 완료 후 호출될 콜백함수
@discussion		프리롤 광고를 해당뷰에 재생시킨다.
*/
- (void) prerollAtView(Context context,String url, ViewGroup view, CompletionListener listener)

/*!
@method			stopCurrentAD
@discussion		현재 재생중인 광고를 중지 시킨다.
*/
- (void) stopCurrentAD;

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
