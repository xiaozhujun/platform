package org.whut.platform.fundamental.communication.dataHander;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 15-3-6
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */

public class HCoderFactory implements ProtocolCodecFactory {

    private final HEncoder encoder;
    private final HDecoder decoder;

    public HCoderFactory() {
        this(Charset.defaultCharset());
    }

    public HCoderFactory(Charset charSet) {
        this.encoder = new HEncoder(charSet);
        this.decoder = new HDecoder(charSet);
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        return decoder;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        return encoder;
    }

}
