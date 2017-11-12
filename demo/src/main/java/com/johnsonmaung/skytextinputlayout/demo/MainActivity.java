package com.johnsonmaung.skytextinputlayout.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
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
    //stil.addTextChangedListener(new );
  }

  @OnClick(R.id.btn) public void test() {
    //stil.showError("Lol");
    Toast.makeText(this, stil.getText(), Toast.LENGTH_SHORT).show();
  }

  @OnClick(R.id.btnSet) public void set() {
    stil.setText("123");
  }

  @OnClick(R.id.btnError) public void error() {
    stil.showError();
  }

  @OnClick(R.id.btnHide) public void hide() {
    stil.hideError();
  }
}
