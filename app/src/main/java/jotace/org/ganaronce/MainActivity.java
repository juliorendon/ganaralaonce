package jotace.org.ganaronce;

import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private AudioManager mAudioManager;
    private SoundPool mSoundPool;
    private int mSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtaining the font
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/awesome.ttf");

        TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtTitle.setTypeface(font);

        final TextView digit1 = (TextView)findViewById(R.id.digit1);
        final TextView digit2 = (TextView)findViewById(R.id.digit2);
        final TextView digit3 = (TextView)findViewById(R.id.digit3);
        final TextView digit4 = (TextView)findViewById(R.id.digit4);
        final TextView digit5 = (TextView)findViewById(R.id.digit5);

        final Button btnWin = (Button)findViewById(R.id.btnWin);
        btnWin.setTypeface(font);
        btnWin.setEnabled(false);

        // creating the sound pool
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        // loading the sound
        mSoundId = mSoundPool.load(this, R.raw.cash, 1);

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0)
                    btnWin.setEnabled(true);
            }
        });

        btnWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = 0;

                // generating winning number
                number = RandomUtils.generateRandomDigit();
                setDigitText(digit1, number);

                number = RandomUtils.generateRandomDigit();
                setDigitText(digit2, number);

                number = RandomUtils.generateRandomDigit();
                setDigitText(digit3, number);

                number = RandomUtils.generateRandomDigit();
                setDigitText(digit4, number);

                number = RandomUtils.generateRandomDigit();
                setDigitText(digit5, number);


                // play the sound
                mSoundPool.play(mSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        if (null != mSoundPool) {
            mSoundPool.unload(mSoundId);
            mSoundPool.release();
            mSoundPool = null;
        }
        super.onPause();
    }

    private void setDigitText(TextView digit, int number) {

        switch (number) {
            case 0:
                digit.setText(R.string.zero);
                break;
            case 1:
                digit.setText(R.string.one);
                break;
            case 2:
                digit.setText(R.string.two);
                break;
            case 3:
                digit.setText(R.string.three);
                break;
            case 4:
                digit.setText(R.string.four);
                break;
            case 5:
                digit.setText(R.string.five);
                break;
            case 6:
                digit.setText(R.string.six);
                break;
            case 7:
                digit.setText(R.string.seven);
                break;
            case 8:
                digit.setText(R.string.eight);
                break;
            case 9:
                digit.setText(R.string.nine);
                break;
        }
    }

} // end
