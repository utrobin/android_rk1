package android_rk.utrobin.utrobin_rk;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by utrobin on 07.03.17.
 */

public class MyIntentService extends IntentService {

    public static final String ACTION_RESULT = "action_result";

    public MyIntentService() {
        super("service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Processor.process(this);
        intent = new Intent(ACTION_RESULT);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
