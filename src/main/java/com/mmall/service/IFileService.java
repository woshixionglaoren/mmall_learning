package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 华耀 王 on 2020/3/23
 **/

public interface IFileService {
    String upload(MultipartFile file, String path);
}
