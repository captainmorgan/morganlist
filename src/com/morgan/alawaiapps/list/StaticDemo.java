package com.morgan.alawaiapps.list;

/***
	Copyright (c) 2008-2009 CommonsWare, LLC
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may obtain
	a copy of the License at
		http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

import android.app.Activity;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

public class StaticDemo extends ListActivity {

	final String TAG ="List";
	
	TextView selection;
	String[] items={"lorem", "ipsum", "dolor", "sit", "amet",
					"consectetuer", "adipiscing", "elit", "morbi", "vel",
					"ligula", "vitae", "arcu", "aliquet", "mollis",
					"etiam", "vel", "erat", "placerat", "ante",
					"porttitor", "sodales", "pellentesque", "augue",
					"purus"};
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		setListAdapter(new ArrayAdapter<String>(this,
												R.layout.row, R.id.label,
												items));
		selection=(TextView)findViewById(R.id.selection);
	}
	
	public void onListItemClick(ListView parent, View v, int position,	long id) {
	 	//selection.setText(items[position]);

		
		switch (position+1) {
		case 1:
			Log.d(TAG, "case 1. list position =" + position);
			Intent q = new Intent(StaticDemo.this, SoftwarePassionView.class);
			startActivity(q);
			break;
		case 2:
			Log.d(TAG, "case 2. list position =" + position);
			Intent q1 = new Intent(StaticDemo.this, SoftwarePassionView.class);
			startActivity(q1);
			break;
		default:
			Log.d(TAG, "default case. list position =" + position);
			Intent q2 = new Intent(StaticDemo.this, MediaPlayerDemo.class);
			startActivity(q2);
			break;
		}
	
		//Intent q = new Intent(StaticDemo.this, MediaPlayerDemo.class);
		
		// TODO
		// pass variable to this activity with the index
		//startActivity(q);
		
	}
}
