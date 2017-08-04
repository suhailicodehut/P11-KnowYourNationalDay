package com.suhaili.a15017519.p11_knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> contentList = new ArrayList<String>();
    private ArrayAdapter<String> listAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lv);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contentList);
        contentList.add("Singapore's National Day is on 9 Aug");
        contentList.add("Singapore is 52 years old");
        contentList.add("Theme is #OneNationTogether");
        lv.setAdapter(listAdapter);

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout passPhrase =
                (LinearLayout) inflater.inflate(R.layout.login, null);
        final EditText etPassphrase = (EditText) passPhrase
                .findViewById(R.id.editTextPassPhrase);

        final String code = etPassphrase.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("code",code);
        if(code.equalsIgnoreCase("")){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please login")
                .setView(passPhrase)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "You had entered " +
                                etPassphrase.getText().toString(), Toast.LENGTH_LONG).show();
                        if(etPassphrase.getText().toString().equalsIgnoreCase("738964")){

                        }else{
                            finish();
                        }
                    }

                })
                .setNegativeButton("No Access Code", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"No code", Toast.LENGTH_LONG).show();
                        finish();

                    }
                });



        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tally against the respective action item clicked
        //  and implement the appropriate action
        if (item.getItemId() == R.id.sendToFriend) {
            String [] list = new String[] { "Email", "SMS"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    // Set the list of items easily by just supplying an
                    //  array of the items
                    .setItems(list, new DialogInterface.OnClickListener() {
                        // The parameter "which" is the item index
                        // clicked, starting from 0
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                Toast.makeText(MainActivity.this, "Email",
                                        Toast.LENGTH_LONG).show();
                                Intent email = new Intent(Intent.ACTION_SEND);

                                email.putExtra(Intent.EXTRA_SUBJECT, "National Day Contents");
                                String text = "";
                                for (int i = 0; i < contentList.size(); i++) {
                                    text = text + contentList.get(i)+"\n";

                                }
                                email.putExtra(Intent.EXTRA_TEXT, text);
                                email.setType("message/rfc822");
                                startActivity(Intent.createChooser(email,
                                        "Choose an Email client :"));


                            } else if (which == 1) {
                                Toast.makeText(MainActivity.this, "SMS",
                                        Toast.LENGTH_LONG).show();
                                SmsManager sms = SmsManager.getDefault();
                                String text = "";
                                for (int i = 0; i < contentList.size(); i++) {
                                    text = text + contentList.get(i)+"\n";

                                }
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "number"));
                                intent.putExtra("sms_body", text);
                                startActivity(intent);

                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        else if (item.getItemId() == R.id.quiz) {
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout passPhrase =
                    (LinearLayout) inflater.inflate(R.layout.quiz, null);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Test Yourself")
                    .setView(passPhrase)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            final RadioGroup rg1 = (RadioGroup)passPhrase.findViewById(R.id.rg1);
                            int selectedButtonId1 = rg1.getCheckedRadioButtonId();
                            // Get the radio button object from the Id we had gotten above
                            final RadioButton rb1 = (RadioButton) passPhrase.findViewById(selectedButtonId1);
                            final RadioGroup rg2 = (RadioGroup)passPhrase.findViewById(R.id.rg2);
                            int selectedButtonId2 = rg2.getCheckedRadioButtonId();
                            // Get the radio button object from the Id we had gotten above
                            final RadioButton rb2 = (RadioButton) passPhrase.findViewById(selectedButtonId2);
                            final RadioGroup rg3 = (RadioGroup)passPhrase.findViewById(R.id.rg3);
                            int selectedButtonId3 = rg3.getCheckedRadioButtonId();
                            // Get the radio button object from the Id we had gotten above
                            final RadioButton rb3 = (RadioButton) passPhrase.findViewById(selectedButtonId3);
                            int score = 0;
                            if (rb1.getText().toString().equalsIgnoreCase("no")){
                                score = score +1;
                            }
                            if (rb2.getText().toString().equalsIgnoreCase("yes")){
                                score = score +1;
                            }
                            if (rb3.getText().toString().equalsIgnoreCase("yes")){
                                score = score +1;
                            }
                            Toast.makeText(MainActivity.this, score+"/3", Toast.LENGTH_LONG).show();


                        }
                    })
                    .setNegativeButton("Don't know lah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        else if (item.getItemId() == R.id.quit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")
                    // Set text for the positive button and the corresponding
                    //  OnClickListener when it is clicked
                    .setPositiveButton("Not really", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {			  Toast.makeText(MainActivity.this, "You clicked yes",
                                Toast.LENGTH_LONG).show();


                        }
                    })
                    // Set text for the negative button and the corresponding
                    //  OnClickListener when it is clicked
                    .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {			  Toast.makeText(MainActivity.this, "You clicked no",
                                Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }

        return super.onOptionsItemSelected(item);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
