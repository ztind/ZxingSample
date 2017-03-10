package com.zt.zxingsample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

/**
 * 基于Google Zxing进行瘦身后的Zxing二维码库
 * 功能: 1,实现二维码的扫描
 *       2，根据用户输入的文本/url生成一张 二维码
 *       3，生成带logo的二维码
 */
public class MainActivity extends Activity implements View.OnClickListener{
    private Button mBtnScanQr,mBtnProductQr;
    private TextView mTvResult;
    private EditText mEtContent;
    private ImageView mImageView;
    private CheckBox mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        mBtnScanQr = (Button) findViewById(R.id.btn_scan_qrCode);
        mBtnProductQr = (Button) findViewById(R.id.btn_product_qrCode);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mEtContent = (EditText) findViewById(R.id.et_content);
        mImageView = (ImageView) findViewById(R.id.iv_image);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
        mBtnScanQr.setOnClickListener(this);
        mBtnProductQr.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_scan_qrCode://打开activity扫描二维码
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.btn_product_qrCode://生成二维码
                String content = mEtContent.getText().toString();
                if(TextUtils.isEmpty(content)){
                    Toast.makeText(this, "请输入要存储的信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                //生成Bitmap二维码
                Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.icon);
                Bitmap bitmap = EncodingUtils.createQRCode(content, 500, 500, mCheckBox.isChecked() ? logo : null);
                mImageView.setImageBitmap(bitmap);

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEtContent.getWindowToken(),0);
                break;
        }
    }

    //处理扫描的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            String type = bundle.getString("type");
            mTvResult.setText( "type: " + type+"\n"+"result: " + result);

            //在此可以根据扫描后的数据type做相应的业务处理
            switch (type){
                case "ADDRESSBOOK":
                    break;
                case "EMAIL_ADDRESS":
                    break;
                case "PRODUCT":
                    break;
                case "URI":
                    break;
                case "TEXT":
                    break;
                case "GEO":
                    break;
                case "TEL":
                    break;
                case "SMS":
                    break;
                case "CALENDAR":
                    break;
                case "WIFI":
                    break;
                case "ISBN":
                    break;
                case "VIN":
                    break;
            }
        }
    }
}
