package com.johnsonmaung.skytextinputlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by johnsonmaung on 10/28/17.
 */

public class SkyTextInputLayout extends LinearLayout {

  TextView tv;
  EditText edt;
  View v;

  String hint_text = "";
  String text = "";
  int inputType;
  int error_color;
  String error_text = "";

  public enum InputType {
    text(0), number(1);
    int id;

    InputType(int id) {
      this.id = id;
    }

    static InputType fromId(int id) {
      for (InputType f : values()) {
        if (f.id == id) return f;
      }
      throw new IllegalArgumentException();
    }
  }

  public SkyTextInputLayout(Context context) {
    super(context);
  }

  public SkyTextInputLayout(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  public SkyTextInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    inflate(getContext(), R.layout.skytextinputlayout, this);
    tv = findViewById(R.id.tv);
    edt = findViewById(R.id.edt);
    v = findViewById(R.id.v);

    tv.setText(hint_text);
    edt.setHint(hint_text);
    edt.setText(text);
    switch (inputType) {

      case 0:

        break;

      default:

        break;
    }

    //edt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

    edt.setOnFocusChangeListener(new OnFocusChangeListener() {
      @Override public void onFocusChange(View view, boolean b) {
        if (view.getId() == R.id.edt) {
          if (hasFocus()) {
            if (TextUtils.isEmpty(edt.getText().toString())) {
              tv.setVisibility(View.INVISIBLE);
            } else {
              tv.setVisibility(View.VISIBLE);
            }
          } else {

          }
        }
      }
    });

    edt.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = charSequence.toString();
        tv.setTextColor(Color.GRAY);
        tv.setText(edt.getHint());

        if (TextUtils.isEmpty(text)) {
          tv.setVisibility(View.INVISIBLE);
        } else {
          tv.setVisibility(View.VISIBLE);
        }
      }

      @Override public void afterTextChanged(Editable editable) {

      }
    });
  }

  private void init(AttributeSet attrs) {
    if (!isInEditMode()) {
      TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SkyTextInputLayout);
      try {
        hint_text = a.getString(R.styleable.SkyTextInputLayout_hint);
        text = a.getString(R.styleable.SkyTextInputLayout_text);
        error_color = a.getColor(R.styleable.SkyTextInputLayout_error_color, Color.RED);
        error_text = a.getString(R.styleable.SkyTextInputLayout_error_text);
        inputType = a.getInt(R.styleable.SkyTextInputLayout_inputType, 0);
      } finally {
        a.recycle();
      }
    }
  }

  public void setError(String error) {
    tv.setVisibility(VISIBLE);
    tv.setTextColor(error_color);
    if (error.equals(null)) {
      tv.setText(error_text);
    } else {
      tv.setText(error);
    }
    invalidate();
    requestLayout();
  }

  public void hideError() {
    tv.setVisibility(INVISIBLE);
    tv.setTextColor(Color.GRAY);
    tv.setText(edt.getHint());
    invalidate();
    requestLayout();
  }
}
