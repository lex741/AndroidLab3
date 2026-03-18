package com.example.lab3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    public interface ResultListener {
        void onResultCanceled();
    }

    private static final String ARG_TEXT = "arg_text";
    private ResultListener listener;

    public static ResultFragment newInstance(String text) {
        ResultFragment f = new ResultFragment();
        Bundle b = new Bundle();
        b.putString(ARG_TEXT, text);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ResultListener) {
            listener = (ResultListener) context;
        } else {
            throw new RuntimeException("Activity must implement ResultListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result, container, false);

        TextView tvResult = v.findViewById(R.id.tvResult);
        Button btnCancel = v.findViewById(R.id.btnCancel);

        String text = (getArguments() != null) ? getArguments().getString(ARG_TEXT) : "";
        tvResult.setText(text);

        btnCancel.setOnClickListener(view -> listener.onResultCanceled());

        return v;
    }
}