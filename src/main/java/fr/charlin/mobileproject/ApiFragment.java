package fr.charlin.mobileproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class ApiFragment extends Fragment implements UsersAdapter.ClickedItem {
    UsersAdapter usersAdapter;
    RecyclerView recyclerView;
    public ApiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_api, container, false);
        usersAdapter = new UsersAdapter(this::ClickedUser);
        androidx.appcompat.widget.Toolbar tool = view.findViewById(R.id.toolbar);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        getAllUsers();



        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void getAllUsers(){
        Call<List<User>> userList = ApiClient.getApiService().getAllUsers();
        userList.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    List<User> users = response.body();
                    usersAdapter.setData(users);

                    recyclerView.setAdapter(usersAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void ClickedUser(User user) {
        startActivity(new Intent(this.getContext(),UsersDetailsActivity.class).putExtra("data", user));
    }
}
