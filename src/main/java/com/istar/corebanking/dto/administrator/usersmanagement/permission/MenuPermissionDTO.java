package com.istar.corebanking.dto.administrator.usersmanagement.permission;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuPermissionDTO {

    private String code;
    private String label;
    private String route;
    private String icon;
    private boolean visible;

    private List<MenuPermissionDTO> children = new ArrayList<>();
}
