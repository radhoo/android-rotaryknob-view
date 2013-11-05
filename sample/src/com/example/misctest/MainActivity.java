package com.example.misctest;

import com.example.misctest.RoundKnobButton.RoundKnobButtonListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

/*
File:              RoundKnobButton
Version:           1.0.0
Release Date:      November, 2013
License:           GPL v2
Description:	   A round knob button to control volume and toggle between two states

****************************************************************************
Copyright (C) 2013 Radu Motisan  <radu.motisan@gmail.com>

http://www.pocketmagic.net

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
****************************************************************************/
public class MainActivity extends Activity {

	Singleton m_Inst = Singleton.getInstance();
		
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        // Scaling mechanism, as explained on:
        // http://www.pocketmagic.net/2013/04/how-to-scale-an-android-ui-on-multiple-screens/
        m_Inst.InitGUIFrame(this);
        
        RelativeLayout panel = new RelativeLayout(this);
        setContentView(panel);
       
        TextView tv = new TextView(this); tv.setText("Rotary knob control\nRadu Motisan 2013\nwww.pocketmagic.net"); tv.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
  		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
  		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
  		panel.addView(tv, lp);
      	
        final TextView tv2 = new TextView(this); tv2.setText("");
        lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
  		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
  		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
  		panel.addView(tv2, lp);

          		
        RoundKnobButton rv = new RoundKnobButton(this, R.drawable.stator, R.drawable.rotoron, R.drawable.rotoroff, 
        		m_Inst.Scale(250), m_Inst.Scale(250));
        lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		panel.addView(rv, lp);
        
        rv.setRotorPercentage(100);
        rv.SetListener(new RoundKnobButtonListener() {
			public void onStateChange(boolean newstate) {
				Toast.makeText(MainActivity.this,  "New state:"+newstate,  Toast.LENGTH_SHORT).show();
			}
			
			public void onRotate(final int percentage) {
				tv2.post(new Runnable() {
					public void run() {
						tv2.setText("\n" + percentage + "%\n");
					}
				});
			}
		});
        
        
    }

    
}
