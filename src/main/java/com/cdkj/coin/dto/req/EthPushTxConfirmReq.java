package com.cdkj.coin.dto.req;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by tianlei on 2017/十一月/02.
 */
public class EthPushTxConfirmReq {

    @NotNull
    @Size(min = 1)
    private List hashList;

    public List getHashList() {
        return hashList;
    }

    public void setHashList(List hashList) {
        this.hashList = hashList;
    }
}
