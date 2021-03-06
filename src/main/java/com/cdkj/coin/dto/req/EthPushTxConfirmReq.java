package com.cdkj.coin.dto.req;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by tianlei on 2017/十一月/02.
 */
public class EthPushTxConfirmReq {

    @NotNull
    @Size(min = 1)
    private List<String> hashList;

    public List<String> getHashList() {
        return hashList;
    }

    public void setHashList(List<String> hashList) {
        this.hashList = hashList;
    }
}
