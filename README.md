# Sample
<img src="screenshort/demo.gif">

# 使用
- 将library作为一个Module导入AS工程,build.gradle文件里添加依赖 compile project(':library') 即可
- sample为一个demo
#
    dependencies {
           compile project(':library')
    }

### 打开Activity扫描二维码
    Intent intent = new Intent(this, CaptureActivity.class);
    startActivityForResult(intent,0);
    
### 重写onActivityResult获取扫描后的数据
    //处理扫描的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            String type = bundle.getString("type");
            Log.i("TAG", "type: " + type + "\n" + "result: " + result);
        }
    }
    
### 生成二维码
      Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.icon);
      Bitmap bitmap = EncodingUtils.createQRCode(content, 500, 500,logo);
      imageView.setImageBitmap(bitmap);
