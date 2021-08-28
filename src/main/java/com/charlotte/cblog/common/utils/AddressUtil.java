package com.charlotte.cblog.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.charlotte.cblog.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AddressUtil {

    private static final Logger log = LoggerFactory.getLogger(AddressUtil.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip)
    {
        String address = UNKNOWN;
        // 内网不查询
        if (IpUtil.internalIp(ip))
        {
            return "内网IP";
        }
        try
        {
            String rspStr = HttpUtil.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
            if (StrUtil.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return UNKNOWN;
            }
            JSONObject obj = JackSonUtil.jsonToPojo(rspStr, JSONObject.class);
            String region = obj.getStr("pro");
            String city = obj.getStr("city");
            return String.format("%s %s", region, city);
        }
        catch (Exception e)
        {
            log.error("获取地理位置异常 {}", ip);
        }

        return address;
    }
}
