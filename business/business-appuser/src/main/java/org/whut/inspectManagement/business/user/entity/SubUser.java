package org.whut.inspectManagement.business.user.entity;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-23
 * Time: 下午6:49
 * To change this template use File | Settings | File Templates.
 */
public class SubUser {

        private long id;
        private String name;
        private String password;
        private String sex;
        private String role;

        private String status;
        private long appId;
        private String appName;





        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getAppId() {
            return appId;
        }

        public void setAppId(long appId) {
            this.appId = appId;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public long getId() {
                return id;
            }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSex(){
            return sex;
        }

        public void setSex(String sex){
            this.sex = sex;
        }

        public String getRole(){
            return role;
        }

        public void setRole(String role){
            this.role = role;
        }


}
