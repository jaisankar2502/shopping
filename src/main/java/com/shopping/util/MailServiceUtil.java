/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.util;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGridAPI;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;



/**
 *
 * @author ajmal
 */
public class MailServiceUtil {

    @Value("${mail.smtp.host}")
    private String host;
    @Value("${mail.smtp.port}")
    private String port;
    @Value("${mail.smtp.user}")
    private String userName;
    @Value("${mail.smtp.password}")
    private String password;
    @Value("${mail.smtp.from}")
    private String from;


    @Autowired
    private LanguageUtil languageUtil;

     @Value("${mail.sendgrid.from.email}")
    private String fromSendgridEmail;

    @Value("${mail.sendgrid.from.name}")
    private String fromSendgridName;

//    @Autowired
    private SendGridAPI sendGridAPI;


    public void sendPasswordResetMail(String email, String firstName, String link)
            throws MessagingException, IOException {
        String subject = languageUtil.getTranslatedText("mail.subject", null, "en");
        // String content = firstName + " " + languageUtil.getTranslatedText("hi", null,
        // "en")+"!<br/>" +
        String content = firstName + "様<br/><br/>" + "いつもAIK orderをご利用いただき誠にありがとうございます。<br/><br/>"
                + "↓こちらがパスワード再設定用のアドレスです。<br/>このURLにアクセスしてください。<br/>" + "<br/><br/>" + link
                + "<br/><br/><br/>URLの有効期限は24時間となっておりますのでご注意ください。"
                + "<br/><br/><hr style=\"height:1px;width:600px;text-align:left;margin-left: 0; border:none; color:#000; background-color:#000;\"/>"
                + "このメッセージはのサービスシステムより自動<br/>" + "送信されています。<br/>" + "お心当たりのない場合は、運営会社にご連絡ください。<br/>"
                + "<hr style=\"height:1px;width:300px;text-align:left;margin-left: 0; border:none; color:#000; background-color:#000;\"/>"
                + "※このメールアドレスは送信専用のため、返信は受け付けておりません。<br/><br/>" + "AIK orderお問い合わせ窓口<br/>"
                + " <hr style=\"height:2px;width:150px;text-align:left;margin-left: 0; border:none; color:#000; background-color:#000;\"/>"
                + "株式会社AIK <br/>" + "〒100-6006 東京都千代田区霞が関3-2-5 霞が関ビルディング6F<br/>"
                + "https://aik-security.co.jp/<br/>";
        // System.out.println("mail content" + subject);
        // System.out.println("mail content" + content);
        String receipient = email;
        sendMail(subject, content, receipient);
    }

    public void emailVerification(String email, String firstName, String link) throws MessagingException, IOException {
        String subject = "AIK order|会員登録手続きのご案内";
        // String content = firstName + " " + languageUtil.getTranslatedText("hi", null,
        // "en")+"!<br/>" +
        String content = firstName + "様" + "<br/> <br/>" + "AIK order ご利用いただき誠にありがとうございます。<br/> <br/>"
                + "この度は「AIK order」にご登録いただきありがとうございます。<br/>まだ、ご登録は完了しておりません。<br/> <br/>" + "下記URLにアクセスをお願いいたします。<br/>"
                + link + "<br/> <br/>"
                + "URLの有効期限は24時間となっておりますのでご注意ください。<br/> <br/> <hr style=\"height:1px;width:600px;text-align:left;margin-left: 0; border:none; color:#000; background-color:#000;\"/>"
                + "このメッセージはのサービスシステムより自動<br/>" + "送信されています。<br/>"
                + "お心当たりのない場合は、運営会社にご連絡ください。<br/> <hr style=\"height:1px;width:300px;text-align:left;margin-left: 0; border:none; color:#000; background-color:#000;\"/>"
                + "※このメールアドレスは送信専用のため、返信は受け付けておりません。<br/> <br/>"
                + "AIK orderお問い合わせ窓口<br/> <hr style=\"height:2px;width:150px;text-align:left;margin-left: 0; border:none; color:#000; background-color:#000;\"/>"
                + "株式会社AIK <br/>" + "〒100-6006 東京都千代田区霞が関3-2-5 霞が関ビルディング6F<br/>"
                + "https://aik-security.co.jp/<br/>";

        String receipient = email;
        sendMail(subject, content, receipient);
    }

    public void emailChange(String email, String firstName, String link) throws MessagingException, IOException {
        String subject = "「AIK order」メールアドレス変更手続きのご案内";
        // String content = firstName + " " + languageUtil.getTranslatedText("hi", null,
        // "en")+"!<br/>" +
        String content = firstName + "様" + "<br/> <br/>" + "お世話になっております。<br/> <br/>"
                + "「AIK order」をご利用いただきありがとうございます。<br/>" + "まだ、メールアドレスの変更お手続きは完了しておりません。<br/><br/>"
                + "下記URLにアクセスをお願いいたします。<br/>" + link + "<br/> <br/>"
                + "URLの有効期限は24時間となっておりますのでご注意ください。<br/> <br/> <hr style=\"height:1px;width:300px;text-align:left;margin-left: 0; border:none; color:#000; background-color:#000;\"/>"
                + "このメッセージはAIK orderのサービスシステムより自動<br/>" + "送信されています。<br/>"
                + "本メールにご返信されても、ご返答ができませんのでご了承ください。<br/> <hr style=\"height:1px;width:300px;text-align:left;margin-left: 0; border:none; color:#000; background-color:#000;\"/>";
        System.out.println(content);
        String receipient = email;
        sendMail(subject, content, receipient);
    }
    
    public void orderConfirmation(String email,float amount) throws MessagingException, IOException{
    
        String subject="Order Confirmed";
        String content="You order confirmed with price"+amount;
        
        
        
        sendMailSendGrid(subject, content, email);
        
    }

   
  
    public void sendMail(String subject, String content, String receipient) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from, false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipient));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html; charset=UTF-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
    
    void sendMailSendGrid(String subject, String body, String receipient) throws IOException {        
        Email fromEmail = new Email(fromSendgridEmail, fromSendgridName);
        Email to = new Email(receipient);
        Content content = new Content("text/html", body);
        Mail mail = new Mail(fromEmail, subject, to, content);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sendGridAPI.api(request);
        System.out.println(response.getBody());
    }
    
    
    

    
}
