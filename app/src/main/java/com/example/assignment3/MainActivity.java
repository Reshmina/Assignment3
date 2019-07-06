package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmObject;

import static io.realm.Realm.getDefaultInstance;

public class MainActivity extends AppCompatActivity {

    Switch switch1;
    Button submit, display;
    TextView name, roll, dept, phone;
    EditText nameet, rollet, deptet, phoneet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switch1 = findViewById(R.id.Switch);
        submit = findViewById(R.id.Submit);
        display = findViewById(R.id.DisplayRecords);
        nameet = findViewById(R.id.edittext1);
        deptet = findViewById(R.id.edittext2);
        rollet = findViewById(R.id.edittext3);
        phoneet = findViewById(R.id.edittext4);

    }
    public void onSubmitpressed(View view) {
        if(nameet.getText().toString().equals("")){
            nameet.requestFocus();
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return;
        }else if(deptet.getText().toString().equals("")){
            deptet.requestFocus();
            Toast.makeText(this, "Please enter dept", Toast.LENGTH_SHORT).show();
            return;
        }else if(rollet.getText().toString().equals("")){
            rollet.requestFocus();
            Toast.makeText(this, "Please enter roll no", Toast.LENGTH_SHORT).show();
            return;
        }else if(phoneet.getText().toString().equals("")){
            phoneet.requestFocus();
            Toast.makeText(this, "Please enter phone no", Toast.LENGTH_SHORT).show();
            return;
        }
        String depart=deptet.getText().toString().toUpperCase();
        if(!depart.equals("CSE") && !depart.equals("ECE") && !depart.equals("EE") && !depart.equals("IT")){
            deptet.setText("");
            deptet.requestFocus();
            Toast.makeText(this, "Please enter either CSE, ECE, IT or EE", Toast.LENGTH_SHORT).show();
            return;
        }





        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        try {
            Person person = realm.createObject(Person.class,System.currentTimeMillis()/1000);
            person.setName(nameet.getText().toString());
            person.setDept(deptet.getText().toString().toUpperCase());
            person.setPhone(phoneet.getText().toString());
            person.setRoll(rollet.getText().toString());
            person.setGender(switch1.isChecked());
            realm.commitTransaction();
            Toast.makeText(this, "Success  ", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            realm.cancelTransaction();
            Toast.makeText(this, "Failure  "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        /*finally
        {
            realm.close();
        }*/
    }
        public void onDisplayPressed(View view)
        {
            Intent intent =  new Intent(this, DisplayActivity.class);
            startActivity(intent);
        }
        @Override
        protected void onDestroy()
        {
            super.onDestroy();
        }

}
