package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64Decoder {

    public static void saveImage(String base64Data) {
        // 替换下面的字符串为你的Base64编码图像数据
       // String base64Data = "Your_Base64_String_Here";
        // 解码Base64数据
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

        // 将解码后的数据保存到文件
        try {
            Path path = Paths.get("C:\\Users\\57214\\OneDrive\\桌面\\decoded_image.png"); // 这里指定保存文件的路径和名称
            Files.write(path, decodedBytes);
            System.out.println("图片被成功保存到桌面....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        saveImage("");
    }
}
