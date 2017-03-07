package android_rk.utrobin.utrobin_rk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import ru.mail.weather.lib.News;
import ru.mail.weather.lib.Scheduler;
import ru.mail.weather.lib.Storage;

public class MainActivity extends AppCompatActivity implements ServiceHelper.NewsListener {

    private TextView text;
    private TextView title;
    private TextView date;

    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.title);
        text = (TextView) findViewById(R.id.text);
        date = (TextView) findViewById(R.id.date);

        storage = Storage.getInstance(this);

        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceHelper.getInstance(getApplicationContext()).getNews(getApplicationContext());
            }
        });

        Button background = (Button) findViewById(R.id.background);
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
                Scheduler.getInstance().schedule(getApplicationContext(), intent, 60000);
            }
        });

        Button noBackground = (Button) findViewById(R.id.no_background);
        noBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
                Scheduler.getInstance().unschedule(getApplicationContext(), intent);
            }
        });


        Button settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        ServiceHelper.getInstance(this).setCallback(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        News news = storage.getLastSavedNews();
        if (news != null) {
            title.setText(news.getTitle());
            text.setText(news.getBody());
            Date dateObj = new Date(news.getDate());
            date.setText(dateObj.toString());
        }
        ServiceHelper.getInstance(this).getNews(this);
    }

    @Override
    public void NewsLoaded() {
        News news = storage.getLastSavedNews();
        if (news != null) {
            title.setText(news.getTitle());
            text.setText(news.getBody());
            Date dateObj = new Date(news.getDate());
            date.setText(dateObj.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ServiceHelper.getInstance(this).setCallback(null);
    }
}

