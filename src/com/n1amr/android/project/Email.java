package com.n1amr.android.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends Activity implements View.OnClickListener {

    EditText personsEmail, intro, personsName, stupidThings, hatefulAction,
	    outro;
    Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.email);
	initializeVars();
	sendEmail.setOnClickListener(this);
    }

    private void initializeVars() {
	personsEmail = (EditText) findViewById(R.id.etEmails);
	intro = (EditText) findViewById(R.id.etIntro);
	personsName = (EditText) findViewById(R.id.etName);
	stupidThings = (EditText) findViewById(R.id.etThings);
	hatefulAction = (EditText) findViewById(R.id.etAction);
	outro = (EditText) findViewById(R.id.etOutro);
	sendEmail = (Button) findViewById(R.id.bSentEmail);
    }

    @Override
    public void onClick(View v) {

	String emailaddress[] = { personsEmail.getText().toString() };

	Intent intent = new Intent(android.content.Intent.ACTION_SEND);
	intent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
	intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "I hate you");
	intent.setType("plain/text");

	intent.putExtra(android.content.Intent.EXTRA_TEXT, getMyMessage());
	startActivity(intent);
    }

    private String getMyMessage() {
	return "Well hello "
		+ personsName.getText().toString()
		+ " I just wanted to say "
		+ intro.getText().toString()
		+ ".  Not only that but I hate when you "
		+ stupidThings.getText().toString()
		+ ", that just really makes me crazy.  I just want to make you "
		+ hatefulAction.getText().toString()
		+ ".  Welp, thats all I wanted to chit-chatter about, oh and"
		+ outro.getText().toString()
		+ ".  Oh also if you get bored you should check out www.mybringback.com"
		+ '\n' + "PS. I think I love you...   :(";
    }

    @Override
    protected void onPause() {
	super.onPause();
	finish();
    }
}