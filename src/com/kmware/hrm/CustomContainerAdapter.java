package com.kmware.hrm;

import java.util.ArrayList;

import model.BaseModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomContainerAdapter extends ArrayAdapter<BaseModel> {
	
	public static String LOGTAG = CustomContainerAdapter.class.getSimpleName();
	
	Context ctx;
	  LayoutInflater lInflater;
	  ArrayList<BaseModel> objects;

	  
	  CustomContainerAdapter(Context context, ArrayList<BaseModel> products, int layout) {
		  super(context, layout, products);
	    ctx = context;
	    objects = products;
	    lInflater = (LayoutInflater) ctx
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }




	  // пункт списка
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    // используем созданные, но не используемые view
	    View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.list_container_row, parent, false);
	    }

	    BaseModel p = getItem(position);

	    // заполняем View в пункте списка данными 
	    ((TextView) view.findViewById(R.id.tvTitle)).setText(""+p.getId());
	    ((TextView) view.findViewById(R.id.tvDescription)).setText(p.getName());
	    
	    return view;
	  }

}
