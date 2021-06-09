package com.gomcorp.vrix.sample;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gomcorp.vrix.VrixAdCallback;
import com.gomcorp.vrix.VrixAdItem;
import com.gomcorp.vrix.VrixInitCallback;
import com.gomcorp.vrix.VrixPlayer;
import com.gomcorp.vrix.ExtensionIconAction;

/**
 * vrix version 2.x.x 용
 */
public class SampleActivityV2 extends AppCompatActivity implements View.OnClickListener {

    private VrixPlayer vrixPlayer;
    private ViewGroup pnlPlayer;
    private View progress;
    private View btnStart;

    private static final String VRIX_URL = "https://devads.vrixon.com/vast/vast.vrix?invenid=KHLOC";
//    private static final String VRIX_URL = "https://devads.vrixon.com/vast/vast.vrix?invenid=PEFOC";  //광고가 없는경우
//    private static final String VRIX_URL = "https://devads.vrixon.com/vast/vast.vrix?invenid=XXXXXX";  // 잘못된 URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        pnlPlayer = (ViewGroup) findViewById(R.id.pnl_player);
        progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
        btnStart = findViewById(R.id.btn_start);
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