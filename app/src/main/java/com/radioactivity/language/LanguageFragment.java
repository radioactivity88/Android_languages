package com.radioactivity.language;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageFragment extends Fragment {

    private final LanguageCallback callback;

    public interface LanguageCallback {
        void onLanguageChanged(String lang);
    }

    public LanguageFragment(LanguageCallback callback) {
        // Required empty public constructor
        this.callback = callback;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        Button selectButton = view.findViewById(R.id.selectLanguage);

        selectButton.setOnClickListener(v -> selectLanguageDialog(getActivity()));

        return view;
    }

    private void selectLanguageDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.select_language_title);
        builder.setSingleChoiceItems(R.array.languages, -1, this::selectLang);
        builder.create().show();
    }

    private void selectLang(DialogInterface dialogInterface, int index) {
        String lang = getResources().getStringArray(R.array.lang_iso)[index];
        dialogInterface.dismiss();
        callback.onLanguageChanged(lang);
    }
}
