package com.johnsonmaung.skytextinputlayout.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.johnsonmaung.skytextinputlayout.SkyTextInputLayout;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.stil) SkyTextInputLayout stil;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn) public void test() {
    stil.setError("Lol");
  }
}
