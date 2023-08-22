package org.jun.controller;

import entity.Result;
import entity.StatusCode;

import org.jun.util.FastDFSUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pojo.file.FastDFSFile;
import pojo.file.FilePath;

import java.io.FileOutputStream;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/File")
@CrossOrigin
public class FileController {
    /*
     * 文件上传
     * */
    @PostMapping(value = "/upload")
    public Result upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        // 对文件封装
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename())
        );

        // 调用文件工具类上传到FastDFS
        String[] fileInfo = FastDFSUtil.upload(fastDFSFile);
        // 拼接访问地址 url
        String fileUrl = "http://192.168.211.131:8080/" + fileInfo[0] + "/" + fileInfo[1];

        return new Result<String>(true, StatusCode.OK, "上传文件成功", fileUrl);
    }

    /*
     * 文件下载
     * */
    @PostMapping("/{groupName}/{remoteFileName}")
    public Result downloadFile(@PathVariable(name = "groupName") String groupName,
                               @PathVariable(name = "remoteFileName") String remoteFileName,
                               @RequestBody FilePath filePath) throws Exception {
        // 固定路径
        String FileName = "M00/00/00/" + remoteFileName;
        InputStream is = FastDFSUtil.downloadFile(groupName, FileName);
        // 保持文件
        FileOutputStream os = new FileOutputStream(filePath.getDownloadPath());
        // 读取文件
        // 定义缓存区
        byte[] buffer = new byte[102423];
        while (is.read(buffer) != -1) {
            os.write(buffer);
        }
        os.flush();
        os.close();
        is.close();
        return new Result<String>(true, StatusCode.OK, "下载文件成功", "本地文件路径为" + filePath.getDownloadPath());
    }

    /*
     * 文件删除*/
    @DeleteMapping("/{groupName}/{remoteFileName}")
    public Result deleteFile(@PathVariable(name = "groupName") String groupName,
                             @PathVariable(name = "remoteFileName") String remoteFileName) throws Exception {
        // 固定路径
        String FileName = "M00/00/00/" + remoteFileName;
        FastDFSUtil.deleteFile(groupName, FileName);
        return new Result<String>(true, StatusCode.OK, "刪除文件成功", "success");
    }
}
