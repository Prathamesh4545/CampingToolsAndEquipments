package com.example.campingtoolsandequipment.ui.person;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.campingtoolsandequipment.Models.Users;
import com.example.campingtoolsandequipment.R;
import com.example.campingtoolsandequipment.databinding.FragmentPersonBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonFragment extends Fragment {
    private FragmentPersonBinding binding;

    CircleImageView profile_pic;
    EditText name,email,number,address;
    Button update;

    FirebaseStorage storage;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonViewModel personViewModel =
                new ViewModelProvider(this).get(PersonViewModel.class);

        View root = inflater.inflate(R.layout.fragment_person,container,false);

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        profile_pic = root.findViewById(R.id.profile_pic);
        name = root.findViewById(R.id.profile_name);
        email = root.findViewById(R.id.profile_email);
        number = root.findViewById(R.id.profile_phoneNo);
        address = root.findViewById(R.id.profile_Address);
        update= root.findViewById(R.id.profile_update);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users users = snapshot.getValue(Users.class);
                        Glide.with(getContext()).load(users.getProfile_pic()).into(profile_pic);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
            }
        });

        return root;
    }

    private void updateUserProfile() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.getData() != null){
            Uri profileUri = data.getData();
            profile_pic.setImageURI(profileUri);
            
            final StorageReference reference = storage.getReference().child("Profile Picture")
                    .child(FirebaseAuth.getInstance().getUid());
            
            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profile_pic").setValue(uri.toString());
                            Toast.makeText(getContext(), "Profile Picture Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}