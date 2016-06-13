package jp.study.todo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<CharSequence> listToDo;
    private ArrayAdapter<CharSequence> arrayToDo;
    private TextView text = null;
    private static final int SUB_ACTIVITY = 1001;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnAdd);





        listToDo = new ArrayList<CharSequence>();
        arrayToDo = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, listToDo);
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(arrayToDo);


        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);

                startActivityForResult(intent, SUB_ACTIVITY);

            }
        });
        registerForContextMenu(list);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        arrayToDo.add(data.getCharSequenceExtra("text"));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() != R.id.listView) {
            return;
        }
        menu.setHeaderTitle("DELETE");
        String[] options = {"yes", "Cancel"};
        for (String option : options) {
            menu.add(option);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int sitem = info.position;
        if (item.getTitle().equals("yes")) {
            listToDo.remove(sitem);
            arrayToDo.notifyDataSetChanged();
        }
        return true;
    }
}


