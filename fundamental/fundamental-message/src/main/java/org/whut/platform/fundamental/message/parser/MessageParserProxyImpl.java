package org.whut.platform.fundamental.message.parser;

import org.whut.platform.fundamental.message.api.MessageParser;
import org.whut.platform.fundamental.message.api.MessageParserProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-28
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
public class MessageParserProxyImpl implements MessageParser,MessageParserProxy{

    List<MessageParser> parserChain = new ArrayList<MessageParser>();

    @Override
    public boolean canParse(String message) {
        for(MessageParser parser:parserChain){
            if(parser.canParse(message)) return true;
        }
        return false;
    }

    @Override
    public void parse(String message) {
         for (MessageParser parser:parserChain){
             parser.parse(message);
         }
    }

    @Override
    public void registryParser(MessageParser parser) {
        if(!parserChain.contains(parser)){
            parserChain.add(parser);
        }
    }
}
