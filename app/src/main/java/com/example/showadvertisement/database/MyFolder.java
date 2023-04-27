package com.example.showadvertisement.database;

import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyFolder {

    private static MyFolder myFolder;

    private static File advertiseFolder;

    public static MyFolder getMyFolderInstance() {
        //避免不必要的同步
        if (myFolder == null) {
            synchronized (MyFolder.class){
                //避免重复创建单例
                if (myFolder == null) {
                    myFolder = new MyFolder();
                }
            }
        }
        return myFolder;
    }


    private MyFolder() {
        initSdCardDir();
    }

    private void initSdCardDir() {
        advertiseFolder = new File(Environment.getExternalStorageDirectory()+ "/advertise/");
        if (!advertiseFolder.exists() && Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            advertiseFolder.mkdirs();
        } else {
            advertiseFolder.delete();
            advertiseFolder.mkdirs();
        }
    }

    public void storageFile(List<File> advertises, String createDate)  {
        File destFile = new File(myFolder + "/" + createDate);
        if (!destFile.exists() && advertises.size() > 0) {
            destFile.mkdirs();
            try {
                FileOutputStream fileOutputStream = null;
                for (File file:advertises) {
                    String path = destFile.getAbsolutePath() + file.getName();
                    fileOutputStream = new FileOutputStream(path);
                    fileOutputStream.write(1024*1024*5);
                    fileOutputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStoragePath () {
        if (!advertiseFolder.exists() || !advertiseFolder.isDirectory()) {
            initSdCardDir();
        }
        return advertiseFolder.getAbsolutePath();
    }

    public void deleteTimeOutFile() {
        File[] fileOld = advertiseFolder.listFiles();
        if (fileOld.length > 0) {

        }
    }

    public List<File> copyFrom() {
        File srcFile = new File("D:/ShowAdvertisement/app/src/imagefile/fil001");
        List<File> fileList = new ArrayList<>();
        if (srcFile.exists()) {
            File[] files = srcFile.listFiles();
            if (files != null) {
                fileList.addAll(Arrays.asList(files));
            }
        }
        return fileList;
    }

    public List<File> getFileList (String fileFolder) {
        File file = new File(advertiseFolder.getAbsolutePath() + "/" + fileFolder);
        List<File> fileList = new ArrayList<>();
        if (file.exists() && file.isDirectory() && file.listFiles() != null) {
            // TODO: 2023/4/11  添加遍历并读取逻辑
//            fileList = file.listFiles();
            for (File theFile:
                 file.listFiles() ) {

            }
//            FileInputStream fileInputStream = new FileInputStream()
        } else {
            return null;
        }
        return fileList;
    }

}
