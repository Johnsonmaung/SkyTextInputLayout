package com.johnsonmaung.skytextinputlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
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

  int error_color;
  String error = "";

  String hint = "";
  String text = "";
  int inputType = -1;
  int imeOptions = -1;
  InputFilter[] inputFilters = null;
  int maxLines = -1;

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

  private void init(AttributeSet attrs) {
    if (!isInEditMode()) {
      TypedArray typedArray =
          getContext().obtainStyledAttributes(attrs, R.styleable.SkyTextInputLayout);
      try {

        hint = typedArray.getString(R.styleable.SkyTextInputLayout_android_hint);
        text = typedArray.getString(R.styleable.SkyTextInputLayout_android_text);

        error_color = typedArray.getColor(R.styleable.SkyTextInputLayout_textColorError,
            ContextCompat.getColor(getContext(), R.color.error_color));
        error = typedArray.getString(R.styleable.SkyTextInputLayout_error);

        if (typedArray.hasValue(R.styleable.SkyTextInputLayout_android_inputType)) {
          inputType = (typedArray.getInt(R.styleable.SkyTextInputLayout_android_inputType, -1));
        }

        if (typedArray.hasValue(R.styleable.SkyTextInputLayout_android_imeOptions)) {
          imeOptions = typedArray.getInt(R.styleable.SkyTextInputLayout_android_imeOptions, -1);
        }

        if (typedArray.hasValue(R.styleable.SkyTextInputLayout_android_maxLength)) {
          inputFilters = new InputFilter[] {
              new InputFilter.LengthFilter(
                  typedArray.getInt(R.styleable.SkyTextInputLayout_android_maxLength, 0))
          };
        }

        if (typedArray.hasValue(R.styleable.SkyTextInputLayout_android_maxLines)) {
          maxLines = typedArray.getInt(R.styleable.SkyTextInputLayout_android_maxLines, 1);
        }
      } finally {
        typedArray.recycle();
      }
    }
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    inflate(getContext(), R.layout.skytextinputlayout, this);
    tv = findViewById(R.id.tv);
    edt = findViewById(R.id.edt);
    v = findViewById(R.id.v);

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

    tv.setText(hint);

    edt.setHint(hint);
    if (inputType != -1) edt.setInputType(inputType);
    if (imeOptions != -1) edt.setImeOptions(imeOptions);
    if (maxLines != -1) edt.setMaxLines(maxLines);
    if (inputFilters != null) edt.setFilters(inputFilters);
    edt.setText(text);
    edt.setSelection(edt.getText().length());
  }

  public String getHint() {
    return hint;
  }

  public void setHint(String hint) {
    this.hint = hint;
    edt.setHint(hint);
  }

  public void showError(String error_text) {
    if (!TextUtils.isEmpty(edt.getText().toString())) {
      tv.setVisibility(VISIBLE);
      tv.setTextColor(error_color);
      if ((error_text != null) && (!TextUtils.isEmpty(error_text))) {
        tv.setText(error_text);
      } else {
        tv.setText(this.error);
      }
    }
  }

  public void showError() {
    if (!TextUtils.isEmpty(edt.getText().toString())) {
      tv.setVisibility(VISIBLE);
      tv.setTextColor(error_color);
      if (!TextUtils.isEmpty(error)) {
        tv.setText(error);
      }
    }
  }

  public void hideError() {
    if (TextUtils.isEmpty(edt.getText().toString())) {
      tv.setVisibility(INVISIBLE);
    } else {
      tv.setVisibility(VISIBLE);
    }
    tv.setTextColor(Color.GRAY);
    tv.setText(hint);
  }

  public void setText(String text) {
    this.text = text;
    edt.setText(text);
    edt.setSelection(edt.getText().length());
  }

  public String getText() {
    return edt.getText().toString();
  }

  public void setError(String error) {
    this.error = error;
    tv.setText(error);
  }

  public String getError() {
    return error;
  }

  public void setInputType(int inputType) {
    this.inputType = inputType;
    edt.setInputType(inputType);
  }

  public int getInputType() {
    return edt.getInputType();
  }
}
