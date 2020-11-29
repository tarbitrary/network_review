package net.xcip.tarbitrary.bio.demo.convert;

import net.xcip.tarbitrary.demo.convert.VoteInfoConvert;
import net.xcip.tarbitrary.demo.entity.Vote;

import java.io.*;

/**
 * @author tarbitrary
 * @info <p>
 * 编码方式
 * ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * 1   2   3   4   5   6   7   8   9  10   11  12  13  14  15  16
 * ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * MAGIC_PREFIX(1-6) |req|vote| 0 (9 - 16)
 * ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * 侯选人ID（两个字节）
 * ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * 投票数（两个字节） 响应时此字段值才会输出
 * ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
 * </p>
 */
public class BinaryVoteInfoConvertImpl implements VoteInfoConvert {
    /**
     * 01010100 00000000
     */
    public static final int MAGIC_PREFIX = 0x5400;

    /**
     * 11111100 00000000
     */
    public static final int MAGIC_PREFIX_MASK = 0xfc00;

    /**
     * 00000010 00000000
     */
    public static final int REQUEST_FLAG = 0x0200;

    /**
     * 00000001 00000000
     */
    public static final int VOTE_FLAG = 0x0100;

    @Override
    public byte[] encode(Vote vote) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(baos);
        int magicNum = MAGIC_PREFIX;
        if (vote.isRequest()) {
            magicNum |= REQUEST_FLAG;
        }

        if (vote.isVoteFlag()) {
            magicNum |= VOTE_FLAG;
        }

        try {
            dataOutputStream.writeShort(magicNum);
            dataOutputStream.writeShort(vote.getUserId());
            if (!vote.isRequest()) {
                dataOutputStream.writeShort(vote.getCounts());
            }
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return baos.toByteArray();
    }

    @Override
    public Vote decode(byte[] bytes) {
        try (
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                DataInputStream dis = new DataInputStream(byteArrayInputStream)

        ) {
            final short magicNum = dis.readShort();
            if ((magicNum & MAGIC_PREFIX_MASK) != MAGIC_PREFIX) {
                throw new UnsupportedOperationException("invalid request");
            }
            final Vote vote = new Vote();

            if ((magicNum & REQUEST_FLAG) == REQUEST_FLAG) {
                vote.setRequest(true);
            }

            if ((magicNum & VOTE_FLAG) == VOTE_FLAG) {
                vote.setVoteFlag(true);
            }

            vote.setUserId(dis.readShort());

            if (!vote.isRequest()) {
                vote.setCounts(dis.readShort());
            }

            return vote;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Vote vote = new Vote();
        vote.setRequest(false);
        vote.setVoteFlag(false);
        vote.setUserId(123);
        vote.setCounts(100);

        System.out.println(vote);

        VoteInfoConvert convert = new BinaryVoteInfoConvertImpl();
        final byte[] encode = convert.encode(vote);

        final Vote decode = convert.decode(encode);
        System.out.println("decode" + decode);

    }
}
