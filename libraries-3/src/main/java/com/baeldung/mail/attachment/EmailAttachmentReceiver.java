package com.baeldung.mail.attachment;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailAttachmentReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailAttachmentReceiver.class);
    private static final String IMAP = "imap";
    private static final String MAIL_IMAP_PORT = "mail.imap.port";
    private static final String MAIL_IMAP_HOST = "mail.imap.host";
    private static final String MAIL_IMAP_SOCKET_FACTORY_PORT = "mail.imap.socketFactory.port";
    private static final String MAIL_IMAP_SOCKET_FACTORY_FALLBACK = "mail.imap.socketFactory.fallback";
    private static final String MAIL_IMAP_SOCKET_FACTORY_CLASS = "mail.imap.socketFactory.class";
    private static final String FALSE = "false";
    private static final String JAVAX_NET_SSL_SSL_SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String MULTIPART = "multipart";
    private static final String INBOX = "INBOX";
    private static final String AN_ERROR_OCCURRED_WHILE_CLOSING_CONNECTIONS = "An error occurred while closing connections";
    private static final String AN_ERROR_OCCURRED_WHILE_DOWNLOADING_ATTACHMENTS = "An error occurred while downloading attachments";

    private String host;
    private String port;
    private String userName;
    private String password;

    public EmailAttachmentReceiver(String host, String port, String userName, String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public void downloadEmailAttachmentsTo(String directory) {
        Properties properties = new Properties();

        // server settings
        properties.put(MAIL_IMAP_HOST, host);
        properties.put(MAIL_IMAP_PORT, port);

        // SSL settings
        properties.put(MAIL_IMAP_SOCKET_FACTORY_CLASS, JAVAX_NET_SSL_SSL_SOCKET_FACTORY);
        properties.put(MAIL_IMAP_SOCKET_FACTORY_FALLBACK, FALSE);
        properties.put(MAIL_IMAP_SOCKET_FACTORY_PORT, port);

        // obtaining sessions
        Session session = Session.getDefaultInstance(properties);
        Store store = null;
        Folder inbox = null;
        try {
            // connecting to the mail server
            store = session.getStore(IMAP);
            store.connect(userName, password);

            // opens the inbox folder
            inbox = store.getFolder(INBOX);
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                // filtering message with content type 'multipart'
                if (message.getContentType()
                    .equalsIgnoreCase(MULTIPART)) {
                    checkForAttachmentsAndSave(message, directory);
                }
            }
        } catch (NoSuchProviderException noSuchProviderException) {
            LOGGER.error(AN_ERROR_OCCURRED_WHILE_DOWNLOADING_ATTACHMENTS, noSuchProviderException);
        } catch (MessagingException messagingException) {
            LOGGER.error(AN_ERROR_OCCURRED_WHILE_DOWNLOADING_ATTACHMENTS, messagingException);
        } catch (IOException ioException) {
            LOGGER.error(AN_ERROR_OCCURRED_WHILE_DOWNLOADING_ATTACHMENTS, ioException);
        } finally {
            try {
                if (inbox != null) {
                    inbox.close();
                }
                if (store != null) {
                    store.close();
                }
            } catch (MessagingException messagingException) {
                LOGGER.error(AN_ERROR_OCCURRED_WHILE_CLOSING_CONNECTIONS, messagingException);
            }
        }
    }

    private void checkForAttachmentsAndSave(Message message, String directory) throws IOException, MessagingException {
        Multipart multipart = (Multipart) message.getContent();
        for (int partCount = 0; partCount < multipart.getCount(); partCount++) {
            if (isAttachment(partCount, multipart)) {
                // this is the attachment.
                MimeBodyPart mimeBodyPart = (MimeBodyPart) multipart.getBodyPart(partCount);
                mimeBodyPart.saveFile(directory + File.separator + mimeBodyPart.getFileName());
            }
        }
    }

    private boolean isAttachment(int partCount, Multipart multipart) throws MessagingException {
        MimeBodyPart mimeBodyPart = (MimeBodyPart) multipart.getBodyPart(partCount);
        return Part.ATTACHMENT.equals(mimeBodyPart.getDisposition());
    }
}
