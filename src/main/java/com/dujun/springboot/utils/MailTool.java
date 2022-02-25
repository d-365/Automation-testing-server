package com.dujun.springboot.utils;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.text.Normalizer;
import java.util.Properties;
import java.util.Scanner;

/**
 * author     : dujun
 * date       : 2022/2/7 14:23
 * description: 告诉大家我是干啥的
 */

public class MailTool {
    private static final String From = "859893448@qq.com";//邮件发送人
    private static final String Host = "smtp.qq.com";//邮件传输协议
    private static final String password = "wsrbthmniifobdef";//邮件校验码


    // 发送邮件（不包含附件）
    /**
     * @param recipients 邮件接收人
     * @param Subject 邮件主题
     * @param Content 邮件文本
     */
    public static void sendEmail(String[] recipients,String Subject,String Content){
        //第一步：构造 SMTP 邮件服务器的基本环境
        Properties properties = new Properties();
        properties.setProperty("mail.host", Host);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        //第二步：构造邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            for (String recipient : recipients) {
                mimeMessage.addRecipients(Message.RecipientType.TO, recipient);//设置收信人
                mimeMessage.addRecipients(Message.RecipientType.CC, recipient);//抄送
            }
            mimeMessage.setFrom(From);//邮件发送人
            mimeMessage.setSubject(Subject);//邮件主题
            mimeMessage.setContent(Content, "text/html;charset=utf-8");//正文
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //第三步：发送邮件
        try {
            Transport transport = session.getTransport();
            transport.connect(Host,From, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());//发送邮件，第二个参数为收件人
            transport.close();
        } catch (MessagingException e) {
            System.out.println("邮件发送失败"+e);
        }

    }

    // 发送带附件邮件
    public static void sendEmail(String[] recipients,String Subject,String Content,File[] files,String[] fileNames){
        //第一步：构造 SMTP 邮件服务器的基本环境
        Properties properties = new Properties();
        properties.setProperty("mail.host", Host);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        //第二步：构造邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            for (String recipient : recipients) {
                mimeMessage.addRecipients(Message.RecipientType.TO, recipient);//设置收信人
                mimeMessage.addRecipients(Message.RecipientType.CC, recipient);//抄送
            }
            mimeMessage.setFrom(From);//邮件发送人
            mimeMessage.setSubject(Subject);//邮件主题

            Multipart multipart = new MimeMultipart();

            // 添加附件
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String name = fileNames[i];
                MimeBodyPart attach = new MimeBodyPart();//创建附件1
                FileDataSource fds1 = new FileDataSource(file);//构造附件一的数据源
                DataHandler dh1 = new DataHandler(fds1);//数据处理
                attach.setDataHandler(dh1);//设置附件一的数据源
                attach.setFileName(name);//设置附件一的文件名
                multipart.addBodyPart(attach);
            }

            // 设置正文
            MimeBodyPart mimeBodyText = new MimeBodyPart();
            mimeBodyText.setText(Content);


            multipart.addBodyPart(mimeBodyText);
            mimeMessage.setContent(multipart);


        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //第三步：发送邮件
        try {
            Transport transport = session.getTransport();
            transport.connect(Host,From, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());//发送邮件，第二个参数为收件人
            transport.close();
        } catch (MessagingException e) {
            System.out.println("邮件发送失败"+e);
        }

    }

    public static void main(String[] args) {
        String[] s = new  String[]{"17637898368@163.com"};
        File[] file = new File[]{
                new File("C:\\Users\\dujun\\Pictures\\usually\\photo.jpg"),
                new File("C:\\Users\\dujun\\Pictures\\usually\\courgette.log")
        };
        String[] names = new String[]{
                "1.png","courgette.log"
        };
        sendEmail(s,"吃","ss",file,names);
    }

}

