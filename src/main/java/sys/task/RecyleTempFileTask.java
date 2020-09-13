package sys.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sys.constast.SysConstast;
import sys.utils.AppFileUtils;

import java.io.File;

@Component
@EnableScheduling  //开启定时任务
public class RecyleTempFileTask {

     /*每天晚上12点执行*/
     @Scheduled(cron="0 0 0 * * ? ")
    public void recyleTempFile(){
        /*获取文件夹目录*/
        File file= new File(AppFileUtils.PATH);
        deleteFile(file);
    }

    private void deleteFile(File file) {
        /*先判断目录里面不为空*/
        if(null!=file){
            /*获取里面的全部文件夹*/
            File[] listFiles=file.listFiles();
            if(null!=listFiles&&listFiles.length>0){
                /*文件类接收,遍历得到每个文件*/
                /*遍历每个文件*/
                for (File f: listFiles) {
                    /*判断是否是文件*/
                    if(f.isFile()) {
                        if (f.getName().endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
                                f.delete();
                        }
                    }else {
                        /*否者就是文件夹*/
                        /*调用递归*/
                        deleteFile(f);
                    }
                }
            }
        }
    }
}
