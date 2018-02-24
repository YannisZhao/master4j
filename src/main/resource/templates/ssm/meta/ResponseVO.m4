package ${package};

import java.io.Serializable;

public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = 3928054812181907536L;

    public static final ResponseVO SUCCESS = new com.ximalaya.corp.paper.web.basis.ResponseVO(200, "OK");
    public static final ResponseVO FAILED = new com.ximalaya.corp.paper.web.basis.ResponseVO(500, "Server internal error");

    protected int code;

    protected String message;

    protected String detail;

    protected T data;

    public ResponseVO() {
    }

    public ResponseVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVO(int code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public ResponseVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(int code, String message, String detail, T data) {
        this.code = code;
        this.message = message;
        this.detail = detail;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
