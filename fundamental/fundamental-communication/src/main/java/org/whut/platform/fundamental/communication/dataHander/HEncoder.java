package org.whut.platform.fundamental.communication.dataHander;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 15-3-6
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
public class HEncoder extends ProtocolEncoderAdapter {

    private final Charset charset;

    public HEncoder(Charset charset) {
        this.charset = charset;

    }

    @Override
    public void encode(IoSession arg0, Object message, ProtocolEncoderOutput arg2)
            throws Exception {

        CharsetEncoder ce = charset.newEncoder();

        String value = (message == null ? "" : message.toString());
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
        buffer.putString(value, ce);
        buffer.flip();
        arg2.write(buffer);

    }

}
