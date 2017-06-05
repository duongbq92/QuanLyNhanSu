package com.example.admin.quanlynhansu;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.quanlynhansu.DTO.MainMenu;

import java.util.List;

/**
 * Created by RPG_LOVER on 02/05/2017.
 */

public class CustomMenuAdapter extends ArrayAdapter<MainMenu> {
    Context context;
    List<MainMenu> list;
    int resource;

    public CustomMenuAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MainMenu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.main_menu, null);
        MainMenu mainMenu = list.get(position);
        ImageView imgView = (ImageView)view.findViewById(R.id.imgView);
        TextView txtItem = (TextView)view.findViewById(R.id.txtItem);
        imgView.setImageResource(mainMenu.getImage_id());
        txtItem.setText(mainMenu.getMenu_items());
        return view;
    }
}
