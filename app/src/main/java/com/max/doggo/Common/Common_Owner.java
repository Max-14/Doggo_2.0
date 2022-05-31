package com.max.doggo.Common;

import com.max.doggo.Model.OwnerModel;

public class Common_Owner {
    public static final String OWNER_INFO_REFERENCE = "Owners";
    public static final String TOKEN_REFERENCE = "Tokens";
    public static OwnerModel currentOwner;

    public static String buildwelcomeMessage(){
        if(Common_Owner.currentOwner != null){
            return new StringBuilder("Welcome")
                    .append(Common_Owner.currentOwner.getName()).toString();
        }else return "";
    }
}
