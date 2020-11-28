package net.xcip.tarbitrary.demo.convert;

import net.xcip.tarbitrary.demo.entity.Vote;

/**
 * <p></p>
 *
 * @author tarbitrary
 * @version V1.0
 * @created_time 2019-12-11 17:23
 * @module 现货延期模块
 **/
public class VoteInfoConvertImpl implements VoteInfoConvert {

    @Override
    public Vote marshall(byte[] bytes) {
        return null;
    }

    @Override
    public byte[] unmarshall(Vote vote) {
        return new byte[0];
    }

    public static void main(String[] args) {
        byte[] bytes = "vote".getBytes();
        for (byte b : bytes) {
            System.out.println(b);
        }
        System.out.println("vote".getBytes());
    }
}
