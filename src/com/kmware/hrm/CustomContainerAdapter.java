package com.kmware.hrm;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomContainerAdapter extends BaseAdapter {
	Context ctx;
	  LayoutInflater lInflater;
	  ArrayList<ContainerRow> objects;

	  CustomContainerAdapter(Context context, ArrayList<ContainerRow> products) {
	    ctx = context;
	    objects = products;
	    lInflater = (LayoutInflater) ctx
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }

	  // кол-во элементов
	  @Override
	  public int getCount() {
	    return objects.size();
	  }

	  // элемент по позиции
	  @Override
	  public Object getItem(int position) {
	    return objects.get(position);
	  }

	  // id по позиции
	  @Override
	  public long getItemId(int position) {
	    return position;
	  }

	  // пункт списка
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    // используем созданные, но не используемые view
	    View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.list_container_row, parent, false);
	    }

	    ContainerRow p = getContainerRow(position);

	    // заполняем View в пункте списка данными 
	    ((TextView) view.findViewById(R.id.tvTitle)).setText(p.title);
	    ((TextView) view.findViewById(R.id.tvDescription)).setText(p.description);
	    
	    return view;
	  }

	  // товар по позиции
	  ContainerRow getContainerRow(int position) {
	    return ((ContainerRow) getItem(position));
	  }

}
