package sg.edu.np.s10178658.medicx;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PickTimeActivity extends AppCompatActivity {

    DbHandler db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_time);

        Intent intent = getIntent();
        String time = intent.getStringExtra(SelectDateActivity.TIME_TEXT);
        String date = intent.getStringExtra(SelectDateActivity.DATE_TEXT);

        ListView listView = findViewById(R.id.lvTime);
        db = new DbHandler(this, null, null, 1);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getListContents();

        if(data.getCount() == 0){
            Toast.makeText(PickTimeActivity.this, "There is no appointment.", Toast.LENGTH_LONG).show();
        }
        else{
            while (data.moveToNext()){
                theList.add(data.getString(1));
            }
        }
        ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout.activity_list_adapter, theList);
        listView.setAdapter(listAdapter);
    }
}
