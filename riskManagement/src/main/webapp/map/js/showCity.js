if (typeof console == "undefined"){
    window.console = {log: function(){}};
}
$.initMap(province,8);
function getBoundary(province,data){
$.drawBoundary("areaRisk.jsp",province,null,null,data);
}
$.clearAllMarker;
var liaonings = [
    "沈阳市-#FF0000", "本溪市-#33FF00", "鞍山市-#FF0066", "铁岭市-#33FF00", "朝阳市-#FF3300",
    "锦州市-#33FF00", "盘锦市-#FF0000", "葫芦岛市-#FF0000", "营口市-#33FF00", "大连市-#FF3300",
    "丹东市-#FF3300","阜新市-#FF0000","抚顺市-#FF0066","辽阳市-#FF0000"];
var guangxis=[
    "南宁市-#FF0000", "柳州市-#33FF00", "桂林市-#FF3300", "梧州市-#33FF00", "北海市-#FF3300",
    "防城港市-#FF0000", "钦州市-#33FF00", "贵港市-#FF3300", "玉林市-#FF0000", "百色市-#FF3300",
    "贺州市-#FF0000", "河池市-#33FF00", "来宾市-#FF0000", "崇左市-#33FF00"
];
var guangdongs=[
    "广州市-#33FF00", "韶关市-#FF3300", "深圳市-#FF0000", "珠海市-#FCFBBB", "汕头市-#FF3300",
    "佛山市-#FF3300", "江门市-#FF0000", "湛江市-#E7CCAF", "茂名市-#E7CCAF", "肇庆市-#FF0000", "惠州市-#FF0066",
    "梅州市-#FF0000", "汕尾市-#33FF00", "河源市-#FF0000","阳江市-#C8C1E3", "清远市-#FF0066", "东莞市-#FF3300", "中山市-#FF0066", "潮州市-#33FF00",
    "揭阳市-#FF3300", "云浮市-#FF0000"
];
var hunans=[
    "长沙市-#FF3300", "株洲市-#FF0000", "湘潭市-#FF0000", "衡阳市-#FF0000", "邵阳市-#33FF00",
    "岳阳市-#33FF00", "常德市-#FF0000", "张家界市-#33FF00", "益阳市-#FF0000", "郴州市-#E7CCAF",
    "永州市-#DBECC8","怀化市-#FF0000", "娄底市-#33FF00", "湘西土家族苗族自治州-#33FF00"
];
var guizhous=[
    "贵阳市-#FF0000", "六盘水市-#FF0066", "遵义市-#33FF00", "安顺市-#FF0066", "铜仁地区-#FF0000",
    "兴义市-#33FF00", "毕节地区-#33FF00", "黔东南苗族侗族自治州-#FF0000", "黔南布依族苗族自治州-#FF0000"
];
var yunnans=[
    "昆明市-#FF0000", "曲靖市-#33FF00", "玉溪市-#FF0066", "保山市-#FF0066", "昭通市-#FF0000",
    "丽江市-#FF0066", "墨江哈尼族自治县-#33FF00", "临沧市-#FF0000", "楚雄彝族自治州-#FBC5DC", "红河哈尼族彝族自治州-#FF0000",
    "文山壮族苗族自治州-#FF0000", "西双版纳傣族自治州-#FF0000", "大理白族自治州-#FF0000", "德宏傣族景颇族自治州-#FF0066","怒江傈僳族自治州-#FF0066",
    "迪庆藏族自治州-#FF0000","普洱市-#33FF00"
];
var fujians=[
    "福州市-#FF0000", "厦门市-#33FF00", "莆田市-#33FF00", "三明市-#FF0000", "泉州市-#FF0066",
    "漳州市-#FF0066", "南平市-#33FF00", "龙岩市-#FF0000"
];
var jiangxis=[
    "南昌市-#FF0000", "景德镇市-#FF0066", "萍乡市-#33FF00", "九江市-#FF0000", "新余市-#FF0066",
    "鹰潭市-#FF0066", "赣州市-#FF0000", "吉安市-#33FF00", "宜春市-#33FF00", "抚州市-#FF0066", "上饶市-#FF0000"
];
var zhejiangs=[
    "杭州市-#FF0000", "宁波市-#FF0066", "温州市-#33FF00", "嘉兴市-#FF0066", "湖州市-#33FF00",
    "绍兴市-#33FF00", "金华市-#FF0066", "衢州市-#33FF00", "舟山市-#FF0000", "台州市-#33FF00",
    "丽水市-#FF0000"
];
var anhuis=[
    "合肥市-#33FF00", "芜湖市-#FF0000", "蚌埠市-#33FF00", "淮南市-#33FF00", "马鞍山市-#33FF00",
    "淮北市-#FF0000", "铜陵市-#FF0000", "安庆市-#33FF00", "黄山市-#FF0000", "滁州市-#FF0000", "阜阳市-#FF0066",
    "宿州市-#FF0000", "巢湖市-#FF0066", "六安市-#33FF00","亳州市-#FF0000", "池州市-#FF0000", "宣城市-#33FF00"
];
var hubeis=[
    "武汉市-#FF0066", "黄石市-#33FF00", "十堰市-#FF0066", "宜昌市-#FF0066", "襄樊市-#FF0000",
    "鄂州市-#FF0066", "荆门市-#33FF00", "孝感市-#33FF00", "荆州市-#33FF00", "黄冈市-#FF0000",
    "咸宁市-#33FF00", "随州市-#FF0066", "恩施土家族苗族自治州-#33FF00", "仙桃市-#FF0000","天门市-#FF0066","潜江市-#33FF00","神农架林区-#FF0066"
];
var henans=[
    "郑州市-#33FF00", "开封市-#FF0000", "洛阳市-#33FF00", "平顶山市-#FF0066", "安阳市-#33FF00",
    "鹤壁市-#FF0066", "新乡市-#FF0066", "焦作市-#FF0066", "濮阳市-#FF0000", "许昌市-#FF0000",
    "漯河市-#FF0000", "三门峡市-#33FF00", "南阳市-#33FF00", "商丘市-#33FF00","信阳市-#FF0000",
    "周口市-#FF0066","驻马店市-#33FF00","洛阳市-#FF0000"
];
var jiangsus=[
    "南京市-#FF0066", "无锡市-#33FF00", "徐州市-#FF0066", "常州市-#FF0000", "苏州市-#33FF00",
    "南通市-#FF0000", "连云港市-#FF0000", "淮安市-#33FF00", "盐城市-#FF0066", "扬州市-#FF0000", "镇江市-#33FF00",
    "泰州市-#FF0066", "宿迁市-#FF0066"
];
var sichuans=[
    "成都市-#FF0066", "自贡市-#FF0000", "攀枝花市-#FF0066", "泸州市-#FF0000", "德阳市-#FF0066",
    "绵阳市-#FF0000", "广元市-#FF0066", "遂宁市-#33FF00", "内江市-#33FF00", "乐山市-#33FF00", "南充市-#FF0000",
    "眉山市-#FF0066", "宜宾市-#FF0000", "广安市-#33FF00","达州市-#FF0066", "雅安市-#FF0066", "巴中市-#FF0066",
    "资阳市-#FF0000", "阿坝藏族羌族自治州-#FF0000", "甘孜藏族自治州-#FF0066", "凉山彝族自治州-#FF0000"
];
var hainans=[
    "海口市-#FF0066", "三亚市-#FF0000", "五指山市-#33FF00","东方市-#FF0066","文昌市-#33FF00"
];
var shandongs=[
    "济南市-#FF0066", "青岛市-#FF0000", "淄博市-#FF0066", "枣庄市-#FF0000", "东营市-#FF0066",
    "烟台市-#FF0000", "潍坊市-#33FF00", "济宁市-#FF0000", "泰安市-#33FF00", "威海市-#FF0000", "日照市-#FF0000",
    "莱芜市-#FF0000", "临沂市-#FF0066","德州市-#33FF00", "聊城市-#FF0066", "滨州市-#33FF00"
];
var xinjiangs=[
    "乌鲁木齐市-#FF0000", "克拉玛依市-#FF0000", "昌吉回族自治州-#FF0000", "博尔塔拉蒙古自治州-#FF0066", "巴音郭楞蒙古自治州-#FF0066",
    "阿克苏地区-#FF0066", "阿图什市-#FF0066", "喀什地区-#FF0066", "和田地区-#FF0066", "伊犁哈萨克自治州-#FF0000", "塔城地区-#FF0000",
    "阿勒泰地区-#FF0000", "石河子市-#FF0066"
];
var xizangs=[
    "拉萨市-#FF0000", "昌都地区-#FF0066", "山南地区-#FF0000", "日喀则地区-#FF0066", "那曲地区-#FF0000",
    "阿里地区-#33FF00", "林芝地区-#FF0000"
];
var shanxis=[
    "西安市-#FF0000", "铜川市-#FF0066", "宝鸡市-#FF0000", "咸阳市-#FF0066", "渭南市-#33FF00",
    "延安市-#FF0066", "汉中市-#FF0000", "榆林市-#33FF00", "安康市-#FF0000", "商洛市-#FF0066"
];
var hebeis=[
    "石家庄市-#FF0000", "唐山市-#FF0066", "秦皇岛市-#FF0000", "邯郸市-#FCFBBB", "邢台市-#FF0066",
    "保定市-#FF0066", "张家口市-#FF0000", "承德市-#FF0066", "沧州市-#FF0000", "廊坊市-#FF0000", "衡水市-#FF0000"
];
var heilongjiangs=[
    "哈尔滨市-#FF0000", "齐齐哈尔市-#FF0066", "鸡西市-#FF0066", "鹤岗市-#FF0000", "双鸭山市-#FF0066",
    "大庆市-#33FF00", "伊春市-#33FF00", "佳木斯市-#FF0000", "七台河市-#33FF00", "牡丹江市-#FF0000", "黑河市-#FF0000",
    "绥化市-#FF0000", "大兴安岭地区-#FF0000"
];
var ningxias=[
    "银川市-#FF0000", "石嘴山市-#FF0066", "吴忠市-#FF0000", "固原市-#FF0066", "中卫市-#33FF00"
];
var neimenggus=[
    "呼和浩特市-#33FF00", "包头市-#33FF00", "乌海市-#FF0000", "赤峰市-#FF0066", "通辽市-#FF0000",
    "鄂尔多斯市-#FF0000", "呼伦贝尔市-#33FF00", "巴彦淖尔市-#FF0000", "乌兰察布市-#FF0000", "兴安盟-#FF0066",
    "锡林郭勒盟-#33FF00","阿拉善盟-#FF0000"
];
var qinghais=[
    "西宁市-#FF0000", "海东地区-#FF0066", "海北藏族自治州-#FF0000", "黄南藏族自治州-#FF0000", "海南藏族自治州-#FF0066",
    "果洛藏族自治州-#FF0000", "玉树藏族自治州-#33FF00", "海西蒙古族藏族自治州-#FF0066"
];
var gansus=[
    "兰州市-#FF0066", "嘉峪关市-#FF0000", "金昌市-#FF0066", "白银市-#33FF00", "天水市-#FF0000",
    "武威市-#FF0000", "张掖市-#FF0066", "平凉市-#FF0000", "酒泉市-#33FF00", "庆阳市-#FF0000", "定西市-#33FF00",
    "陇南市-#FF0066", "临夏回族自治州-#FF0066","甘南藏族自治州-#FF0000"
];
var shanxishengs=[
    "太原市-#FF0066", "大同市-#FF0000", "阳泉市-#FF0066", "长治市-#33FF00", "晋城市-#FF0066",
    "朔州市-#FF0000", "晋中市-#FF0066", "运城市-#FF0000", "忻州市-#FF0066", "临汾市-#FF0000",
    "吕梁市-#FF0066"
];
var jilins=[
    "长春市-#FF0000", "吉林市-#FF0066", "四平市-#FF0000", "辽源市-#FF0066", "通化市-#33FF00",
    "白山市-#FF0066", "松原市-#FF0000", "白城市-#FF0066", "延边朝鲜族自治州-#FF0000"
];
var  beijings=[
    "北京市-#FBC5DC"
];
var tianjins=[
    "天津市-#33FF00"
];
var shanghais=[
    "上海市-#FF0000"
];
var chongqins=[
    "重庆市-#33FF00"
];
var xianggangs=[
    "香港-#FCFBBB"
];
if(province=="辽宁省"){
    drawColor("辽宁省",liaonings);
}else if(province=="内蒙古自治区"){
    drawColor("内蒙古自治区",neimenggus);
}else if(province=="广西"){
    drawColor("广西",guangxis);
}else if(province=="广东"){
    drawColor("广东",guangdongs);
}else if(province=="湖南"){
    drawColor("湖南",hunans);
}else if(province=="贵州"){
    drawColor("贵州",guizhous);
}else if(province=="云南"){
    drawColor("云南",yunnans);
}else if(province=="福建"){
    drawColor("福建",fujians);
}else if(province=="江西"){
    drawColor("江西",jiangxis);
}else if(province=="浙江"){
    drawColor("浙江",zhejiangs);
}else if(province=="安徽"){
    drawColor("安徽",anhuis);
}else if(province=="湖北"){
    drawColor("湖北",hubeis);
}else if(province=="河南"){
    drawColor("河南",henans);
}else if(province=="江苏"){
    drawColor("江苏",jiangsus);
}else if(province=="四川"){
    drawColor("四川",sichuans);
}else if(province=="海南省"){
    drawColor("海南省",hainans);
}else if(province=="山东"){
    drawColor("山东",shandongs);
}else if(province=="辽宁"){
    drawColor("辽宁",liaonings);
}else if(province=="新疆"){
    drawColor("新疆",xinjiangs);
}else if(province=="西藏"){
    drawColor("西藏",xizangs);
}else if(province=="陕西"){
    drawColor("陕西",shanxis);
}else if(province=="河北"){
    drawColor("河北",hebeis);
}else if(province=="黑龙江"){
    drawColor("黑龙江",heilongjiangs);
}else if(province=="宁夏"){
    drawColor("宁夏",ningxias);
}else if(province=="青海"){
    drawColor("青海",qinghais);
}else if(province=="甘肃"){
    drawColor("甘肃",gansus);
}else if(province=="山西"){
    drawColor("山西",shanxishengs);
}else if(province=="吉林省"){
    drawColor("吉林省",jilins);
}else if(province=="北京"){
    drawColor("北京",beijings);
}else if(province=="天津"){
    drawColor("天津",tianjins);
}else if(province=="上海"){
    drawColor("上海",shanghais);
}else if(province=="重庆市"){
    drawColor("重庆市",chongqins);
}else if(province=="香港"){
    drawColor("香港",xianggangs);
}
//给不同的市赋予不同颜色
function drawColor(province,array){
    for(var i=0;i<array.length;i++){
        getBoundary(province,array[i]);
    }
}