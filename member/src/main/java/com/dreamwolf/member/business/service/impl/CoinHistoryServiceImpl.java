package com.dreamwolf.member.business.service.impl;

import com.dreamwolf.entity.member.CoinHistory;
import com.dreamwolf.member.business.mapper.CoinHistoryMapper;
import com.dreamwolf.member.business.service.CoinHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CoinHistoryServiceImpl implements CoinHistoryService {

    @Resource
    CoinHistoryMapper coinHistoryMapper;

    @Override
    public Integer addcoin(CoinHistory coinHistory) {
        return coinHistoryMapper.addcoin(coinHistory);
    }

    @Override
    public CoinHistory coinhistory(Integer uID, Integer vID) {
        return coinHistoryMapper.coinhistory(uID,vID);
    }
}
