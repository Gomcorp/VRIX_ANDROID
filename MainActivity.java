package vrix.gomandcorp.com.myapplicationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.gomcorp.vrixlib.VrixManager;
import com.gomcorp.vrixlib.player.listener.CompletionListener;
import com.gomcorp.vrixlib.player.listener.ReturnCompletionListener;

public class MainActivity extends AppCompatActivity {

    private VrixManager vrixManager;
    private FrameLayout player;
    private Button start;
    private Button stop;

    private String VRIX_URL = "http://183.110.11.246/vast_ads.vrix?vcode=vmap|33|C1|100|1000|7447|";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vrixManager = new VrixManager();
        vrixManager.init(this);

        vrixManager.fetchVRiX(VRIX_URL, new ReturnCompletionListener() {
            @Override
            public void onSuccess() {
                Log.e("VRIX", "vmap parser success");
            }

            @Override
            public void onFail(String error) {
                Log.e("VRIX", "vmap parser fail : " + error);
            }
        });

        player = (FrameLayout) findViewById(R.id.player);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vrixManager.prerollAtView(player, new CompletionListener() {
                    @Override
                    public void onSuccess() {
                        Log.e("VRIX", "preroll success");
                    }

                    @Override
                    public void onFail() {
                        Log.e("VRIX", "preroll fail");
                    }
                });
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vrixManager.stopCurrentAD();
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
}
