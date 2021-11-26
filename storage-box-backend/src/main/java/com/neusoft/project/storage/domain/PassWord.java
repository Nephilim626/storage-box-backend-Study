package com.neusoft.project.storage.domain;

import lombok.Data;

@Data
public class PassWord {
    private String oldPassword;
    private String newPassword;
}
