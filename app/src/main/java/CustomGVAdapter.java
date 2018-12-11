import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gding3.maindelivery.BrowseRecipesActivity;
import com.example.gding3.maindelivery.R;

public class CustomGVAdapter extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomGVAdapter(BrowseRecipesActivity mainActivity, String[] NameList, int[] Images) {
        // TODO Auto-generated constructor stub
        result=NameList;
        //context=;
        imageId=Images;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView textView;
        ImageView imageView;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.grid_layout_browse, null);
        holder.textView =(TextView) rowView.findViewById(R.id.text);
        holder.imageView =(ImageView) rowView.findViewById(R.id.images);

        holder.textView.setText(result[position]);
        holder.imageView.setImageResource(imageId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }

}



