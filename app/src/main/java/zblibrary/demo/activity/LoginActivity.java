/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package zblibrary.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.zxing.encoding.EncodingHandler;

import java.io.File;

import zblibrary.demo.DEMO.DemoMainActivity;
import zblibrary.demo.R;
import zblibrary.demo.application.DemoApplication;
import zblibrary.demo.util.Constant;
import zblibrary.demo.util.HttpRequest;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.ui.WebViewActivity;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.DownloadUtil;
import zuo.biao.library.util.SettingUtil;

/**关于界面
 * @author Lemon
 */
public class LoginActivity extends BaseActivity implements OnClickListener, OnLongClickListener, OnBottomDragListener {
    private static final String TAG = "LoginActivity";

    //启动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    /**启动这个Activity的Intent
     * @param context
     * @return
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, AboutActivity.class);
    }

    //启动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity, this);

        //功能归类分区方法，必须调用<<<<<<<<<<
        initView();
        initData();
        initEvent();
        //功能归类分区方法，必须调用>>>>>>>>>>
    }

    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initView() {

    }


    //UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initData() {

    }


    private Bitmap qRCodeBitmap;
    /**显示二维码
     */
    protected void setQRCode() {
        runThread(TAG + "setQRCode", new Runnable() {

            @Override
            public void run() {

                try {
                    qRCodeBitmap = EncodingHandler.createQRCode(Constant.APP_DOWNLOAD_WEBSITE
                            , (int) (2 * getResources().getDimension(R.dimen.qrcode_size)));
                } catch (WriterException e) {
                    e.printStackTrace();
                    Log.e(TAG, "initData  try {Bitmap qrcode = EncodingHandler.createQRCode(contactJson, ivContactQRCodeCode.getWidth());" +
                            " >> } catch (WriterException e) {" + e.getMessage());
                }

                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //ivAboutQRCode.setImageBitmap(qRCodeBitmap);
                    }
                });
            }
        });
    }

    /**下载应用
     */
    private void downloadApp() {
        showProgressDialog("正在下载...");
        runThread(TAG + "downloadApp", new Runnable() {
            @Override
            public void run() {
                File file = DownloadUtil.downLoadFile(context, "ZBLibraryDemo", ".apk", Constant.APP_DOWNLOAD_WEBSITE);
                dismissProgressDialog();
                DownloadUtil.openFile(context, file);
            }
        });
    }

    //Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








    //Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void initEvent() {
        findViewById(R.id.login_button).setOnClickListener(this);
    }


    @Override
    public void onDragBottom(boolean rightToLeft) {
        if (rightToLeft) {
            //toActivity(WebViewActivity.createIntent(context, "博客", Constant.APP_OFFICIAL_BLOG));
            return;
        }

        if (SettingUtil.isFirstStart) {
            runThread(TAG + "onDragBottom", new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "onDragBottom  >> SettingUtil.putBoolean(context, SettingUtil.KEY_IS_FIRST_IN, false);");
                    SettingUtil.putBoolean(SettingUtil.KEY_IS_FIRST_START, false);
                }
            });
        }

        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:

            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.llAboutDeveloper:
                CommonUtil.copyText(context, Constant.APP_DEVELOPER_WEBSITE);
                return true;
            case R.id.llAboutWeibo:
                CommonUtil.copyText(context, Constant.APP_OFFICIAL_BLOG);
                return true;
            case R.id.llAboutContactUs:
                CommonUtil.copyText(context, Constant.APP_OFFICIAL_EMAIL);
                return true;
            default:
                break;
        }
        return false;
    }



    //生命周期、onActivityResult<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



    //生命周期、onActivityResult>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>







    //内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



    //内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
