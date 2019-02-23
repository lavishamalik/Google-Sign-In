package com.example.lavisha.loginusinggoogle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class NewActivity extends AppCompatActivity {
    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layput_new_activity);
        TextView name=findViewById(R.id.tvName);
        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient=GoogleSignIn.getClient(this,googleSignInOptions);
        TextView email=findViewById(R.id.tvEmail);
        if(getIntent().hasExtra("Name")&&getIntent().hasExtra("Email"))
        {
            String n=getIntent().getStringExtra("Name");
            String e=getIntent().getStringExtra("Email");
            name.setText(n);
            email.setText(e);
        }
        Button signOut=findViewById(R.id.btnSignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnSignOut:
                        signOut();
                        break;
                }
            }
        });
    }

    private void signOut() {

        googleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateUI();
            }
        });
    }

    private void updateUI() {
            Intent intent=new Intent(NewActivity.this,MainActivity.class);
            startActivity(intent);
    }
}
