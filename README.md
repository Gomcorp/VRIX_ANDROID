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
        implementation ('com.gomcorp.vrix.android:vrix:2.0.0@aar') {
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


## Usage example V1.X.X
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

## License

![License][license-image]

Gomcorp – (https://www.gomcorp.com/) – cilee@gomcorp.com

Copyright © 2017 Gomcorp.


[license-image]: https://img.shields.io/badge/License-MIT-blue.svg

