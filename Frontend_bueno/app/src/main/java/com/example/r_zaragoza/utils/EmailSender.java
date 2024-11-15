package com.example.r_zaragoza.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.r_zaragoza.utils.Email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender extends AsyncTask<Void, Void, Boolean> {
    private String toEmail;
    private String subject;
    private String body;

    // Credenciales de la cuenta de la aplicación
    private static final String FROM_EMAIL = "r.zaragoza.info@gmail.com";
    private static final String PASSWORD = "pxywdvhvegbvolxl";

    public EmailSender(Email email) {
        this.toEmail = email.getDestinatario();
        this.subject = email.getEncabezado();
        this.body = email.getCuerpo();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Log.e("llego", "llego");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            System.out.println("Email sent successfully");
        } else {
            System.out.println("Email sending failed");
        }
    }
}