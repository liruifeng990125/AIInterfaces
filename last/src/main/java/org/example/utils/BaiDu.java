package org.example.utils;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

public class BaiDu {
    private static final Logger logger = LogManager.getLogger(BaiDu.class);
    static HashMap<String, String> hashMap;
    private static String API_KEY;
    private static String SECRET_KEY;
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    static {
        hashMap = ReadPropertiesFile.readProperties();
        API_KEY = hashMap.get("baiduAPI_KEY");
        logger.info("API_KEY " + API_KEY);
        SECRET_KEY = hashMap.get("baiduSECRET_KEY");
        logger.info("SECRET_KEY " + SECRET_KEY);

    }

    /**
     * 封装请求参数
     *
     * @param question
     * @return
     */
    public static JSONObject testJsonArray(String question) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("role", "user");
        jsonObject.put("content", question);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("messages", jsonArray);
        return jsonObject1;

    }

    public static String baiDuBigData(String question) throws IOException {

        MediaType mediaType = MediaType.parse("application/json");
        JSONObject content = testJsonArray(question);
        logger.info("用户提问的问题是 " + content);
        RequestBody body = RequestBody.create(mediaType, content.toString());
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=" + getAccessToken())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        OkHttpClient httpClient2 = new OkHttpClient.Builder()
                .callTimeout(30, java.util.concurrent.TimeUnit.SECONDS) // Set overall call timeout
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS) // Set read timeout
                .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS) // Set write timeout
                .build();

        Response response = null;
        try {
            response = httpClient2.newCall(request).execute();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            // Handle other IO exceptions
            e.printStackTrace();
        }


        assert response.body() != null;
        String answer = null;
        String result = null;
        try {
            answer = response.body().string();
            JSONObject jsonObject = new JSONObject(answer);
            result = jsonObject.get("result").toString();
            logger.info("result "+result);
        } catch (IOException e) {
            logger.error("百度模型返回出错  " + e);
            throw new RuntimeException(e);
        } finally {
            response.body().close();
            return result;
        }


    }

    /**
     * 返回参数的格式
     * {
     * "id": "as-ckm3y8x4aa",
     * "object": "chat.completion",
     * "created": 1699060692,
     * "result": "你好，很高兴认识你。有什么我可以帮助你的吗？",
     * "is_truncated": false,
     * "need_clear_history": false,
     * "usage": {
     * "prompt_tokens": 1,
     * "completion_tokens": 12,
     * "total_tokens": 13
     * }
     * }
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        baiDuBigData("直角三角形，直角边为5和12，斜边长度是多少");


    }

    /**
     * 从用户的AK，SK生成鉴权签名（Access Token）
     *
     * @return 鉴权签名（Access Token）
     * @throws IOException IO异常
     */
    static String getAccessToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&client_id=" + API_KEY
                + "&client_secret=" + SECRET_KEY);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return new JSONObject(response.body().string()).getString("access_token");
    }


}
