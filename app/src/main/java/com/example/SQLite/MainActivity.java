package com.example.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdihakimtask5.R;

public class MainActivity extends AppCompatActivity {

    EditText name, programCode, fees;
    TextView nameError, programCodeError, feeError;
    int id = 1;
    float fee = 0;
    Button submit, show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextName);
        programCode = (EditText) findViewById(R.id.editTextProgramCode);
        fees = (EditText) findViewById(R.id.editTextFees);
        submit = (Button) findViewById(R.id.submit);
        show = (Button) findViewById(R.id.show);
        nameError = (TextView) findViewById(R.id.nameError);
        programCodeError = (TextView) findViewById(R.id.programCodeError);
        feeError = (TextView) findViewById(R.id.feeError);
    }

    public void onSubmit(View view){
        if (name.getText().toString() != "" && programCode.getText().toString() != "" && Float.parseFloat(fees.getText().toString()) != 0)
        {
            fee =  Float.parseFloat(fees.getText().toString());
            SQLiteDatabase db = openOrCreateDatabase("Student Registration", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS students(id INT, name VARCHAR, programCode VARCHAR, fees FLOAT);");
            db.execSQL("INSERT INTO students(id,name,programCode,fees) VALUES("+id+",'"+name.getText()+"','"+programCode.getText()+"',"+fee+")");
            Toast.makeText(this, "Record added", Toast.LENGTH_LONG).show();
            id++;
        }
    }

    public void onShow(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();}});
        builder.setTitle(name.getText());
        builder.setMessage("Student Name: "+name.getText() +"\nProgram Code: "+programCode.getText() + "\nFees: " +fees.getText());
        builder.show();
    }
}