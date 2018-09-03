package com.example.ubuntu.youtubejson8series;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class ActorAdapter extends ArrayAdapter<Actor> {

    ArrayList<Actor> arrayList;
    int Resource;
    Context context;
    LayoutInflater layoutInflater;
    public ActorAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Actor> objects) {
        super(context, resource, objects);

        arrayList = objects;
        Resource = resource;
        this.context = context;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        holder = new ViewHolder();
        if(convertView == null){
          convertView = layoutInflater.inflate(Resource, null);


          holder.imageView = convertView.findViewById(R.id.iv_image);
            holder.textViewName = convertView.findViewById(R.id.tv_name);
            holder.textViewCity = convertView.findViewById(R.id.tv_city);
            holder.textViewChildren = convertView.findViewById(R.id.tv_child);
            holder.textViewId = convertView.findViewById(R.id.tv_id);
            holder.textViewCountry = convertView.findViewById(R.id.tv_country);
            holder.textViewDescription = convertView.findViewById(R.id.tv_description);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewName.setText((arrayList.get(position).getName()));
        holder.textViewCity.setText((arrayList.get(position).getCity()));
        holder.textViewCountry.setText((arrayList.get(position).getCountry()));
        holder.textViewChildren.setText((arrayList.get(position).getChildren()));
        holder.textViewDescription.setText((arrayList.get(position).getDescription()));
        holder.textViewId.setText((arrayList.get(position).getId()));



//////////////////////////////display image////////////////

        // Then later, when you want to display image
        ImageLoader.getInstance().displayImage(arrayList.get(position).getImage(), holder.imageView); // Default options will be used

//////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



        return convertView;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewCity;
        public TextView textViewCountry;
        public TextView textViewId;
        public TextView textViewDescription;
        public TextView textViewChildren;
    }
}
