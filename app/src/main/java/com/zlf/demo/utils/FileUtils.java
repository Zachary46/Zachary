package com.zlf.demo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

    public static File saveBitmapToFile(Bitmap b,Context context)  {
        if (Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED)) // 判断是否可以对SDcard进行操作
        {    // 获取SDCard指定目录下
            String  sdCardDir = Environment.getExternalStorageDirectory()+ "/KHTImage/";
            File dirFile  = new File(sdCardDir);  //目录转化成文件夹
            if (!dirFile .exists()) {              //如果不存在，那就建立这个文件夹
                dirFile .mkdirs();
            }                          //文件夹有啦，就可以保存图片啦
            File file = new File(sdCardDir, System.currentTimeMillis()+".jpg");// 在SDcard的目录下创建图片文,以当前时间为其命名
            FileOutputStream out=null;
            try {
                out = new FileOutputStream(file);
                b.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
                System.out.println("_________保存到____sd______指定目录文件夹下____________________");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return file;
        }
        return null;
    }

    /**
     *  从assets目录中复制整个文件夹内容
     *  @param  context  Context 使用CopyFiles类的Activity
     *  @param  oldPath  String  原文件路径  如：/aa
     *  @param  newPath  String  复制后路径  如：xx:/bb/cc
     */
    public static void copyFilesFassets(Context context,String oldPath,String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context,oldPath + "/" + fileName,newPath+"/"+fileName);
                }
            } else {//如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount=0;
                while((byteCount=is.read(buffer))!=-1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //如果捕捉到错误则通知UI线程
           // MainActivity.handler.sendEmptyMessage(COPY_FALSE);
        }
    }

    public static String createImageFilePath() {
        File mediaStorageDir=null;
        Log.d("GetImageUtil", "SDCardUtils.isSDCardMounted():" + SDCardUtils.isSDCardMounted());
        if (SDCardUtils.isSDCardMounted()){
            mediaStorageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath
                    () + "/kht/image","eweima.png");
        }else {
            mediaStorageDir=new File("/sdcard/kht/image","eweima.png");
        }
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
    /*    // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "kht_" + timeStamp;
        String suffix = ".jpg";
        File mMediaFile = new File(mediaStorageDir + File.separator + imageFileName + suffix);*/
        return mediaStorageDir.getAbsolutePath();
    }

    /**
     * 保存路径的文件夹名称
     */
    public static String DIR_NAME = "ballislove";

    public static boolean fileIsExists(String url){
        try{
            File f=new File(url);
            if(!f.exists()){
                return false;
            }

        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    /**
     * 给指定的文件名按照时间命名
     */
    private static SimpleDateFormat OUTGOING_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");


    /**
     * 得到指定的Video保存路径
     * @return
     */
    public static File getDoneVideoPath(Context context) {
        String str = OUTGOING_DATE_FORMAT.format(new Date());
        File dir = new File(context.getFilesDir()
                + File.separator + DIR_NAME + "/" + str);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }


    private static File getStorageDir(Context context) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.MEDIA_MOUNTED), "ZhouImage");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TAG", "Directory not created");
            }
        }

        return file;
    }
    public static File getStorageDir2(Context context) {
        // Get the directory for the app's private pictures directory.
        File file = new File(Environment.getExternalStorageDirectory()+File.separator+"ZhouImage", OUTGOING_DATE_FORMAT.format(new Date())+".jpg");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TAG", "Directory not created");
            }
        }

        return file;
    }

  /*  public static String getImageSavePath(){
        String filename=Environment.getExternalStorageDirectory()+File.separator+"ZhouImage", OUTGOING_DATE_FORMAT.format(new Date())+".jpg";
    }*/

    /**
     * 得到输出的Video保存路径
     * @return
     */
    public static String newOutgoingFilePath(Context context) {
        String str = OUTGOING_DATE_FORMAT.format(new Date());
        String fileName = getStorageDir(context).getPath()+ "/" + File.separator + str + ".jpg";
        return fileName;
    }


}
