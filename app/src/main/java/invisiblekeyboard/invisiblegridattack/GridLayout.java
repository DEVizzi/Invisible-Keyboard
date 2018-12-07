package invisiblekeyboard.invisiblegridattack;

import android.content.Context;
import android.graphics.Rect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by reyammer on 7/26/16.
 */
public class GridLayout {
    private final Context mContext;
    private TreeMap<Integer, String> mId2symbol;
    private TreeMap<Integer, Rect> mId2rect;

    public GridLayout(Context ctx, String gridType) {
        mContext = ctx;

        mId2symbol = new TreeMap<>();
        mId2rect = new TreeMap<>();

        try {
            parseGridLayout(gridType);
        } catch (Exception e) {
            Utils.log("EXCEPTION WHILE PARSING GRID LAYOUT: " + e.toString());
            mId2symbol = null;
            mId2rect = null;
        }
    }

    public Set<Integer> getIds() {
        return mId2symbol.keySet();
    }

    public String getSymbol(Integer id) {
        return mId2symbol.get(id);
    }

    public Rect getRect(Integer id) {
        return mId2rect.get(id);
    }

    public Integer getViewsNum() {
        return mId2symbol.size();
    }

    private void parseGridLayout(String gridType) throws Exception {
        String fileName = "grid_" + gridType + ".csv";

        BufferedReader reader = new BufferedReader(new InputStreamReader(mContext.getAssets().open(fileName)));

        // do reading, usually loop until end of file reading
        String mLine;
        boolean first = true;
        int currId = 0;
        while ((mLine = reader.readLine()) != null) {
            //process line
            if (first) {
                // skip first line
                first = false;
                continue;
            }

            String[] parts = mLine.split("\\|");
            if (parts.length != 5) {
                throw new Exception("line not OK: " + mLine);
            }

            String symbol = parts[0];
            Integer left = Integer.parseInt(parts[1]);
            Integer top = Integer.parseInt(parts[2]);
            Integer right = Integer.parseInt(parts[3]);
            Integer bottom = Integer.parseInt(parts[4]);

            Rect rect = new Rect(left, top, right, bottom);
            mId2rect.put(currId, rect);
            mId2symbol.put(currId, symbol);

            currId++;
        }
    }
}
