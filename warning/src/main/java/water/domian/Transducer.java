package water.domian;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 传感器
 */
@Entity
public class Transducer {

    @Id
    @GeneratedValue
    private int id;//传感器id编号
    private String message;//报文内容
    private String binaryMessage;//转换成二进制形式的报文内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBinaryMessage() {
        return binaryMessage;
    }

    public void setBinaryMessage(String binaryMessage) {
        binaryMessage = binaryMessage;
    }
}
