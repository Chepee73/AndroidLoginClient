package utils;

import org.json.JSONArray;

/**
 * Created by cezar on 28/02/17.
 */

public class RequestBlock implements Runnable {
    JSONArray jArr;

    public void initJson(JSONArray jArr) {
        this.jArr = jArr;
    }

    public JSONArray getjArr() {
        return jArr;
    }

    @Override
    public void run() {
    }

    public RequestBlock(/*Block block*/) {
    }
}