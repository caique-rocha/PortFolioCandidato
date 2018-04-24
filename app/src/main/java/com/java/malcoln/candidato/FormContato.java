package com.java.malcoln.candidato;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormContato extends AppCompatActivity {
    Button btnOK;
    EditText txtTo;
    EditText txtSubject;
    EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contato);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            btnOK = (Button) findViewById(R.id.btnOK);
            txtTo = (EditText) findViewById(R.id.etTo);
            txtTo.requestFocus();
            txtSubject = (EditText) findViewById(R.id.etSubject);
            txtMessage = (EditText) findViewById(R.id.etMessage);
            btnOK.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String to = txtTo.getText().toString();
                    String subject = txtSubject.getText().toString();
                    String message = txtMessage.getText().toString();
                    Intent mail = new Intent(Intent.ACTION_SEND);
                    //mail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mail.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                    mail.putExtra(Intent.EXTRA_SUBJECT, subject);
                    mail.putExtra(Intent.EXTRA_TEXT, message);
                    mail.setType("message/rfc822");
                    try{
                    startActivity(Intent.createChooser(mail, "Enviar email via:"));
                    finish(); }
                    catch (android.content.ActivityNotFoundException ex){
                        Toast.makeText(FormContato.this, "Não há cliente de e.mail instalado.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case android.R.id.home:
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
