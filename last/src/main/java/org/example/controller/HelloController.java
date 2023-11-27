package org.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.XunFei;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.example.utils.BaiDu.baiDuBigData;
import static org.example.utils.test.ChatGPT3_5Example.chatGpt;
import static org.example.utils.test.ChatGPT3_5Example.getJsonContent;

@ResponseBody
@Controller
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    @RequestMapping("/helloIFlytek")
    public String helloIFlytek(HttpServletResponse response, String question){
        String s1 ="Access-Control-Allow-Origin";
        response.setHeader(s1, "http://localhost:63342");
        response.setHeader(s1, "http://localhost:5173");
        String answer = "";
        try {
            answer=  XunFei.xunfeiBigData(question);
        } catch (Exception e) {
            logger.error("IFlytek接口调用失败 "+e);
            throw new RuntimeException(e);
        }
       logger.info("调用IFlytek接口成功");
        return "科大讯飞: "+answer;


    }
    @RequestMapping("/helloIBaidu")
    public String helloIBaiduAI(HttpServletResponse response,String question) throws IOException {
        String s1 ="Access-Control-Allow-Origin";
       // response.setHeader(s1, "http://localhost:63342");
        response.setHeader(s1, "http://localhost:5173");
        String answer = null;
        try {
            answer = baiDuBigData(question);
        } catch (IOException e) {
            logger.error("百度接口调用失败 "+e);
            throw new RuntimeException(e);
        }
        logger.info("调用百度接口成功 ");
        return "百度AI: "+answer;
    }
    @RequestMapping("/hellochatGPT")
    public String hellochatGpt(HttpServletResponse response,String question){
        String s1 ="Access-Control-Allow-Origin";
       // response.setHeader(s1, "http://localhost:63342");
        response.setHeader(s1, "http://localhost:5173");
        String gptResponse = chatGpt(question);
        assert gptResponse != null;
        String jsonContent = getJsonContent(gptResponse);
        return "chatGpt:" +jsonContent;
    }
}
