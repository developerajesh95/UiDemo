package com.ui.demo.interfaces;

import com.ui.demo.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("/users")
    Call<List<User>> getUsers();

}
