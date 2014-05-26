package org.whut.platform.business.user.entity;

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
        private String dataRole;

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    private long appId;

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

        public String getDataRole(){
            return dataRole;
        }

        public void setDataRole(String dataRole){
            this.dataRole = dataRole;
        }
}
