package cc.dt.hrweb.common.domain;

import java.util.HashMap;

public class HrWebResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public HrWebResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public HrWebResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public HrWebResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
