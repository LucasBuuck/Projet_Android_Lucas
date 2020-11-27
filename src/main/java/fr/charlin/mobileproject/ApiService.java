package fr.charlin.mobileproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/users")
    Call<List<User>> getAllUsers();
}
