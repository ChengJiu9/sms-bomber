package cn.zxf.sms_bomber.read_file;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengxf on 2019/8/5.
 */
@Slf4j
public class ReadFile {

    private static final String
            FILE_NAME = "mobile-list.md";

    public static List<String> mobiles() {
        try {
            URL url = ReadFile.class.getResource("/" + FILE_NAME);
            String file = url.getFile();
            log.info("file-url: [{}] \n file: [{}]", url, file);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            List<String> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.startsWith("- ")) {
                    list.add(line.substring(2));
                }
            }
            return list;
        } catch (Exception e) {
            log.info("读取文件[{}]出错", FILE_NAME, e);
            return List.of();
        }
    }

}
