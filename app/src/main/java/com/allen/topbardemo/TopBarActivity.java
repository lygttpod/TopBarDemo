package com.allen.topbardemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.allen.topbardemo.view.TopBar;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

/**
 * Created by Allen on 2016/4/11.
 */
public class TopBarActivity extends AppCompatActivity {
    private EditText input_content;
    private Button creatQrcode;
    private ImageView showQrcodeImg;

    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topbar_layout);

        topBar = (TopBar) findViewById(R.id.topbar_qr);
        input_content = (EditText) findViewById(R.id.input_content_et);
        creatQrcode = (Button) findViewById(R.id.create_QRcode_btn);
        showQrcodeImg = (ImageView) findViewById(R.id.show_qrcode_img);

        topBar.setTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void LeftIconClickListener() {

                finish();
            }

            @Override
            public void rightIconClickListener() {
                startActivityForResult(new Intent(TopBarActivity.this, CaptureActivity.class), 1);
            }

            @Override
            public void rightIconClickListener2() {

            }
        });
        topBar.setCenterText("二维码工具").setCenterTextSize(18);
        creatQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_content_string = input_content.getText().toString().trim();
                creatQrcode(input_content_string);
            }
        });
    }


    private void creatQrcode(String input_content_string) {
        Bitmap bitmap_qr = EncodingUtils.createQRCode(input_content_string, 1000, 1000, null);
        showQrcodeImg.setImageBitmap(bitmap_qr);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String qrString = data.getStringExtra("result");
                    input_content.setText(qrString);
                }
                break;
        }
    }
}
