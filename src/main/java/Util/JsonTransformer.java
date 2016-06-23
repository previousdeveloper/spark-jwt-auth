package Util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    public String render(Object model) throws Exception {

        return gson.toJson(model);
    }
}
