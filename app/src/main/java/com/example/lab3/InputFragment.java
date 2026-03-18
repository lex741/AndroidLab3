package com.example.lab3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InputFragment extends Fragment {

    public interface InputListener {
        void onInputConfirmed(String productType, String company);
        void onOpenRequested();
    }

    private InputListener listener;

    private RadioGroup rgProductType;
    private RadioGroup rgCompany;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof InputListener) {
            listener = (InputListener) context;
        } else {
            throw new RuntimeException("Activity must implement InputListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input, container, false);

        rgProductType = v.findViewById(R.id.rgProductType);
        rgCompany = v.findViewById(R.id.rgCompany);
        Button btnOk = v.findViewById(R.id.btnOk);
        Button btnOpen = v.findViewById(R.id.btnOpen);

        btnOk.setOnClickListener(view -> {
            int typeId = rgProductType.getCheckedRadioButtonId();
            int companyId = rgCompany.getCheckedRadioButtonId();

            if (typeId == -1 || companyId == -1) {
                Toast.makeText(getContext(),
                        "Будь ласка, виберіть тип товару та фірму",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String typeText = ((RadioButton) v.findViewById(typeId)).getText().toString();
            String companyText = ((RadioButton) v.findViewById(companyId)).getText().toString();

            listener.onInputConfirmed(typeText, companyText);
        });

        btnOpen.setOnClickListener(view -> listener.onOpenRequested());

        return v;
    }

    public void clearForm() {
        if (rgProductType != null) rgProductType.clearCheck();
        if (rgCompany != null) rgCompany.clearCheck();
    }
}