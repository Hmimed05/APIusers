package fstt.hamza.exerciceAPI;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private static final String TAG = "UsersListAdapter";
    private Context mContext;
    private ArrayList<User> users;
    private int mResource;

    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
        this.users = objects;
    }

    @Override
    public int getCount() {

        return users.size();

    }

    @Nullable
    @Override
    public User getItem(int position) {
        return users.get(position);
    } //Return the item in the giving position

    @Override
    public int getPosition(@Nullable User item) {
        return users.indexOf(item);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parents){
        if ( convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource,parents,false);

        }

        TextView Id = (TextView) convertView.findViewById(R.id.Id);
        TextView Fname = (TextView) convertView.findViewById(R.id.Fname);
        TextView Lname = (TextView) convertView.findViewById(R.id.Lname);
        ImageView Avatar= (ImageView) convertView.findViewById(R.id.Avatar);
        TextView Email= (TextView) convertView.findViewById(R.id.Email);
        Id.setText(getItem(position).getId_str());
        Fname.setText(getItem(position).getFirst_name());
        Lname.setText(getItem(position).getLast_name());
        Picasso.with(mContext).load(getItem(position).getAvatar().toString()).into(Avatar);
        Email.setText(getItem(position).getEmail().toString());


        return convertView;
    }

}