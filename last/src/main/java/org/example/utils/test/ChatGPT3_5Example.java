package org.example.utils.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.ReadPropertiesFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class ChatGPT3_5Example {

    private static final Logger logger = LogManager.getLogger(ChatGPT3_5Example.class);
    private static HashMap<String, String> map = ReadPropertiesFile.readProperties();

    public static JSONObject createRequestBody(String question) {
        JSONObject role1 = new JSONObject();
        role1.put("role", "system");
        role1.put("content", "You are a helpful assistant");
        JSONObject role2 = new JSONObject();
        role2.put("role", "user");
        role2.put("content", question);
        JSONArray message = new JSONArray();
        message.add(role1);
        message.add(role2);
        JSONObject resultObject = new JSONObject();
        resultObject.put("messages", message);
        resultObject.put("model", "gpt-3.5-turbo");
        return resultObject;

    }

    public static String chatGpt(String question) {
        String apiKey = map.get("chatgpt_apiKey");
        // 替换为你的OpenAI API密钥和API端点
       // String apiKey = "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";
        String apiEndpoint = "https://api.openai.com/v1/chat/completions";  // ChatGPT-3.5的API端点
        //构建请求
        JSONObject requestBody1 = createRequestBody(question);
        String stringRequestBody = requestBody1.toString();
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "7890");
        System.setProperty("https.proxyPort", "7890");
        try {
            // 创建URL对象
            URL url = new URL(apiEndpoint);

            // 创建HTTP连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000); // 5秒

            // 设置读取超时时间
            connection.setReadTimeout(10000); // 5秒
            // 发送请求体
            connection.getOutputStream().write(stringRequestBody.getBytes("UTF-8"));

            // 获取API响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 关闭连接
            connection.disconnect();

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getJsonContent(String response) {

        if (!response.isEmpty()) {
            JSONObject jsonObject = JSON.parseObject(response);
            JSONArray choices = (JSONArray) jsonObject.get("choices");
            JSONObject jsonObject1 = (JSONObject) choices.get(0);
            JSONObject message = (JSONObject) jsonObject1.get("message");
            logger.info("chatGpt: " + message.get("content").toString());
            return message.get("content").toString();
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        String date = chatGpt("北京时间,2023年11月25日，今天星期几");
        String jsonContent = getJsonContent(date);
        System.out.println(jsonContent);
    }

}
