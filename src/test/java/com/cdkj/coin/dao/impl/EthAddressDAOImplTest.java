package com.cdkj.coin.dao.impl;

import com.cdkj.coin.ao.IEthAddressAO;
import com.cdkj.coin.base.ADAOTest;
import com.cdkj.coin.dao.IEthAddressDAO;
import com.cdkj.coin.dao.ISYSConfigDAO;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import static org.junit.Assert.*;

/**
 * Created by tianlei on 2017/十一月/07.
 */
public class EthAddressDAOImplTest extends ADAOTest {

    @SpringBeanByType
    private IEthAddressDAO iEthAddressDAO;

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void selectTotalCount() throws Exception {
    }

    @Test
    public void selectTotalCountByAddress() throws Exception {

        long count = this.iEthAddressDAO.selectTotalCountByAddress(null);
        System.out.print(count);

    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void select() throws Exception {
    }

    @Test
    public void insertBatch() throws Exception {
    }

    @Test
    public void selectList() throws Exception {
    }

    @Test
    public void selectList1() throws Exception {
    }

}