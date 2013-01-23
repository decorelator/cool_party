package com.kmware.hrm.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.widget.TextView;

public class SimpleTextView extends TextView
{
    private final int DEFAULT_TEXT_SIZE = 14;
    private int textSize = DEFAULT_TEXT_SIZE;
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext()); ; 
    
    
    public SimpleTextView(Context context)
    {
        super(context);
        textSize = Integer.parseInt(prefs.getString("Pref_text_size", "" + DEFAULT_TEXT_SIZE));
        this.setTextSize(textSize);
    }

    public SimpleTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        textSize = Integer.parseInt(prefs.getString("Pref_text_size", "" + DEFAULT_TEXT_SIZE));
        this.setTextSize(textSize);
    }

    public SimpleTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        textSize = Integer.parseInt(prefs.getString("Pref_text_size", "" + DEFAULT_TEXT_SIZE));
        this.setTextSize(textSize);
    }

    public void setGlobalTextSize(int size)
    {
        textSize = size;
    }

    public int getGlobalTextSize()
    {
        return textSize;
    }
}
