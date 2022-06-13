package com.example.registrationappp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    private ImageView imgProfile;
    private Button btnPickImage, btnRegister;
    private EditText txtName, txtEmail, txtPassword, txtRepPassword;
    private TextView txtWarnName, txtWarnEmail, txtWarnPass, txtWarnRepPass;
    private CheckBox licenseCheck;
    private RadioGroup rgGender;
    private Spinner countrySpinner;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        System.out.println("First Change");
        System.out.println("Do whatever you want :)");

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Image Selected!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        });
    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");

        imgProfile = findViewById(R.id.imgProfile);
        btnPickImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtRepPassword = findViewById(R.id.txtRepPassword);
        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPass = findViewById(R.id.txtWarnPass);
        txtWarnRepPass = findViewById(R.id.txtWarnRepPass);

        licenseCheck = findViewById(R.id.licenseCheck);
        rgGender = findViewById(R.id.rgGender);
        countrySpinner = findViewById(R.id.countrySpinner);

        parent = findViewById(R.id.parent);
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: Started");

        if (validateData()) {
            if (licenseCheck.isChecked()) {
                showSnackBar();
            } else {
                Toast.makeText(MainActivity.this, "You Need To Agree to the License Agreement First", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: Started");

        if (txtName.getText().toString().equals("")) {
            txtWarnName.setVisibility(View.VISIBLE);
            txtWarnName.setText("Enter Your Name:");
            return false;
        }

        if (txtEmail.getText().toString().equals("")) {
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("Enter Your Email:");
            return false;
        }

        if (txtPassword.getText().toString().equals("")) {
            txtWarnPass.setVisibility(View.VISIBLE);
            txtWarnPass.setText("Enter Your Password:");
            return false;
        }

        if (txtRepPassword.getText().toString().equals("")) {
            txtWarnRepPass.setVisibility(View.VISIBLE);
            txtWarnRepPass.setText("Please Re-enter Password:");
            return false;
        }
        if(!txtPassword.getText().toString().equals(txtRepPassword.getText().toString())){
            txtWarnRepPass.setVisibility(View.VISIBLE);
            txtWarnRepPass.setText("Password dose not match");
            return false;
        }
        return true;

    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");

        String Name=txtName.getText().toString();
        String Email=txtEmail.getText().toString();
        String Country=countrySpinner.getSelectedItem().toString();
        String gender="";

        switch (rgGender.getCheckedRadioButtonId()){
            case R.id.rbMale:
                gender="Male";
                break;
            case R.id.rbFemale:
                gender="Female";
                break;
            case R.id.rbOthers:
                gender="Other";
                break;
            default:
                gender="Unknown";
                break;
        }

        String snackText = "Name: "+ Name +"\n"+
                "Email: "+Email+"\n"+
                "Country: "+Country+"\n"+
                "Gender: "+gender;

        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtName.setText("");
                        txtEmail.setText("");
                        txtPassword.setText("");
                        txtRepPassword.setText("");
                    }
                })
                .show();
    }
}