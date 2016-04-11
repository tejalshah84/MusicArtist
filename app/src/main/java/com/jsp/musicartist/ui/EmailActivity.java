package com.jsp.musicartist.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jsp.musicartist.R;
import com.jsp.musicartist.exception.EmailFormatException;

import java.util.List;

/**
 * Created by Tejal Shah.
 */
public class EmailActivity extends AppCompatActivity {

    EditText sendToView, subjectView, messageView;
    String[] sendTo;
    String subject, message;
    Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sendToView = (EditText) findViewById(R.id.SendTo);
        subjectView = (EditText) findViewById(R.id.Subject);
        messageView = (EditText) findViewById(R.id.Message);
        sendEmail = (Button) findViewById(R.id.SendEmailButton);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEmailClient();
            }
        });
    }

    public void launchEmailClient(){
        sendTo = new String[1];
        sendTo[0] = sendToView.getText().toString();
        subject = subjectView.getText().toString();
        message = messageView.getText().toString();

        try {
            if (!sendTo[0].contains("@") || !sendTo[0].contains(".")) {
                throw new EmailFormatException(0, EmailActivity.this, sendTo[0]);
            }
        }catch (EmailFormatException e){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(EmailActivity.this);
            builder1.setMessage("Incorrect Email Address Format");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder1.create();
            alert.show();
            return;
        }

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, sendTo);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");
        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(emailIntent,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;

        if(isIntentSafe){
            String title = getResources().getString(R.string.EmailName);
            Intent chooser = Intent.createChooser(emailIntent, "Email");
            if (emailIntent.resolveActivity(packageManager) != null) {
                startActivity(chooser);
            }
        }
        else{
            AlertDialog.Builder builder2 = new AlertDialog.Builder(EmailActivity.this);
            builder2.setMessage("No Email Client Available, Please Download.");
            builder2.setCancelable(true);

            builder2.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder2.create();
            alert.show();
            return;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_gallery) {
            Intent intent = new Intent(this, PortfolioActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_mail) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
