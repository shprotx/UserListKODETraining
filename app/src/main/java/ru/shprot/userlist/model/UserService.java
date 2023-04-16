package ru.shprot.userlist.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import ru.shprot.userlist.model.User;

public interface UserService {
    @Headers({"Content-Type: application/json",
            "Prefer: code=200, example=success"})
    @GET("users")
    Call<UserList> getUsers();
}
