package com.goodboy.picshop.util;

import com.goodboy.picshop.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成激活邮箱、重新设置密码的工具
 */
public class GenerateLinkUtils {
    /**
     * 生成重新设置密码的链接
     */
    public static String generateResetPwdLink(User user) throws Exception {
        //添加 过期时间，24小时后链接失效
        long endTimes = System.currentTimeMillis()+1*24*3600*1000;
        String para = user.getAccount()+";"+user.getEmail()+";"+endTimes;
        //先加密，再url转码,顺序不能修改
        String encode = UrlUtil.getURLEncoderString(DesUtil.encrypt(para));
        String str="http://10.10.112.170:8080/user/resetPwd?checkCode="+encode;
        return  str;
    }

    /**
     * 生成激活邮箱的链接
     */
    public static String generateEmailLink(String email) throws Exception {
        //添加 过期时间，24小时后链接失效
        long endTimes = System.currentTimeMillis()+1*24*3600*1000;
        String para = email+";"+endTimes;
        //先加密，再url转码,顺序不能修改
        String encode = UrlUtil.getURLEncoderString(DesUtil.encrypt(para));
        String str="http://10.10.112.170:8080/user/emailActive?checkCode="+encode;
        return  str;
    }

    /**
     * 测试生成激活邮箱链接
     */
    @Test
    public void testGenerateEmailLink(){
        try {
           System.out.print(generateEmailLink("xxx@qq.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 对密码重置链接进行解密，获取参数
     * @param checkCode
     * @return
     * @throws Exception
     */
    public static List toSetPayrollPwd2(String checkCode) throws Exception {
        String nickname=null;
        String email=null;
        String enTimes =null;
        //此处直接 des解码
        if(null!=checkCode) {
            try {
                //此处直接 des解码
                String decode = DesUtil.decrypt(checkCode);
                List list = EmailUtil.parseContent(decode,";");
                if (null != list && list.size() > 0) {
                    nickname= (String) list.get(0);
                     email= (String) list.get(1);
                     enTimes = (String)list.get(2);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<String> result = new ArrayList();
        result.add(nickname);
        result.add(email);
        result.add(enTimes);
        return result;
    }


    /**
     * 对邮箱激活链接进行解密，获取参数
     * @param checkCode
     * @return
     * @throws Exception
     */
    public static List parseEmailLink(String checkCode) throws Exception {
        String email=null;
        String enTimes =null;
        //此处直接 des解码
        if(null!=checkCode) {
            try {
                //此处直接 des解码
                String decode = DesUtil.decrypt(checkCode);
                List list = EmailUtil.parseContent(decode,";");
                if (null != list && list.size() > 0) {
                    email= (String) list.get(0);
                    enTimes = (String)list.get(1);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<String> result = new ArrayList();
        result.add(email);
        result.add(enTimes);
        return result;
    }

}