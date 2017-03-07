package android_rk.utrobin.utrobin_rk;

import ru.mail.weather.lib.News;
import ru.mail.weather.lib.NewsLoader;

/**
 * Created by utrobin on 07.03.17.
 */

public class NewsRest {
    public static News process(String topic) {
        NewsLoader newsLoader = new NewsLoader();
        News news;
        try {
            news = newsLoader.loadNews(topic);
        }
        catch (Exception e) {
            news = null;
        }
        return news;
    }
}
