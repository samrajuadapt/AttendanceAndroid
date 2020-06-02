package com.adapt.attendance.utils;

import android.util.Patterns;
import android.widget.EditText;


import com.adapt.attendance.R;

import java.util.regex.Pattern;

/**
 * Created by Hari Group Unity on 16-02-2018.
 */

public class Validation {

    private static final Pattern specialChars = Pattern.compile("[$=\\\\|/<>^*%]");


    public static boolean checkIsEmpty(EditText... editTexts) {
        boolean isEmpty = false;

        for (EditText e : editTexts) {
            if (e.getText().toString().trim().isEmpty()) {
                isEmpty = true;
                e.setError(e.getResources().getString(R.string.err_empty));
            } else {
                e.setError(null);
            }
        }
        return isEmpty;
    }

}
