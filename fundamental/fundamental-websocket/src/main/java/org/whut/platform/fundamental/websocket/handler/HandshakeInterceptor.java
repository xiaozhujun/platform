package org.whut.platform.fundamental.websocket.handler;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 14-8-9
 * Time: 上午9:51
 * To change this template use File | Settings | File Templates.
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    private static final PlatformLogger logger = PlatformLogger.getLogger(WebsocketEndPoint.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        logger.info("Before Handshake");
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        logger.info("After Handshake");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
