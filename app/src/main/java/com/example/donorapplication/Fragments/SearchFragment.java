package com.example.donorapplication.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.donorapplication.Adapters.ExerciseAdapter;
import com.example.donorapplication.Adapters.ProfilePostAdapter;
import com.example.donorapplication.Adapters.ThreadPostAdapter;
import com.example.donorapplication.Adapters.UserSearchAdapter;
import com.example.donorapplication.AppDatabase;
import com.example.donorapplication.Entity.Post;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.R;
import com.example.donorapplication.Utils.TextChangedListener;

import java.util.List;


public class SearchFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    AppDatabase db;

    EditText searchBar;
    private String mParam1;
    private String mParam2;

    RecyclerView peopleRecycler, postRecycler;

    public SearchFragment() {
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(this.getActivity());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        searchBar = (EditText) view.findViewById(R.id.searchBar);
                searchBar.addTextChangedListener(new TextChangedListener<EditText>(searchBar) {
                    @Override
                    public void onTextChanged(EditText target, Editable s) {
                        String searchInput = "%" + String.valueOf(target.getText());
                        List<User> users = db.userDao().searchForUsers(searchInput);
                        peopleRecycler = view.findViewById(R.id.peopleSearchRecycler);
                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                        peopleRecycler.setLayoutManager(layoutManager);
                        UserSearchAdapter userSearchAdapter = new UserSearchAdapter(getContext(), users);
                        peopleRecycler.setAdapter(userSearchAdapter);


                        List<ThreadPost> posts = db.threadPostDAO().searchForThreadPosts(searchInput);
                        postRecycler = view.findViewById(R.id.postSearchRecycler);
                        postRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        ThreadPostAdapter adapter = new ThreadPostAdapter(getContext(), posts);
                        postRecycler.setAdapter(adapter);
                    }
                });
        return view;
    }
}