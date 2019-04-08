package com.cdkj.coin.dto.req;

import javax.validation.constraints.NotNull;
import java.util.List;

public class XN626420Req {

    @NotNull
    List<Long> idList;

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}
