package android_rk.utrobin.utrobin_rk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by utrobin on 07.03.17.
 */

public class ServiceHelper {
    private static ServiceHelper serviceHelper;
    private NewsListener newsListener;

    private ServiceHelper() {
    }

    public static ServiceHelper getInstance(Context context) {
        if (serviceHelper == null) {
            serviceHelper = new ServiceHelper();
            serviceHelper.initBroadcastReceiver(context);
        }
        return serviceHelper;
    }

    public interface NewsListener {
        void NewsLoaded();
    }

    public void setCallback(NewsListener newsListener) {
        this.newsListener = newsListener;
    }


    public void getNews(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        context.startService(intent);
    }

    private void initBroadcastReceiver(Context context) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(MyIntentService.ACTION_RESULT);

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                if (newsListener != null)
                    newsListener.NewsLoaded();
            }
        }, filter);
    }
}
