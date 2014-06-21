import org.whut.platform.fundamental.mongo.connector.MongoConnector;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-11
 * Time: 上午9:32
 * To change this template use File | Settings | File Templates.
 */
public class InsertToMongoData {
        private static MongoConnector mongoConnector=new MongoConnector("craneInspectReportDB","inspectItemRecordCollection");
        public static void insertToMongoData(){
            //构造成:inspectitemrecords:[{'mongoId':'1','inspectTableId':'1','inspectTagId':'1','inspectTagId':'1','inspectItemId':'1','inspectChoiceId':'1','inspectChoiceValue':'正常','inspectTableRecord':'1','userId':'1','deviceId':'1','appId':'1','note':'笔记'},
            // {'mongoId':'2','inspectTableId':'1','inspectTagId':'1','inspectTagId':'1','inspectItemId':'1','inspectChoiceId':'1','inspectChoiceValue':'正常','inspectTableRecord':'1','userId':'1','deviceId':'1','appId':'1','note':'笔记'},
            // {'mongoId':'3','inspectTableId':'1','inspectTagId':'1','inspectTagId':'1','inspectItemId':'1','inspectChoiceId':'1','inspectChoiceValue':'正常','inspectTableRecord':'1','userId':'1','deviceId':'1','appId':'1','note':'笔记'},
            // {'mongoId':'4','inspectTableId':'1','inspectTagId':'1','inspectTagId':'1','inspectItemId':'1','inspectChoiceId':'1','inspectChoiceValue':'正常','inspectTableRecord':'1','userId':'1','deviceId':'1','appId':'1','note':'笔记'}]
             /*String mongoString="{inspectitemrecords:[";
             for(int i=1;i<=10;i++){
                 mongoString+="{'mongoId':'"+i+"','inspectTableId':'"+i+"','inspectTagId':'"+i+"','inspectTagId':'"+i+"','inspectItemId':'"+i+"','inspectChoiceId':'"+i+"','inspectChoiceValue':'正常','inspectTableRecord':'"+i+"','userId':'"+i+"','deviceId':'"+i+"','appId':'"+i+"','note':'笔记'},";
                 if(i==10){
                     mongoString+="{'mongoId':'"+i+"','inspectTableId':'"+i+"','inspectTagId':'"+i+"','inspectTagId':'"+i+"','inspectItemId':'"+i+"','inspectChoiceId':'"+i+"','inspectChoiceValue':'正常','inspectTableRecord':'"+i+"','userId':'"+i+"','deviceId':'"+i+"','appId':'"+i+"','note':'笔记'}";
                 }
             }
            mongoString+="]}";*/

            String mongoString="{inspectitemrecords:[{'mongoId':'1','inspectTableId':'1','inspectTagId':'2','inspectItemId':'16','inspectChoiceId':'1','inspectChoiceValue':'正常','inspectTableRecord':'1','userId':'1','deviceId':'1','appId':'1','note':'笔记'}]}";
            mongoConnector.insertDocument(mongoString);
        }
        public static void test(){
            try{
                System.out.println(new String("机修人员".getBytes("GBK"))+"1");
                System.out.println(new String("机修人员".getBytes("UTF-8"))+"2");
                System.out.println("机修人员"+"3");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public static void main(String[] args){
               //insertToMongoData();
            test();
        }

}
