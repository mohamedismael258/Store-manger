package com.example.storemanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.storemanger.databinding.ActivityMain2Binding;
import com.example.storemanger.databinding.ActivitySigninBinding;
import com.example.storemanger.databinding.FragmentAddBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
ActivityMain2Binding binding;
ArrayList<ModelFragment>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentAdd fragmentaddd = new FragmentAdd();
        FragmentReview fragmentReview = new FragmentReview();
        list = new ArrayList<>();
        setFragment(list,fragmentaddd,fragmentReview);
        Intent intent =getIntent();
        String UId = intent.getStringExtra("message_key");
        final androidx.fragment.app
                .FragmentManager mFragmentManager
                = getSupportFragmentManager();
        final androidx.fragment.app
                .FragmentTransaction mFragmentTransaction
                = mFragmentManager.beginTransaction();
        final FragmentAdd fragmentAdd
                = new FragmentAdd();
        Bundle mBundle = new Bundle();
        mBundle.putString("UId", UId);

    }
    public void intiateList(ArrayList<ModelFragment> list,FragmentAdd fragmentaddd,FragmentReview fragmentReview)
    {
        list.add(new ModelFragment(fragmentaddd,R.drawable.baseline_add_24,"Add"));
        list.add(new ModelFragment(fragmentReview,R.drawable.baseline_arrow_outward_24,"Review"));
    }
    public void setFragment(ArrayList<ModelFragment> list,FragmentAdd fragmentaddd,FragmentReview fragmentReview)
    {
        intiateList(list,fragmentaddd,fragmentReview);
        FragmentAdaptor fragmentAdaptor=new FragmentAdaptor(this, list);
        binding.viewpager.setAdapter(fragmentAdaptor);
        new TabLayoutMediator(binding.tab, binding.viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(list.get(position).getTitle());
                tab.setIcon(list.get(position).getImage());
            }
        }).attach();
    }
  
}
