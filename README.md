# VRIX_ANDROID

## Features
- VMAP, VAST supported.
- Preroll 광고 지원

## Requirements

- Android API 17+
- Installed "Google Play Service"

## Installation
1. Add repository in your build.gradle on project-level
```groovy
    allprojects {
        repositories {
            maven {
                url  "https://dl.bintray.com/gomcorp/maven/"
            }
        }
    }
```

2. Add dependency in your build.gradle on module-level
```groovy
    dependencies {
        implementation ('com.gomcorp.vrix.android:vrix:1.0.3.1@aar') {
            transitive = true
        }
    }
```

3. Add permission in your AndroidManifest.xml
```
<uses-permission android:name="android.permission.INTERNET" />
```
## Release Note
[Release Note](https://github.com/Gomcorp/VRIX_ANDROID/wiki/Release-Note)

## Usage example
```java
public class SampleActivity extends AppCompatActivity {

    private ViewGroup pnlPlayer;
    private VrixManager vrixManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ....
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVrix();
            }
        });
    }

    private void startVrix() {
        vrixManager = new VrixManager();
        vrixManager.init(this, VRIX_URL, new CompletionListener() {
            @Override
            public void onSuccess() {
                play();
            }

            @Override
            public void onFail() {
                Toast.makeText(SampleActivity.this, "Init 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void play() {
        vrixManager.play(pnlPlayer, new CompletionListener() {
            @Override
            public void onSuccess() {
                // 광고재생완료
                // 메인영상 재생 시작
                Toast.makeText(SampleActivity.this, "재생 완료", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail() {
                // 광고재생실패
                // 메인영상 재생 시작
                Toast.makeText(SampleActivity.this, "재생 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (vrixManager != null) {
            vrixManager.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (vrixManager != null) {
            vrixManager.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (vrixManager != null) {
            vrixManager.stop();
        }
    }
}
```

#### VRiX Handling methods
```java
class com.gomcorp.vrix.android.VrixManager {
    /**
     * Initialize
     * @param context   Context
     * @param url       Vrix URL
     * @param listener  Callback
     */
    public void init(Context context, String url, CompletionListener listener)

    /**
     * Play Ads
     * @param view      광고가 붙을 부모 ViewGroup
     * @param listener  Callback
     */
    public void play(ViewGroup view, CompletionListener listener)

    /**
     * Activity or Flagment onResume 에서 호출
     */
    public void resume()

    /**
     * Activity or Flagment onPause 에서 호출
     */
    public void pause()

    /**
     * Activity or Flagment onStop 에서 호출
     */
    public void stop()
}
```

## License

![License][license-image]

Gomcorp – (https://www.gomcorp.com/) – cilee@gomcorp.com

Copyright © 2017 Gomcorp.


[license-image]: https://img.shields.io/badge/License-MIT-blue.svg

