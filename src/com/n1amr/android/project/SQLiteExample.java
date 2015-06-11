package com.n1amr.android.project;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {
    Button bUpdateSQL, bViewSQL, bSQLGet, bSQLEdit, bSQLDelete;
    EditText etSQLName, etSQLAge, etSQLRow;
    HotOrNot mHotOrNot;

    // Spinner mSpinner;
    // String selectedName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_sqlite_example);

	bUpdateSQL = (Button) findViewById(R.id.bUpdateSQL);
	bViewSQL = (Button) findViewById(R.id.bViewSQL);
	bSQLGet = (Button) findViewById(R.id.bSQLGet);
	bSQLEdit = (Button) findViewById(R.id.bSQLEdit);
	bSQLDelete = (Button) findViewById(R.id.bSQLDelete);

	bUpdateSQL.setOnClickListener(this);
	bViewSQL.setOnClickListener(this);
	bSQLGet.setOnClickListener(this);
	bSQLEdit.setOnClickListener(this);
	bSQLDelete.setOnClickListener(this);

	etSQLName = (EditText) findViewById(R.id.etSQLName);
	etSQLAge = (EditText) findViewById(R.id.etSQLAge);
	etSQLRow = (EditText) findViewById(R.id.etSQLRow);

	// mSpinner = (Spinner) findViewById(R.id.spinner1);
	//
	// HotOrNot mHotOrNot = new HotOrNot(this);
	// mHotOrNot.open();
	//
	// List<String> listNames = mHotOrNot.listAllNames();
	//
	// mHotOrNot.close();
	//
	// ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this,
	// android.R.layout.simple_spinner_item, listNames);
	// mSpinner.setAdapter(mArrayAdapter);
	// mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	// @Override
	// public void onItemSelected(AdapterView<?> parent, View view,
	// int position, long id) {
	// selectedName = listNames.get(position);
	// }
	//
	// @Override
	// public void onNothingSelected(AdapterView<?> parent) {
	// }
	// });

    }

    @Override
    public void onClick(View v) {

	String sRow, sName, sAge;
	sName = etSQLName.getText().toString();
	sAge = etSQLAge.getText().toString();
	long lRow;
	try {
	    switch (v.getId()) {
		case R.id.bUpdateSQL:

		    HotOrNot mHotOrNot = new HotOrNot(this);
		    mHotOrNot.open();
		    mHotOrNot.createEntry(sName, sAge);
		    mHotOrNot.close();

		    Dialog mDialog = new Dialog(this);
		    mDialog.setTitle("OK");
		    TextView mTextView = new TextView(this);
		    mTextView.setText("Success");
		    mDialog.setContentView(mTextView);
		    mDialog.show();

		    break;
		case R.id.bViewSQL:
		    Intent mIntent = new Intent(this, SQLView.class);
		    startActivity(mIntent);
		    break;
		case R.id.bSQLGet:
		    sRow = etSQLRow.getText().toString();
		    lRow = Long.parseLong(sRow);

		    mHotOrNot = new HotOrNot(this);
		    mHotOrNot.open();

		    String returnedName = mHotOrNot.getName(lRow);
		    String returnedAge = mHotOrNot.getAge(lRow);

		    etSQLName.setText(returnedName);
		    etSQLAge.setText(returnedAge);

		    mHotOrNot.close();
		    break;
		case R.id.bSQLEdit:
		    sRow = etSQLRow.getText().toString();
		    lRow = Long.parseLong(sRow);

		    mHotOrNot = new HotOrNot(this);
		    mHotOrNot.open();

		    mHotOrNot.updateEntry(lRow, sName, sAge);

		    mHotOrNot.close();

		    break;
		case R.id.bSQLDelete:
		    sRow = etSQLRow.getText().toString();
		    lRow = Long.parseLong(sRow);

		    mHotOrNot = new HotOrNot(this);
		    mHotOrNot.open();

		    etSQLName.setText(null);
		    etSQLAge.setText(null);

		    mHotOrNot.deleteEntry(lRow);

		    mHotOrNot.close();
		    break;
	    }
	} catch (Exception e) {
	    Dialog mDialog = new Dialog(this);
	    mDialog.setTitle("ERROR");
	    TextView mTextView = new TextView(this);
	    mTextView.setText(e.toString());
	    mDialog.setContentView(mTextView);
	    mDialog.show();
	}
    }
}
