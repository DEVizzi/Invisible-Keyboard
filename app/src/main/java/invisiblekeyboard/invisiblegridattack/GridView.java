package invisiblekeyboard.invisiblegridattack;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;


public class GridView extends TextView {
    final private int id;
    final private Rect rect;
    final private String symbol;
    final private Paint mPaint;
    private final WindowManager.LayoutParams lParams;
    private final GridAttackService service;

    public int getId() {
        return id;
    }

    public GridView(final GridAttackService service, final Integer id, final String symbol, final Rect rect, boolean invisible) {
        super(service);
        this.service = service;
        this.id = id;
        this.rect = rect;
        this.symbol = symbol;
        mPaint = new Paint();

        mPaint.setStyle(Paint.Style.FILL);
        if (invisible) {
            setBackgroundColor(0x000000FF);
            mPaint.setColor(0x00000000);
        } else {
            setBackgroundColor(0x66FF0000);
            setText("#" + Integer.toString(id) + ":" + symbol);
            setTextSize(12);
            setTextColor(0xff000000);
            mPaint.setColor(0x66FF0000);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(10);
        }

        setGravity(Gravity.TOP | Gravity.LEFT);

        WindowManager.LayoutParams lParams = new WindowManager.LayoutParams(
                rect.width(),
                rect.height(),
                rect.left,
                rect.top,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        lParams.gravity = Gravity.LEFT | Gravity.TOP;
        this.lParams = lParams;

        this.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utils.log("onTouch - " + v.toString() + " - " + event.toString());
                GridView.this.service.recordEvent(event);
                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(mPaint);
    }

    public ViewGroup.LayoutParams getParams() {
        return lParams;
    }

    public String toString() {
        String s = "";
        s += "{Id:"+id+" sym:"+symbol+" pos:"+rect.toString()+"}";
        return s;
    }
}
