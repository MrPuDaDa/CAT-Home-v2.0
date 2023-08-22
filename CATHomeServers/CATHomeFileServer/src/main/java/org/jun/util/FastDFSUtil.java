package org.jun.util;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import pojo.file.FastDFSFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * 文件上传
 * 文件删除
 * 文件下载
 * 文件信息获取
 * Tracker信息获取
 * Storage信息获取*/
public class FastDFSUtil {
    /*
     * 加载连接FastDFS连接信息*/
    static {
        try {
            // 获取指定文件的路径
            String filePath = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * 文件上传
     * */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {
        // 附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());
        // 打开Tracker客户端（实例化一个对象）
        TrackerClient trackerClient = new TrackerClient();
        // 打开服务
        TrackerServer trackerServer = trackerClient.getConnection();
        // 打开Storage客户端（实例化一个对象）
        StorageClient storageClient = new StorageClient(trackerServer, null);
        // 开始实现
        /*
         * 文件上传
         * 1)文件字节数组
         * 2)文件扩展名
         * 3)文件作者
         * uploads[
         *      文件的组名，文件的文件名加后缀
         * ]
         */
        String[] uploads = storageClient.upload_file(fastDFSFile.
                getContent(), fastDFSFile.getExt(), meta_list);
        return uploads;
    }

    /*
     * 文件下载
     * */

    public static InputStream downloadFile(String groupName, String remoteFileName) throws Exception {
        // 流程为TrackerClient --》 TrackerServer--》 StorageClient --》 文件操作
        /*TrackerClient ： Tracker客户端
        TrackerServer 打开服务
        StorageClient 文件操作场所
        * */
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        byte[] buffer = storageClient.download_file(groupName, remoteFileName);
        // 转为字节流返回
        return new ByteArrayInputStream(buffer);
    }

    /*
     * 获取文件信息
     * */
    public static FileInfo getFile(String groupName, String remoteFileName) throws Exception {
        // 流程为TrackerClient --》 TrackerServer--》 StorageClient --》 文件操作
        /*TrackerClient ： Tracker客户端
        TrackerServer 打开服务
        StorageClient 文件操作场所
        * */
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 获取文件信息
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * 删除文件
     * */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception {
        // 流程为TrackerClient --》 TrackerServer--》 StorageClient --》 文件操作
        /*TrackerClient ： Tracker客户端
        TrackerServer 打开服务
        StorageClient 文件操作场所
        * */

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        // 获取文件信息
        storageClient.delete_file(groupName, remoteFileName);

    }

    /*
     * 获取Storages信息*/
    public static StorageServer getStorageInfo() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorage(trackerServer);
    }

    /*
     * 获取Storages的IP AND 端口
     * */
    public static ServerInfo[] getFetchStorages(String groupName,
                                                String remoteFileName)
            throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupName,
                remoteFileName);
    }

    public static String getTrackerUrl() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return "http://" + trackerServer.getInetSocketAddress().getHostString() +
                ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }

    public static void main(String[] args) throws Exception {
/*        // 文件读取测试
        FileInfo fileInfo = getFile("group1","M00/00/00/wKjTg2PeHYCAIOKqAC83ESR1fAo580.png");
        System.out.println(fileInfo.getFileSize());*/

/*        // 文件下载测试
        InputStream is = downloadFile("group1","M00/00/00/wKjTg2PeHYCAIOKqAC83ESR1fAo580.png");
        // 保持文件
        FileOutputStream os = new FileOutputStream("C:/ISPProject/CatHome/CatHomeDoc/CodeTest/test.png");
        // 读取文件
        // 定义缓存区
        byte[] buffer = new byte[102423];
        while (is.read(buffer) != -1){
            os.write(buffer);
        }
        os.flush();
        os.close();
        is.close();*/

        // 删除文件测试
        deleteFile("group1", "M00/00/00/wKjTg2QvkliAU_BhACUxD7ewFBA629.jpg");

        // 获取Storages信息测试
        StorageServer storageInfo = getStorageInfo();
        System.out.println(storageInfo.getStorePathIndex());
    }

}
