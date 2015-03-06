package org.whut.platform.fundamental.communication.dataHander;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 15-3-6
 * Time: 下午1:58
 * To change this template use File | Settings | File Templates.
 */

public class HDecoder extends CumulativeProtocolDecoder {

    private final Charset charset;

    public HDecoder(Charset charset) {
        this.charset = charset;

    }

    @Override
    protected boolean doDecode(IoSession arg0, IoBuffer arg1,
                               ProtocolDecoderOutput arg2) throws Exception {
        CharsetDecoder cd = charset.newDecoder();
        String str = arg1.getString(cd);
        arg2.write(str);
        return true;
    }

}
