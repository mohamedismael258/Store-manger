package com.example.storemanger;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.storemanger.databinding.FragmentAddBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentAdd extends Fragment {
 FragmentAddBinding binding;
Const aConst=new Const();
 DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
 String UId="";

 @Nullable
 @Override
 public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
binding=FragmentAddBinding.inflate(inflater);
View view =binding.getRoot();
  Bundle bundle = getArguments();
  UId = bundle.getString("UId");

return view ;
 }

 @Override
 public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
  super.onViewCreated(view, savedInstanceState);
  binding=FragmentAddBinding.bind(view);

  binding.addimage.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    pickMedia.launch(new PickVisualMediaRequest.Builder()
            .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
            .build());
            sendData(UId);
   }
  });

 }
public void sendData(String UId)
{

 ref.child(aConst.KEY_USERS).child(UId).setValue(creatNewData());
}
public ModelProuduct creatNewData()
{
 String assetNAme=binding.EnterAsset.getText().toString().trim();
 String assetPrice=binding.EnterPrice.getText().toString().trim();
 Integer assetImage=binding.imageview.getImageAlpha();
 return new ModelProuduct(assetNAme,assetPrice,assetImage);
}
 ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
         registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
          // Callback is invoked after the user selects a media item or closes the
          // photo picker.
          if (uri != null) {
           Log.d("PhotoPicker", "Selected URI: " + uri);
        binding.addimage.setImageURI(uri);
          } else {
           Log.d("PhotoPicker", "No media selected");
          }
         });

}
