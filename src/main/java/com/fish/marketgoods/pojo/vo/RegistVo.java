package com.fish.marketgoods.pojo.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegistVo {

    private String username;
    private String password;
    private MultipartFile file;
}
