package jp.study.todo1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.ExtractedText;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import jp.study.todo1.R;

public class SubActivity extends AppCompatActivity {
    private ArrayAdapter<CharSequence> arrayToDo;
    private EditText edit = null;
    private static final int REQUEST_CODE = 0;
    private TextView text;
    private String resultsString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //final TextView text = (TextView) findViewById(R.id.editText);
        edit = (EditText) findViewById(R.id.editText);
        Button btn = (Button) findViewById(R.id.btnAdd);
        assert btn != null;

        //test
        //text.setText(resultsString);
        //testEnd


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("text", edit.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }

        });


        Button button = (Button) findViewById(R.id.btnVoice);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Voice");
                    startActivityForResult(intent, REQUEST_CODE);

                } catch (ActivityNotFoundException e) {
//                    Toast.makeText(SubActivity.this,
//                            "ActivityNotFoundException", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String resultsString = "";
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            edit.setText(results.get(0));
            //Toast.makeText(this, resultsString, Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}


