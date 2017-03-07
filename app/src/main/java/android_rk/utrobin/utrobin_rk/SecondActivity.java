package android_rk.utrobin.utrobin_rk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ru.mail.weather.lib.Storage;
import ru.mail.weather.lib.Topics;

/**
 * Created by utrobin on 07.03.17.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Button it = (Button) findViewById(R.id.it);
        Button auto = (Button) findViewById(R.id.auto);
        Button health = (Button) findViewById(R.id.health);

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = Topics.IT;
                Storage.getInstance(getApplicationContext()).saveCurrentTopic(topic);
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = Topics.AUTO;
                Storage.getInstance(getApplicationContext()).saveCurrentTopic(topic);
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = Topics.HEALTH;
                Storage.getInstance(getApplicationContext()).saveCurrentTopic(topic);
            }
        });
    }
}
