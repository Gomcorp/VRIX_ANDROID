package com.gomcorp.vrix.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gomcorp.vrix.android.CompletionListener;
import com.gomcorp.vrix.android.VrixManager;

public class SampleActivity extends AppCompatActivity {

    private VrixManager vrixManager;
    private ViewGroup pnlPlayer;
    private View progress;
    private View btnStart;

    private static final String VRIX_URL = "http://ads.vrixon.com/vast/vast.vrix?invenid=KHLOC";
//    private static final String VRIX_URL = "http://ads.vrixon.com/vast/vast.vrix?invenid=PEFOC";  //광고가 없는경우
//    private static final String VRIX_URL = "http://ads.vrixon.com/vast/vast.vrix?invenid=XXXXXX";  // 잘못된 URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        pnlPlayer = (ViewGroup) findViewById(R.id.pnl_player);
        progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVrix();
            }
        });
    }

    private void startVrix() {
        progress.setVisibility(View.VISIBLE);
        if (vrixManager != null) {
            vrixManager.stop();
        }
        vrixManager = new VrixManager();
        vrixManager.init(this, VRIX_URL, new CompletionListener() {
            @Override
            public void onSuccess() {
                progress.setVisibility(View.GONE);
                play();
            }

            @Override
            public void onFail() {
                progress.setVisibility(View.GONE);
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