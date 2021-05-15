package com.dreamwolf.member.business.mapper;

import com.dreamwolf.entity.member.CoinHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CoinHistoryMapper {
    public Integer addcoin(CoinHistory coinHistory);
    public CoinHistory coinhistory(@Param("uID")Integer uID,@Param("vID")Integer vID);
}
