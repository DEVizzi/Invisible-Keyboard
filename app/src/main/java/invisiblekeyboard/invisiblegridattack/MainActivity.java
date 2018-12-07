package invisiblekeyboard.invisiblegridattack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import invisiblegrid.invisiblegridattack.R;

/**
 * Created by reyammer on 8/18/16.
 */
public class MainActivity extends Activity {

    public TextView buffer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mStartGridAttack = (Button) findViewById(R.id.start_grid_attack);
        mStartGridAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch invisibleSwitch = (Switch) findViewById(R.id.invisible_switch);
                boolean invisible = invisibleSwitch.isChecked();
                Utils.startGridAttack(MainActivity.this, invisible);
            }
        });

        Button mResetBuffer = (Button) findViewById(R.id.reset_buffer);
        mResetBuffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridAttackService.keyStream = "";
            }
        });

        Button mRemoveGrid = (Button) findViewById(R.id.remove_grid);
        mRemoveGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.stopGridAttack(MainActivity.this);
            }
        });

    }
}
