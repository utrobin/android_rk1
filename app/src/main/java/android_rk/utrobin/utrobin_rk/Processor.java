package android_rk.utrobin.utrobin_rk;

import android.content.Context;

import ru.mail.weather.lib.News;
import ru.mail.weather.lib.Storage;
import ru.mail.weather.lib.Topics;

/**
 * Created by utrobin on 07.03.17.
 */

public class Processor {
    public static void process(Context context) {
        String topic = Storage.getInstance(context).loadCurrentTopic();
        if (topic.isEmpty()) {
            topic = Topics.AUTO;
        }
        News news = NewsRest.process(topic);
        if (news != null) {
            Storage.getInstance(context).saveNews(news);
        }
    }
}
