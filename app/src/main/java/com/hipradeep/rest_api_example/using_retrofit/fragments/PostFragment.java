package com.hipradeep.rest_api_example.using_retrofit.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hipradeep.rest_api_example.R;
import com.hipradeep.rest_api_example.using_retrofit.services.ApiServices;
import com.hipradeep.rest_api_example.using_retrofit.services.ServiceGenerator;
import com.hipradeep.rest_api_example.using_retrofit.services.models.requests.RequestCreatePost;
import com.hipradeep.rest_api_example.using_retrofit.services.models.requests.RequestPostUpdate;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponseDeletePost;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponsePostCreated;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponsePostUpdated;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostFragment extends Fragment implements View.OnClickListener {

    private ApiServices apiServices;
    EditText et_post_id, et_user_id, et_title, et_body;
    private TextView tv_response, tv_response_data, textView6;
    private Button btn_request;
    private Spinner spinner_choose_methods;
    private String method;

    public PostFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiServices = ServiceGenerator.createService(ApiServices.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_post, container, false);
        btn_request = v.findViewById(R.id.btn_request);
        textView6 = v.findViewById(R.id.textView6);
        spinner_choose_methods = v.findViewById(R.id.spinner_choose_methods);
        tv_response = v.findViewById(R.id.tv_response);
        tv_response_data = v.findViewById(R.id.tv_response_data);
        et_post_id = v.findViewById(R.id.et_1);
        et_user_id = v.findViewById(R.id.et_2);
        et_title = v.findViewById(R.id.et_3);
        et_body = v.findViewById(R.id.et_4);
        btn_request.setOnClickListener(this);

        setSpinner();
        postIdEnter();

        return v;
    }

    private void createNewPost() {

        RequestCreatePost requestCreatePost = new RequestCreatePost();
        requestCreatePost.setUserId(et_user_id.getText().toString().trim());
        requestCreatePost.setTitle(et_title.getText().toString().trim());
        requestCreatePost.setBody(et_body.getText().toString().trim());

        Call<ResponsePostCreated> call = apiServices.createPost(requestCreatePost);
        call.enqueue(new Callback<ResponsePostCreated>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponsePostCreated> call, Response<ResponsePostCreated> response) {
                tv_response_data.setVisibility(View.VISIBLE);
                try {
                    if (response.body() != null)

                        Log.e("TAG", String.valueOf(response.body()));

                    tv_response_data.setText(
                                      "ID => " + response.body().getId() + "\n"
                                    + "User ID =>" + response.body().getUserId() + "\n"
                                    + "Title => " + response.body().getTitle() + "\n"
                                    + "Body => " + response.body().getBody() + "\n"
                    );

                } catch (Exception ignore) {
                    Log.e("TAG", String.valueOf(ignore));
                }
            }

            @Override
            public void onFailure(Call<ResponsePostCreated> call, Throwable t) {
                Log.e("TAG", String.valueOf(t));
            }
        });


    }

    private void updatePostRequest() {
        RequestPostUpdate requestPostUpdate = new RequestPostUpdate();
        requestPostUpdate.setUserId(et_user_id.getText().toString().trim());
        requestPostUpdate.setUserId(et_post_id.getText().toString().trim());
        requestPostUpdate.setTitle(et_title.getText().toString().trim());
        requestPostUpdate.setBody(et_body.getText().toString().trim());
        Call<ResponsePostUpdated> call=apiServices.updatePost(requestPostUpdate, et_post_id.getText().toString().trim());
        call.enqueue(new Callback<ResponsePostUpdated>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponsePostUpdated> call, Response<ResponsePostUpdated> response) {
                tv_response_data.setVisibility(View.VISIBLE);
                try {
                    if (response.body() != null)

                        Log.e("TAG", String.valueOf(response.body()));

                    tv_response_data.setText(
                                    "ID => " + response.body().getId() + "\n"
                                    + "User ID =>" + response.body().getUserId() + "\n"
                                    + "Title => " + response.body().getTitle() + "\n"
                                    + "Body => " + response.body().getBody() + "\n"
                    );

                } catch (Exception ignore) {
                    Log.e("TAG", String.valueOf(ignore));
                }
            }

            @Override
            public void onFailure(Call<ResponsePostUpdated> call, Throwable t) {
                Log.e("TAG", String.valueOf(t));
            }
        });
    }

    private void deletePostRequest() {

        Call<ResponseDeletePost> call=apiServices.deletePost(et_post_id.getText().toString().trim());
        call.enqueue(new Callback<ResponseDeletePost>() {
            @Override
            public void onResponse(Call<ResponseDeletePost> call, Response<ResponseDeletePost> response) {
                //tv_response_data.setVisibility(View.VISIBLE);
                try {
                    if (response.body() != null)

                        Log.e("TAG", String.valueOf(response.body()));

                    Toast.makeText(getContext(), "Post Deleted!", Toast.LENGTH_SHORT).show();

                } catch (Exception ignore) {
                    Log.e("TAG", String.valueOf(ignore));
                }
            }

            @Override
            public void onFailure(Call<ResponseDeletePost> call, Throwable t) {
                Log.e("TAG", String.valueOf(t));
            }
        });
    }

    private boolean validation() {
        String et1 = et_post_id.getText().toString().trim();
        String et2 = et_user_id.getText().toString().trim();
        String et3 = et_title.getText().toString().trim();
        String et4 = et_body.getText().toString().trim();

        if (et1.isEmpty() && et_post_id.getVisibility() == View.VISIBLE) {
            Toast.makeText(getContext(), "PostId is Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et2.isEmpty() && et_user_id.getVisibility() == View.VISIBLE) {
            Toast.makeText(getContext(), "UserId is Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et3.isEmpty() && et_title.getVisibility() == View.VISIBLE) {
            Toast.makeText(getContext(), "Title is Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et4.isEmpty() && et_body.getVisibility() == View.VISIBLE) {
            Toast.makeText(getContext(), "Body is Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setSpinner() {
        spinner_choose_methods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                method = (String) parent.getItemAtPosition(position).toString().toLowerCase();
                switch (method) {
                    case "post":
                        et_post_id.setVisibility(View.GONE);
                        et_user_id.setVisibility(View.VISIBLE);
                        et_title.setVisibility(View.VISIBLE);
                        et_body.setVisibility(View.VISIBLE);
                        break;
                    case "delete":
                        et_post_id.setVisibility(View.VISIBLE);
                        et_user_id.setVisibility(View.GONE);
                        et_title.setVisibility(View.GONE);
                        et_body.setVisibility(View.GONE);
                        break;
                    case "put":
                        et_post_id.setVisibility(View.VISIBLE);
                        et_user_id.setVisibility(View.VISIBLE);
                        et_title.setVisibility(View.VISIBLE);
                        et_body.setVisibility(View.VISIBLE);
                        break;

                    default:
                        Toast.makeText(getContext(), method, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (method) {
            case "post":
                if (validation()) createNewPost();
                break;
            case "delete":
                if (validation()) deletePostRequest();
                break;
            case "put":
                if (validation()) updatePostRequest();
                break;

            default:
                Toast.makeText(getContext(), method, Toast.LENGTH_SHORT).show();
                break;
        }

    }




    private void postIdEnter() {
        final String s1=textView6.getText().toString();
        et_post_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textView6.setText(s1+et_post_id.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}