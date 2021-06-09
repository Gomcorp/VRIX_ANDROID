# VRIX_ANDROID

## Features
- VMAP, VAST supported.
- Preroll 광고 지원

## Requirements

- Android API 17+
- Installed "Google Play Service"

## Installation
1. aar 파일을 libs 폴더에 추가   
[download](https://github.com/Gomcorp/VRIX_ANDROID/blob/master/app/libs)

![libs](https://user-images.githubusercontent.com/31840071/121276972-9f09af00-c90a-11eb-9a23-d1d38350b62e.png)


2. vrix에서 사용하는 라이브러리를 dependency 에 추가해야 합니다.   
app 레밸의 build.gradle에 추가해 줍니다.

![dependencies](https://user-images.githubusercontent.com/31840071/121276969-9dd88200-c90a-11eb-9a61-ebf310415c51.png)

```groovy
    dependencies {
        implementation fileTree(dir: "libs", include: ["*.jar", "*.aar"])

        implementation 'com.squareup.retrofit2:retrofit:2.6.2'
        implementation ('com.squareup.retrofit2:converter-simplexml:2.6.2'){
            exclude group: 'xpp3', module: 'xpp3'
            exclude group: 'stax', module: 'stax-api'
            exclude group: 'stax', module: 'stax'
        }
        implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
        implementation 'com.google.android.gms:play-services-ads-identifier:17.0.1'

        implementation 'com.squareup.picasso:picasso:2.71828'
        implementation 'com.jakewharton.picasso:picasso2-okhttp3-downloader:1.1.0'
    }
```

3. AndroidManifest.xml 에 퍼미션을 추가합니다.
```
<uses-permission android:name="android.permission.INTERNET" />
```

4. proguard 설정
[link](https://github.com/Gomcorp/VRIX_ANDROID/blob/master/app/proguard-rules.pro)


## Release Note
[Release Note](https://github.com/Gomcorp/VRIX_ANDROID/wiki/Release-Note)

## Usage example V2
2.0.0 부터 사용법이 변경 됨

SampleActivityV2.java 파일 참고
```java
public class SampleActivityV2 extends AppCompatActivity implements View.OnClickListener {

    private VrixPlayer vrixPlayer;
    private ViewGroup pnlPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        .....
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnStart) {
            startVrix();
        }
    }

    private void startVrix() {
        progress.setVisibility(View.VISIBLE);
        if (vrixPlayer != null) {
            vrixPlayer.release();
            vrixPlayer = null;
        }
        vrixPlayer = new VrixPlayer(this, pnlPlayer, VRIX_URL);
        vrixPlayer.init(new VrixInitCallback() {
            @Override
            public void onInitialized() {
                progress.setVisibility(View.GONE);
                play();
            }

            @Override
            public void onFailed() {
                progress.setVisibility(View.GONE);
                Toast.makeText(SampleActivityV2.this, "Init 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void play() {
        vrixPlayer.playPreroll(new VrixAdCallback() {
            @Override
            public void onError() {
                // 광고재생실패
                // 메인영상 재생 시작
                Toast.makeText(SampleActivityV2.this, "재생 실패", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdBreakStarted() {
            }

            @Override
            public void onAdStarted(VrixAdItem vrixAdItem) {
                Toast.makeText(SampleActivityV2.this, vrixAdItem.title + " 재생시작", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdCompleted(VrixAdItem vrixAdItem) {
                Toast.makeText(SampleActivityV2.this, vrixAdItem.title + " 재생완료", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdBreakCompleted() {
                // 광고재생완료
                // 메인영상 재생 시작
                Toast.makeText(SampleActivityV2.this, "광고 재생 완료", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked(VrixAdItem vrixAdItem) {
            }

            @Override
            public void onAdSkipped(VrixAdItem vrixAdItem) {
            }

            @Override
            public void onExtensionIconClick(ExtensionIconAction d) {
                // Noting To do, 무시해주세요.
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (vrixPlayer != null) {
            vrixPlayer.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (vrixPlayer != null) {
            vrixPlayer.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (vrixPlayer != null) {
            vrixPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vrixPlayer != null) {
            vrixPlayer.release();
            vrixPlayer = null;
        }
    }
}
```


## License

![License][license-image]

Gomcorp – (https://www.gomcorp.com/) – nsd8352@gomcorp.com

Copyright © 2017 Gomcorp.


[license-image]: https://img.shields.io/badge/License-MIT-blue.svg

