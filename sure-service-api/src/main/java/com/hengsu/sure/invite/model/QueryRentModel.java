package com.hengsu.sure.invite.model;

import com.hengsu.sure.auth.model.UserModel;

/**
 * Created by haiquanli on 15/12/27.
 */
public class QueryRentModel {

    private RentModel rent;
    private UserModel user;

    public RentModel getRent() {
        return rent;
    }

    public void setRent(RentModel rent) {
        this.rent = rent;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
