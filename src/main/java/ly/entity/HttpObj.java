package ly.entity;

public class HttpObj {
    private boolean success;
    private int errorCode;
    private String msg;
    private CountObj obj;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;

    }

    public CountObj getObj() {
        return obj;
    }

    public void setObj(CountObj obj) {
        this.obj = obj;
    }

}