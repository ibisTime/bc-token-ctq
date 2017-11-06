package com.cdkj.coin.dto.req;

import com.cdkj.coin.api.AProcessor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tianlei on 2017/十一月/04.
 */
public class SysDictDeleteReq {

    @NotBlank
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
