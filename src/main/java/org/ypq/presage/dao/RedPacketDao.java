package org.ypq.presage.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RedPacketDao {

    @Select("select token from redpacket where status=0 order by token limit 1")
    String queryRedPacket();

    @Update("update redpacket set status=1, score=#{score}, time=now() where token=#{token}")
    void consumeRedPacket(@Param("token") String token, @Param("score") Integer score);
}
