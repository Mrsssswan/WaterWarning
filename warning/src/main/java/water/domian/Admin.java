package water.domian;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 管理员
 */
@Entity
public class Admin {

    @Id
    @GeneratedValue
    private int id;//管理员编号
    private int role;//管理员类型 1表示系统管理员，0表示普通管理员
    private String name;
    @Column(columnDefinition = "default 123") //密码默认是123
    private String password; //密码使用MFD5加密

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
