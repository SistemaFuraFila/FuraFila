package furaFila.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Authenticator;
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


public class Mail {

    private InternetAddress from;
    private List<InternetAddress> replyTo;
    private List<InternetAddress> to;
    private List<InternetAddress> cc;
    private List<InternetAddress> bcc;
    private String subject;
    private String content;

    /**
     *
     */
    public Mail() {
        replyTo = new ArrayList<InternetAddress>();
        to = new ArrayList<InternetAddress>();
        cc = new ArrayList<InternetAddress>();
        bcc = new ArrayList<InternetAddress>();
    }

    /**
     *
     * @param address
     */
    public void setFrom(String address) throws Exception {
        from = new InternetAddress(address);
    }

    /**
     *
     * @param address
     * @param personal
     */
    public void setFrom(String address, String personal) throws Exception {
        from = new InternetAddress(address, personal);
    }

    /**
     *
     * @param address
     */
    public void addReplyTo(String address) throws Exception {
        replyTo.add(new InternetAddress(address));
    }

    /**
     *
     * @param address
     * @param personal
     */
    public void addReplyTo(String address, String personal) throws Exception {
        replyTo.add(new InternetAddress(address, personal));
    }

    /**
     *
     * @param address
     */
    public void addTo(String address) throws Exception {
        to.add(new InternetAddress(address));
    }

    /**
     *
     * @param address
     * @param personal
     */
    public void addTo(String address, String personal) throws Exception {
        to.add(new InternetAddress(address, personal));
    }

    /**
     *
     * @param address
     */
    public void addCc(String address) throws Exception {
        cc.add(new InternetAddress(address));
    }

    /**
     *
     * @param address
     * @param personal
     */
    public void addCc(String address, String personal) throws Exception {
        cc.add(new InternetAddress(address, personal));
    }

    /**
     *
     * @param address
     */
    public void addBcc(String address) throws Exception {
        bcc.add(new InternetAddress(address));
    }

    /**
     *
     * @param address
     * @param personal
     */
    public void addBcc(String address, String personal) throws Exception {
        bcc.add(new InternetAddress(address, personal));
    }

    /**
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @throws MessagingException
     */
    public void send() throws Exception {

        Properties p = new Properties();

        p.put("mail.debug", "true");
        p.put("mail.password", "furafila2015");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.transport.protocol", "smtps");
        p.put("mail.userName", FuraFilaConstants.EMAIL_OFICIAL);
        p.put("mail.smtp.port", 465);
        p.put("mail.smtp.socketFactory.port", 465);
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.socketFactory.fallback", "true");

        Authenticator a = new SmtpAuthenticator(p.getProperty("mail.userName"), p.getProperty("mail.password"));
        Session s = Session.getDefaultInstance(p, a);

        Message m = new MimeMessage(s);

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachPart = new MimeBodyPart();
        String anexo = FacesContext.getCurrentInstance().getExternalContext().getResource("/resources/FuraFilaLogo1.png").getFile();
        attachPart.attachFile(anexo);
        multipart.addBodyPart(attachPart);

        m.setFrom(from);
        m.setReplyTo(x(replyTo));
        m.setRecipients(Message.RecipientType.TO, x(to));
        m.setRecipients(Message.RecipientType.CC, x(cc));
        m.setRecipients(Message.RecipientType.BCC, x(bcc));
        m.setSentDate(new Date());
        m.setSubject(subject);

        m.setContent(multipart);

        Transport.send(m);

        to.clear();
        
    }

    /**
     *
     * @param internetAddresses
     * @return
     */
    private Address[] x(List<InternetAddress> internetAddresses) {
        Address[] addresses = new Address[internetAddresses.size()];

        for (int i = 0; i < internetAddresses.size(); i++) {
            addresses[i] = internetAddresses.get(i);
        }
        return addresses;
    }

    /**
     *
     * @author wjunior
     */
    private class SmtpAuthenticator extends Authenticator {

        private String userName;
        private String password;

        /**
         *
         * @param userName
         * @param password
         */
        public SmtpAuthenticator(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        /**
         *
         */
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }
}
