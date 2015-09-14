package Util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    public String render(Object model) throws Exception {

        return gson.toJson(model);
    }
}
