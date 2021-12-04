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
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.comments.ResponseComments;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.posts.PostsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFragment extends Fragment {

    private RecyclerView rv_response_list;
    private TextView tv_response;
    private Button btn_request;
    private EditText et_request_end;
   private ApiServices apiServices;
    public GetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_get, container, false);
        rv_response_list=v.findViewById(R.id.rv_response_list);
        btn_request=v.findViewById(R.id.btn_request);
        et_request_end=v.findViewById(R.id.et_request_end);
        tv_response=v.findViewById(R.id.tv_response);
        apiServices = ServiceGenerator.createService(ApiServices.class);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String path=et_request_end.getText().toString().trim();
                Toast.makeText(getContext(), path, Toast.LENGTH_SHORT).show();
                if(!path.isEmpty()){
                    if (path.equals("/posts")){
                        getPostsRequest(path);
                    }


//                    else if (path.contains("comments"))
//                        getCommentsRequest(path);
//
//                    else getSinglePostRequest(path);
                }else
                {
                    Toast.makeText(getContext(), "end url empty!", Toast.LENGTH_SHORT).show();
                }



            }
        });




        return  v;
    }

    private void getSinglePostRequest(String path) {
        Call<PostsModel> call=apiServices.getSinglePosts(path);
        call.enqueue(new Callback<PostsModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<PostsModel> call, Response<PostsModel> response) {
                rv_response_list.setVisibility(View.GONE);
                tv_response.setVisibility(View.VISIBLE);
                try{

                    assert response.body() != null;
                    tv_response.setText(
                            "ID => "+ response.body().getId() +"\n"
                                    +"User ID =>"+ response.body().getUserId() +"\n"
                                    +"Title => " + response.body().getTitle() +"\n"
                                    +"Body => " + response.body().getBody() +"\n"
                    );

                }catch (Exception ignore){
                    Log.e("TAG", String.valueOf(ignore));
                }
            }

            @Override
            public void onFailure(Call<PostsModel> call, Throwable t) {
                Log.e("TAG", String.valueOf(t));
            }
        });
    }

    private void getCommentsRequest(String path) {
        Call<List<ResponseComments>> call=apiServices.getComments(path);
        call.enqueue(new Callback<List<ResponseComments>>() {
            @Override
            public void onResponse(Call<List<ResponseComments>> call, Response<List<ResponseComments>> response) {
                try{
                    setRecyclerViewForCommentsList(response.body());

                }catch (Exception ignore){
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
        Log.e("TAG", "path"+path);
        Call<List<PostsModel>> call=apiServices.getPostsList("/posts");

        call.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {

                try{
                    if(response.body() !=null)
                   // setRecyclerView(response.body());
                    Log.e("TAG", String.valueOf(response.body()));

                }catch (Exception ignore){
                    Log.e("TAG", String.valueOf(ignore));
                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Log.e("TAG", String.valueOf(t));
            }
        });

    }

    private void setRecyclerView(List<PostsModel> _list) {
        rv_response_list.setVisibility(View.VISIBLE);
        tv_response.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_response_list.setLayoutManager(linearLayoutManager);
        PostsAdapter adapter=new PostsAdapter(_list, getContext());
        rv_response_list.setAdapter(adapter);
    }
    private void setRecyclerViewForCommentsList(List<ResponseComments> list) {
        rv_response_list.setVisibility(View.VISIBLE);
        tv_response.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_response_list.setLayoutManager(linearLayoutManager);
        CommentsAdapter adapter=new CommentsAdapter(list, getContext());
        rv_response_list.setAdapter(adapter);
    }
}