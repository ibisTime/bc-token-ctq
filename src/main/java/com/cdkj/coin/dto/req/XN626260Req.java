package com.cdkj.coin.dto.req;

import java.util.List;

import javax.validation.constraints.NotNull;

public class XN626260Req {

    @NotNull
    List<Long> idList;

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}
