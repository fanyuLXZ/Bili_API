package com.dreamwolf.member.business.service;

import com.dreamwolf.entity.member.CoinHistory;
import org.apache.ibatis.annotations.Param;

public interface CoinHistoryService {
    public Integer addcoin(CoinHistory coinHistory);

    public CoinHistory coinhistory(Integer uID,Integer vID);
}
