
 $.extend({
     initWebSocket:function initWebSocket(s,method){
       var url= $.URL.websocket.register;
       ws = new WebSocket(url);
       ws.onopen = function(){
           setInterval(function(){$.post($.URL.user.keepAlive,null,null)},1000*60);
           console.log("open"); ws.send(s);};
       ws.onmessage=function (event){method(event.data);};
       ws.onclose =function onclose(evt){console.log("WebSocketClosed!");};
       ws.onerror =function onerror(evt){console.log("WebSocketError!");};
    },
     WebSocketClose:function WebSocketClose(){ws.close();ws=null},
     WebSocketSend:function WebSocketSend(str){ws.send(str)},
     WebSocketConnect:function WebSocketConnect(){return ws}
});




