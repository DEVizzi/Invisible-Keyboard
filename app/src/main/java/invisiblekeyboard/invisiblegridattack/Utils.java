package invisiblekeyboard.invisiblegridattack;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by reyammer on 8/18/16.
 */
public class Utils {
    public static void log(String s) {
        Log.e("GRID-ATTACK", s);
    }

    public static void startGridAttack(Context ctx, boolean invisible) {
        Intent intent = new Intent(ctx, GridAttackService.class);
        intent.putExtra("invisible", invisible);
        intent.putExtra("grid-type", "keyboard");
        ctx.startService(intent);
    }

    public static void stopGridAttack(Context ctx) {
        Intent intent = new Intent(ctx, GridAttackService.class);
        ctx.stopService(intent);
    }
}
