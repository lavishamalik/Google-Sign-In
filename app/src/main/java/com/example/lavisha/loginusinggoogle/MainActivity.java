package com.example.lavisha.loginusinggoogle;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient=GoogleSignIn.getClient(this,googleSignInOptions);
        SignInButton signInButton=findViewById(R.id.googleSignInbtn);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId())
                {
                    case R.id.googleSignInbtn:
                        signIn();
                        break;
                }
            }

            private void signIn() {
                Intent intent=googleSignInClient.getSignInIntent();
                startActivityForResult(intent,123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123){
            Task<GoogleSignInAccount>task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignIn(task);
        }
    }

    private void handleSignIn(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount googleSignInAccount=task.getResult(ApiException.class);
            updateUI(googleSignInAccount);

        } catch (ApiException e) {
            updateUI(null);
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        Log.e("TAG","inupdateUI");
        if(account==null){


        }
        else{
            Intent intent=new Intent(MainActivity.this,NewActivity.class);
            String name = account.getDisplayName();
            String email = account.getEmail();
            intent.putExtra("Name",name);
            intent.putExtra("Email",email);
            Log.d("CheckTag",name);
            Log.d("CheckTag",email);
            startActivity(intent);
        }
    }
}
