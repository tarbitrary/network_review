package net.xcip.tarbitrary.bio.demo.convert;

import net.xcip.tarbitrary.bio.demo.entity.Vote;

/**
 * <p></p>
 *
 * @author tarbitrary
 * @version V1.0
 * @created_time 2019-12-11 17:21
 * @module 现货延期模块
 **/
public interface VoteInfoConvert {
    /**
     * 字节转换成投票信息
     * @param bytes 转化的字节
     * @return 投票信息
     */
  public Vote decode(byte[] bytes);

    /**
     * 投票信息转换成字节
     * @param vote
     * @return
     */
  public byte[] encode(Vote vote);
}
