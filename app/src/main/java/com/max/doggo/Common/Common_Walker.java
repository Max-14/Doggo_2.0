package com.max.doggo.Common;

import com.max.doggo.Model.OwnerModel;
import com.max.doggo.Model.WalkerModel;

public class Common_Walker {
    public static final String WALKER_INFO_REFERENCE = "Walker";
    public static final String TOKEN_REFERENCE = "Token";
    public static WalkerModel currentWalker;

    public static String buildwelcomeMessage() {
        if (Common_Walker.currentWalker != null) {
            return new StringBuilder("Welcome")
                    .append(Common_Walker.currentWalker.getName()).toString();
        } else return "";
    }
}
