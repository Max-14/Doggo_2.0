package com.max.doggo.Utils;

import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.google.firebase.database.core.view.View;
import com.max.doggo.Common.Common_Owner;
import com.max.doggo.Common.Common_Walker;
import com.max.doggo.Model.TokenModel;

import java.util.Map;

public class UserUtils {
   /* public static void updateWalker (View view, Map<String, Object> updateData){
        FirebaseDatabase.getInstance().getReference(Common_Walker.WALKER_INFO_REFERENCE)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .updateChildren(updateData).addOnFailureListener(e -> Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_SHORT).show());
    }

    public static void updateOwner (View view, Map<String, Object> updateData){
        FirebaseDatabase.getInstance().getReference(Common_Owner.OWNER_INFO_REFERENCE)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .updateChildren(updateData).addOnFailureListener(e -> Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_SHORT).show());
    }

   /* public static void updateWalkerToken(Context context,String token){
        TokenModel tokenWalkerModel = new TokenModel(token);

        FirebaseDatabase.getInstance().getReference(Common_Walker.TOKEN_REFERENCE)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(tokenWalkerModel)
                .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show()).addOnSuccessListener(aVoid ->;){

        });
    }*/
}
