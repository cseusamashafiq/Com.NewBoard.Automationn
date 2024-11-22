package NewBoard.Automationn;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailUtils {

	public Store connectToGmail(final Properties properties) {
		try {
			// Create the session
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							properties.getProperty("gmail_username"),
							properties.getProperty("gmail_password")
							);
				}
			});

			// Connect to Gmail IMAP store
			Store store = session.getStore(properties.getProperty("mail.store.protocol"));
			System.out.println("Connecting to Gmail...");
			store.connect(
					properties.getProperty("mail.imaps.host"),
					properties.getProperty("gmail_username"),
					properties.getProperty("gmail_password")
					);
			System.out.println("Connected to Gmail successfully!");
			return store;

		} catch (AuthenticationFailedException e) {
			throw new RuntimeException("Authentication failed. Verify email credentials or Gmail security settings.", e);
		} catch (MessagingException e) {
			throw new RuntimeException("Messaging exception: Check Gmail IMAP settings and network connectivity.", e);
		}
	}

	public List<String> getUnreadMessageByFromEmail(Store store, String folderName, String fromEmail, String subjectLine) {
		List<String> messages = new ArrayList<>();
		try {
			Folder folder = store.getFolder(folderName);
			folder.open(Folder.READ_ONLY);

			// Fetch unread messages
			Message[] unreadMessages = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
			for (Message message : unreadMessages) {
				Address[] fromAddresses = message.getFrom();
				String subject = message.getSubject();

				if (fromAddresses != null && fromAddresses[0].toString().contains(fromEmail) && subject.contains(subjectLine)) {
					messages.add(getEmailContent(message));
				}
			}
			folder.close(false);
		} catch (Exception e) {
			throw new RuntimeException("Failed to fetch unread messages from " + fromEmail, e);
		}
		return messages;
	}

	private String getEmailContent(Message message) throws Exception {
		StringBuilder content = new StringBuilder();

		if (message.isMimeType("text/plain")) {
			content.append(message.getContent().toString());
		} else if (message.isMimeType("text/html")) {
			content.append(org.jsoup.Jsoup.parse((String) message.getContent()).text());
		} else if (message.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) message.getContent();
			for (int i = 0; i < multipart.getCount(); i++) {
				BodyPart part = multipart.getBodyPart(i);
				if (part.isMimeType("text/plain")) {
					content.append(part.getContent().toString());
				} else if (part.isMimeType("text/html")) {
					content.append(org.jsoup.Jsoup.parse((String) part.getContent()).text());
				} else if (part.getContent() instanceof Multipart) {
					content.append(getEmailContent((Multipart) part.getContent()));
				}
			}
		} else {
			System.out.println("Unhandled message type: " + message.getContentType());
		}

		return content.toString();
	}

	private String getEmailContent(Multipart multipart) throws Exception {
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < multipart.getCount(); i++) {
			BodyPart part = multipart.getBodyPart(i);
			if (part.isMimeType("text/plain")) {
				content.append(part.getContent().toString());
			} else if (part.isMimeType("text/html")) {
				content.append(org.jsoup.Jsoup.parse((String) part.getContent()).text());
			} else if (part.getContent() instanceof Multipart) {
				content.append(getEmailContent((Multipart) part.getContent()));
			}
		}
		return content.toString();
	}



}
