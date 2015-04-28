package org.whut.platform.fundamental.message.api;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-28
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */
public interface MessageParser {
    public boolean canParse(String message);
    public void parse(String message);
}
