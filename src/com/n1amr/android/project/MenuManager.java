package com.n1amr.android.project;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuManager extends ListActivity {

    String[] classNames = { "MainActivity", "TextPlay", "Email", "Camera",
	    "Data", "OpenedClass", "GFX", "GFXSurface", "SoundStuff",
	    "MySlidingDrawer", "Tabs", "Tabs2", "SimpleBrowser", "Flipper",
	    "SharedPrefs", "InternalData", "ExternalData", "SQLView",
	    "SQLiteExample", "Accelerate", "Maps", "Faces" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setListAdapter(new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, classNames));

	Class<?> c;
	try {
	    c = Class.forName("com.n1amr.android.project."
		    + classNames[classNames.length - 1]);
	    Intent intent = new Intent(this, c);
	    startActivity(intent);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
	super.onListItemClick(l, v, position, id);
	Intent intent;
	try {
	    Class<?> c = Class.forName("com.n1amr.android.project."
		    + classNames[position]);
	    intent = new Intent(this, c);
	    startActivity(intent);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	switch (position) {
	    case 19:
		// intent=new
		// Intent(android.bluetooth.BluetoothAdapter.EXTRA_SCAN_MODE);
		// startActivity(intent);
		break;
	}
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main_options_menu, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	Intent intent;

	switch (item.getItemId()) {
	    case R.id.aboutUs:
		// try {
		// Class<?> c =
		// Class.forName("com.n1amr.android.project.AboutUs");
		// Intent intent = new Intent(MenuManager.this, c);
		// startActivity(intent);
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// }
		intent = new Intent("com.n1amr.android.project.ABOUT");
		startActivity(intent);
		break;
	    case R.id.preferences:
		intent = new Intent("com.n1amr.android.project.PREFERENCES");
		startActivity(intent);
		break;
	    case R.id.exit:
		finish();
		break;
	}
	return super.onOptionsItemSelected(item);
    }
}
