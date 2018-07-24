package com.rp.qai.famtree;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {

    TextView tvEditName, tvEditTitle, tvEditAddress, tvEditNumber, tvEditRelation;
    EditText etEditName, etEditTitle, etEditAddress, etEditNumber, etEditRelation;
    Button btnUpdate, btnDelete, btnCancel, btnEditImage;
    ImageView editDisplayPic, ivCall, ivLocation, ivMessage;
    Member data;
    MemberAdapter aa;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        etEditName = findViewById(R.id.etEditName);
        etEditTitle = findViewById(R.id.etEditTitle);
        etEditAddress = findViewById(R.id.etEditAddress);
        etEditNumber = findViewById(R.id.etEditNumber);
        etEditRelation = findViewById(R.id.etEditRelation);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        editDisplayPic = findViewById(R.id.editDisplayPic);
        btnEditImage = findViewById(R.id.btnEditImage);
        ivCall = findViewById(R.id.ivCall);
        ivLocation = findViewById(R.id.ivLocation);
        ivMessage = findViewById(R.id.ivMessage);

        Intent i = getIntent();
        data = (Member)i.getSerializableExtra("data");
        etEditName.setText(data.getName());
        etEditRelation.setText(data.getRelation());
        etEditTitle.setText(data.getTitle());
        etEditAddress.setText(data.getAddress());
        etEditNumber.setText(""+data.getNumber());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setName(etEditName.getText().toString());
                data.setRelation(etEditRelation.getText().toString());
                data.setTitle(etEditTitle.getText().toString());
                data.setAddress(etEditAddress.getText().toString());
                data.setNumber(Integer.parseInt(etEditNumber.getText().toString()));
                int data1 = dbh.updateMember(data);
                Toast.makeText(ModifyActivity.this, data1+" " +data.getId(), Toast.LENGTH_SHORT).show();
                dbh.close();
                setResult(RESULT_OK);
                finish();


            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteMember(data.getId());
                dbh.close();
                setResult(RESULT_OK);
                finish();

            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ModifyActivity.this,
                        MessageActivity.class);
                String name = data.getName();
                int number = data.getNumber();
                i.putExtra("name", name);
                i.putExtra("number", number+"");
                startActivity(i);
            }
        });

    }
    private void makePhoneCall(){
        String number = etEditNumber.getText().toString();
        if (number.trim().length() > 0){
            if (ContextCompat.checkSelfPermission(ModifyActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(ModifyActivity.this,
                        new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }else{
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }else{
            Toast.makeText(ModifyActivity.this,"Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else{
                Toast.makeText(ModifyActivity.this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
