package com.hipradeep.rest_api_example.using_retrofit.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hipradeep.rest_api_example.R;
import com.hipradeep.rest_api_example.using_retrofit.adapters.CommentsAdapter;
import com.hipradeep.rest_api_example.using_retrofit.adapters.PostsAdapter;
import com.hipradeep.rest_api_example.using_retrofit.services.ApiServices;
import com.hipradeep.rest_api_example.using_retrofit.services.ServiceGenerator;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponseComments;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponsePosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFragment extends Fragment {

    private RecyclerView rv_response_list;
    private TextView tv_response, tv_response_data;
    private Button btn_request;
    private EditText et_request_end;
    private ApiServices apiServices;

    public GetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiServices = ServiceGenerator.createService(ApiServices.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_get, container, false);
        rv_response_list = v.findViewById(R.id.rv_response_list);
        btn_request = v.findViewById(R.id.btn_request);
        et_request_end = v.findViewById(R.id.et_request_end);
        tv_response = v.findViewById(R.id.tv_response);
        tv_response_data = v.findViewById(R.id.tv_response_data);

        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String path = et_request_end.getText().toString().trim();

                if (!path.isEmpty()) {
                    if (path.contains("/comments") || path.contains("posts/")) {
                        if (path.equals("posts/")) {
                            getPostsRequest(path);
                        } else if (path.contains("/comments")) {
                            getCommentsRequest(getIds(path));
                        } else if (path.length() > 6 && path.length() < 10) {
                            getSinglePostRequest(getIds(path));
                        } else {
                            Toast.makeText(getContext(), "Please enter correct url!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Please enter correct url!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "end url empty!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return v;
    }

    private String getIds(String path) {

        String[] parts = path.split("/");
        String part1 = parts[0]; // posts
        String part2 = parts[1]; // 1
        //String part3 = parts[2]; // comments
        Log.e("TAG", "ID : " + part2);
        return part2;


    }

    private void getSinglePostRequest(String id) {
        Log.e("TAG", String.valueOf(id));
        Call<ResponsePosts> call = apiServices.getSinglePosts(id);
        call.enqueue(new Callback<ResponsePosts>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponsePosts> call, Response<ResponsePosts> response) {
                rv_response_list.setVisibility(View.GONE);
                tv_response_data.setVisibility(View.VISIBLE);
                try {
                    Log.e("TAG", String.valueOf(response.body()));
                    assert response.body() != null;

                    tv_response_data.setText(
                            "ID => " + response.body().getId() + "\n"
                                    + "User ID =>" + response.body().getUserId() + "\n"
                                    + "Title => " + response.body().getTitle() + "\n"
                                    + "Body => " + response.body().getBody() + "\n"
                    );

                } catch (Exception e) {
                    Log.e("TAG", String.valueOf(e));
                }
            }

            @Override
            public void onFailure(Call<ResponsePosts> call, Throwable t) {
                Log.e("TAG", String.valueOf(t));
            }
        });
    }

    private void getCommentsRequest(String id) {

        Call<List<ResponseComments>> call = apiServices.getComments(id);
        call.enqueue(new Callback<List<ResponseComments>>() {
            @Override
            public void onResponse(Call<List<ResponseComments>> call, Response<List<ResponseComments>> response) {
                try {
                    setRecyclerViewForCommentsList(response.body());

                } catch (Exception ignore) {
                    Log.e("TAG", String.valueOf(ignore));
                }
            }

            @Override
            public void onFailure(Call<List<ResponseComments>> call, Throwable t) {
                Log.e("TAG", String.valueOf(t));
            }
        });
    }


    private void getPostsRequest(String path) {
        Log.e("TAG", "path" + path);
        Call<List<ResponsePosts>> call = apiServices.getPostsList();

        call.enqueue(new Callback<List<ResponsePosts>>() {
            @Override
            public void onResponse(Call<List<ResponsePosts>> call, Response<List<ResponsePosts>> response) {

                try {
                    if (response.body() != null)
                        setRecyclerView(response.body());
                    Log.e("TAG", String.valueOf(response.body()));

                } catch (Exception ignore) {
                    Log.e("TAG", String.valueOf(ignore));
                }
            }

            @Override
            public void onFailure(Call<List<ResponsePosts>> call, Throwable t) {
                Log.e("TAG", String.valueOf(t));
            }
        });

    }

    private void setRecyclerView(List<ResponsePosts> _list) {
        rv_response_list.setVisibility(View.VISIBLE);
        tv_response_data.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_response_list.setLayoutManager(linearLayoutManager);
        if (_list.size() > 0) {
            PostsAdapter adapter = new PostsAdapter(_list, getContext());
            rv_response_list.setAdapter(adapter);
        } else {
            tv_response.append(" null");
        }
    }

    private void setRecyclerViewForCommentsList(List<ResponseComments> _list) {
        rv_response_list.setVisibility(View.VISIBLE);
        tv_response_data.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_response_list.setLayoutManager(linearLayoutManager);
        if (_list.size() > 0) {
            CommentsAdapter adapter = new CommentsAdapter(_list, getContext());
            rv_response_list.setAdapter(adapter);
        } else {
            tv_response.append(" null");
        }
    }
}