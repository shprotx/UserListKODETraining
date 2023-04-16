package ru.shprot.userlist.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    public UserService getInstance() {
        return new Retrofit.Builder()
                .baseUrl("https://stoplight.io/mocks/kode-education/trainee-test/25143926/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);
    }

}
