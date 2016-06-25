package Helper;

import com.google.gson.Gson;
import com.google.inject.Inject;


public class GsonHelper implements JsonHelper {
    private Gson gson;

    @Inject
    public GsonHelper(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String toJson(Object response) {
        String result = gson.toJson(response);

        return result;
    }

    @Override
    public <T> T fromJson(String request, Class<T> classOfT) {
        T result = gson.fromJson(request, classOfT);

        return result;
    }
}
