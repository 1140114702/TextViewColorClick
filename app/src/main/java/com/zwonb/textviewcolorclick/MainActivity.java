package com.zwonb.textviewcolorclick;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String contentStr = "Google 为中国开发者提供的资源\n" +
                "developers.google.cn/china\n" +
                "无论您的产品是针对国内还是面向国际市场，您都可以在这里找到最相关的 Google 产品和技术资源。\n" +
                "了解详情";

        String colourStr = "developers.google.cn/china,Google 产品,技术资源,了解详情";

        getSpannableString((TextView) findViewById(R.id.text_view), contentStr, colourStr);

    }

    private void getSpannableString(TextView textView, String contentStr, String colourStr) {

        int end = 0;

        final String[] split = colourStr.split(",");

        final SpannableString spannableString = new SpannableString(contentStr);

        for (int i = 0; i < split.length; i++) {

            int start = contentStr.indexOf(split[i], end);

            //字符串包含需要着色点击的字体
            if (start != -1) {

                end = start + split[i].length();

                final int finalI = i;

                spannableString.setSpan(new ClickableSpan() {
                                            @Override
                                            public void onClick(View widget) {
                                                Toast.makeText(MainActivity.this, split[finalI], Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void updateDrawState(TextPaint ds) {
                                                super.updateDrawState(ds);
                                                ds.setColor(0xff0285cf);//设置颜色
                                                ds.setUnderlineText(false);//去除下划线
                                            }
                                        },
                        start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

        }


        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance()); //设置上可以点击
        textView.setHighlightColor(Color.TRANSPARENT); //点击时透明

    }
}
