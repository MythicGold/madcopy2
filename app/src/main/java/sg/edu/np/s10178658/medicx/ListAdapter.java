package sg.edu.np.s10178658.medicx;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<DayOfAppointment> {
    Context c;
    int layout;
    ArrayList<DayOfAppointment> theList;

    public ListAdapter(Context c, int layout, ArrayList<DayOfAppointment> theList){
        super(c, layout, theList);

        this.c = c;
        this.layout = layout;
        this.theList = theList;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(c).inflate(layout, parent, false);

            Log.d("L04", "New view " + position);
        }
        Log.d("L04", "Existing view " + position);
        TextView date = v.findViewById(R.id.txtDate);
        TextView time = v.findViewById(R.id.txtTime);
        TextView username = v.findViewById(R.id.txtUsername);

        DayOfAppointment da = theList.get(position);
        date.setText(da.getDate());
        time.setText(da.getTime());
        username.setText(da.getUsername());

        return v;
    }

}
