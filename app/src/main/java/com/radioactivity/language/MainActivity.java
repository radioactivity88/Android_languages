package com.radioactivity.language;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements LanguageFragment.LanguageCallback {
    private static final String LANG_KEY = "lang_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleManager.updateLocale(this, loadLanguage());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crateLangFragment();
    }

    @Override
    public void onLanguageChanged(String lang) {
        saveLanguage(lang);
        LocaleManager.updateLocale(this, lang);
        crateLangFragment();
    }

    private void crateLangFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, new LanguageFragment(this))
                .commit();
    }

    private String loadLanguage() {
        return getPreferences(MODE_PRIVATE).getString(LANG_KEY, "en");
    }

    private void saveLanguage(String lang) {
        getPreferences(MODE_PRIVATE).edit().putString(LANG_KEY, lang).apply();
    }
}
