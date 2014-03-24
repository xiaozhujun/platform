<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
<title>获取地区轮廓线</title> 
<link rel="stylesheet" href="map/css/showcity.css"/>
<link rel="stylesheet" href="map/css/style.css"/>
<script type="text/javascript" src="map/js/getParam.js"></script>
<script type="text/javascript">
    var pname='<%=URLDecoder.decode(request.getParameter("pname"),"utf-8")%>';
    var lat='<%=request.getParameter("lat")%>';
    var lng='<%=request.getParameter("lng")%>';
</script>
</head> 
<body>
<div id="container"></div> 
<script type="text/javascript" src="map/js/showCity.js"></script>
</body> 
</html>  