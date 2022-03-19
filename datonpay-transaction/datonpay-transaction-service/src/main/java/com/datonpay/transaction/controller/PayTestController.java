package com.datonpay.transaction.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付宝接口对接测试类
 * @author Administrator
 * @version 1.0
 **/

@Slf4j
@Controller
//@RestController//请求方法响应统一json格式
public class PayTestController {

    //沙箱应用APPID
    String APP_ID = "2021000119619227";
    //沙箱应用私钥
    String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCEiujnj6/YCFOz5i74ijS7PuT2hYJQdV/YCP1NrJ6a6G3IbCOLKBuN7AAxJLKompqL1yqJxTK/Rn17xb3Y+s+Mu+vZZwGQM6aWsZhjd9VM5zp55wyNNyMdtUYy+kUSMxQrbe/mHBJBxvjQMhWvB9ia1gu+CVydSV3MYRBnKlZ6zqVvfMwzgqh7dwEpwzbQ+0iR0pD+0Ghtc7IfEF3qkQzDZR9FBC1Fy2coMSt3+HAHLtzZI/D0gT1JL2J0cLwGwLi9npxASNlacLpWdf9TeVuT+DS/HUaSYS6KkreDqLphwhGQjEoqzLL0bDj+r7Bz6hGLaHpxARzihToRUKG5KwwjAgMBAAECggEAdrOFFGTdoccLV3ENne8AfLU+Dv3QkEgeFvcuC5NN1APW1gjq6CR6UOcL4PcxvcgAIr8HTK073ou9s8yLUriEilB+T4xtnPx09D6G8IYQlDf9adEfr5SysVd4UA1NMJhoNuDVWarIOvp8ONCyFLgdJ8YMbBwsQnzDMVe4ffwvyPjUSAeo0JQaR/kil5vlaNGh3hCvMegqof1mtC/LsxtQ7novRojA1/kxW1UeyiR3IWfTOWh6/Jj8nfzYRVwxr/splbE1Sc9HyIViz7UUpOsHUGAGkh/HletZFl4AEruC2GCFjLUMBZC9NVxAVldy46dr4DQMwpJkXbP7qGJOUWESEQKBgQDyWCPpsqPoe/YI1EbhgzLPNwK7G2KA6dTizJdgCQZO7uUNkKPylZlo6RDPCcHUvAtw6aSfHSjLkSED5W4D8AOUendNpaMdQ8nhEg79TC7TGxS4t14eccy/U6dMRx/7RDHXINOBhN+i4uqK7CiaOMkdTQa7YZXssuoaNh56osdtCwKBgQCMAtxUOXKvhy0lFN2C5u4gG32vv08FdNWIZhFFgSusmRXBGzp/w+6FWgXWT+cj1z1zCilcxFDK+xfWYtnYeTjJ1Q+yhpTJea/yGij1B9rwQ+xbO/D+mvtdm5Cg7g1Lob4R4ExqKa0J5omkL6xg0LKznSyscADH7sW+xJ2buXdcSQKBgGs1Eq4PmPL/45eEUNbyc0e3zj13n9vnlYW/fDe/xkqk5C9cDSsDgxomAImWh2jUxzrsISdCfwmO6kwO8txLEUpd4uk2nTIVCvnGQ1tKmGxLTp9ofvp43VrGZvwXes5tHJ9OpkJlxYkJWTibwFp5RH7x1EiQowJTI7z0tAcMe6yBAoGAfwzBfrQ6u8lAUBvk/jor73rCzM4fGOZyNjnYhme2iALKWNcqeHIFaYDNNzJ+nmlc251m90U1w1uiv+onoGYwAAaVHf092q3P2TOf4unj5I7eVdPI1mEwpDrcPQyHiBAXeAU/kj6LRSar1A9mrtvyrUZ9GWTxcHvdeUTVeFAOXfECgYAo7h2IOs6c2/Ox2sHJ7hPtl4xSfFkNXFeLIxKLLIQqoka1qVaFNxvTrruwwJLqrXrY3/2OllCDgRNoFVQm6u4JTA9Jtch+hdDZ+qOfjtfpiyxbTw+eDYl1VV29YZHHRbqhbtYTjO+nmPxc6Gvnn5esWuecEjgivP87u+PZI9YSEg==";
    //支付宝公钥
    String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAorL+0dCUKsXh0sXM3PrfxYwp8afVbc8cf9R2WtImVnNA66r0B1nekM9ehv58UkUB0O+a1Z5jnanxvX+tn8G+Dbuo/1h19Qh+Ls8JmSZjz0JJhnIIomUENMqlTwKpFlsYXvp/fxgFbuQwYupif6bGXOb8+qOlWJEAt2gO2fVq/PtmU3ce850ZZk2T+eTv9WRcRcWp5/A6Mem6W0o9MWU4lpho/NGGC7Xvnax7A8sE4n/jlnhiMnVqOBGVnVvhPTwHlqUp7jOY7BXsCiOO6dWyC65MMlvXzz5UVWpGwlsvLs1yro3/8TbJTCQmBsz02RkQmeTnzazNH55ot9xSwDiEkQIDAQAB";
    //签名算法类型
    String CHARSET = "utf-8";
    //支付宝接口的网关地址，正式"https://openapi.alipay.com/gateway.do"
    String serverUrl = "https://openapi.alipaydev.com/gateway.do";
    //签名算法类型
    String sign_type = "RSA2";

    @GetMapping("/alipaytest")
    public void alipaytest(HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse) throws ServletException, IOException {
        //构造sdk的客户端对象
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, sign_type); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
//        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
//        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\"20150420010101017\"," +
                " \"total_amount\":\"88.88\"," +
                " \"subject\":\"Iphone6 16G\"," +
                " \"product_code\":\"QUICK_WAP_PAY\"" +
                " }");//填充业务参数
        String form="";
        try {
            //请求支付宝下单接口,发起http请求
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

}
