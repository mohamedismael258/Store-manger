package com.example.storemanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.storemanger.databinding.ActivityCreatNewAccountBinding;
import com.example.storemanger.databinding.FragmentAddBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatNewAccount extends AppCompatActivity {
ActivityCreatNewAccountBinding binding;
Const aConst=new Const();
DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding =ActivityCreatNewAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth=FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
        binding.creatNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(binding.email.getText().toString().trim(),binding.password.getText().toString().trim());
            }
        });
    }
    public void validate(String email, String password) {
        if (email.isEmpty()) {
            binding.email.setError(getText(R.string.error));
        } else if (password.isEmpty()) {
            binding.password.setError(getText(R.string.error));
        } else {
            creatAccount(email,password);
        }
    }

    public void creatAccount(String email,String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            mAuth.updateCurrentUser(user);
                            sendData(mAuth.getUid());
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(CreatNewAccount.this, "Invalid email",
                                    Toast.LENGTH_SHORT).show();
                            binding.email.setText("");
                            binding.password.setText("");
                        }
                    }
                });
    }
    private void sendData(String uId)
    {
        ref.child(aConst.KEY_USERS).child(uId).setValue("");
        intentAcvtivity(uId);
    }
    private void intentAcvtivity(String uId)
    {
        Intent intent = new Intent(CreatNewAccount.this, MainActivity2.class);
        intent.putExtra("message_key", uId);
        startActivity(intent);
    }

}