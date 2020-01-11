package net.xcip.tarbitrary.demo.convert;

import net.xcip.tarbitrary.demo.entity.Vote;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * <p></p>
 *
 * @author tarbitrary
 * @version V1.0
 * @created_time 2019-12-11 17:23
 * @module 现货延期模块
 **/
public class VoteInfoConvertImpl implements VoteInfoConvert {
    public static final String MAGIC_STR = "VOTE";
    public static final String SEPARATOR_STR = "|";

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    @Override
    public byte[] encode(Vote vote) {
        StringBuilder sb = new StringBuilder();
        sb.append(MAGIC_STR).append(SEPARATOR_STR);

        sb.append(vote.isRequest()).append(SEPARATOR_STR);
        if (vote.isRequest()) {
            sb.append(vote.isVoteFlag()).append(SEPARATOR_STR)
                    .append(vote.getUserId());

        } else {
            sb.append(vote.getCounts());
        }

        return sb.toString().getBytes(DEFAULT_CHARSET);

    }


    @Override
    public Vote decode(byte[] bytes) {
        try {
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                 InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream, DEFAULT_CHARSET);
                 Scanner scanner = new Scanner(inputStreamReader);
            ) {

                scanner.useDelimiter("\\" + SEPARATOR_STR);
                String currStr = scanner.next();
                if (!Objects.equals(currStr, MAGIC_STR)) {
                    throw new UnsupportedOperationException("invalid request!");
                }

                final Vote vote = new Vote();
                boolean isRequest = scanner.nextBoolean();
                vote.setRequest(isRequest);
                if (isRequest) {
                    boolean voteFlag = scanner.nextBoolean();
                    vote.setVoteFlag(voteFlag);
                    vote.setUserId(scanner.nextInt());
                    return vote;
                }

                vote.setCounts(scanner.nextInt());

                return vote;

            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Vote vote = new Vote();
        vote.setRequest(true);
        vote.setVoteFlag(true);
        vote.setUserId(123);

        System.out.println(vote);

        VoteInfoConvert convert = new VoteInfoConvertImpl();
        final byte[] encode = convert.encode(vote);

        final Vote decode = convert.decode(encode);
        System.out.println("decode" + decode);

    }
}
