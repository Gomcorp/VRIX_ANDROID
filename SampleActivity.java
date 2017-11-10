package com.gomandcorp.vrix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.gomcorp.vrixlib.VrixManager;
import com.gomcorp.vrixlib.player.listener.CompletionListener;

/**
 * Created by GRE543 on 2017-11-09.
 */

public class SampleActivity  extends AppCompatActivity {

    private VrixManager vrixManager;
    private FrameLayout player;
    private Button start;

    private String VRIX_URL = "http://devads.vrixon.com/vast/vmap.vrix?ctime=3806&invenid=C1&cate1=110&cate2=1000&cate3=2001@2005@2012@2990&broadcast=512&contentid=14366036&seriesid=14207064&gender=0&age=0&param=\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        player = (FrameLayout) findViewById(R.id.player);
        start = (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vrixManager = new VrixManager();

                if (vrixManager != null) {
                    vrixManager.init(getApplicationContext(), VRIX_URL, player, new CompletionListener() {
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
    }

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
}