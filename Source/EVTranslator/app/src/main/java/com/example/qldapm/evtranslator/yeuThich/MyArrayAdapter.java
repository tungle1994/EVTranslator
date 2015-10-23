package com.example.qldapm.evtranslator.yeuThich;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.qldapm.evtranslator.R;

import java.util.List;

/**
 * Created by vanty on 10/23/2015.
 */
public class MyArrayAdapter extends ArrayAdapter<Favorite_Object> {
    public List<Favorite_Object>danhhSach;
    public int id_Layout;
    public Activity context;
    public MyArrayAdapter(Activity app,int id,List<Favorite_Object>danhsach)
    {
        super(app, id, danhsach);
        danhhSach = danhsach;
        id_Layout = id;
        context = app;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater infla = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infla.inflate(id_Layout,null);

        }
        TextView Title = (TextView)convertView.findViewById(R.id.lbl_title_favorite);
        Title.setText(danhhSach.get(position).getNoiDung());
        TextView date = (TextView)convertView.findViewById(R.id.lbl_favorite_date);
        date.setText(danhhSach.get(position).getNgaySave());
        return  convertView;
    }
}
