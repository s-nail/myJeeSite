package com.thinkgem.jeesite.modules.cms.utils;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;

import com.thinkgem.jeesite.modules.cms.entity.Resume;

public class ExchangeServiceUtils {
	
	public static void main(String[] args) throws Exception {
		receiveMail();
	}
	
	public static List<Resume>  receiveMail() throws Exception{
		
		ExchangeService service = new ExchangeService(
				ExchangeVersion.Exchange2010);//新建server版本
		ExchangeCredentials credentials = new WebCredentials(
				"yuqiangjia", "Summer2016", "hengtiansoft.com");
		service.setCredentials(credentials);
		service.setUrl(new URI("https://mail.hengtiansoft.com/ews/Exchange.asmx"));
		Folder folder = Folder.bind(service, WellKnownFolderName.Inbox);
		System.out.println("======" + folder.getDisplayName());
		int count=folder.getTotalCount();//Inbox 邮件总数
		System.out.println("Inbox count -->"+count);
		int unReadCount=folder.getUnreadCount();//Inbox 未读邮件总数
		System.out.println("InboxUnReadCount -->"+unReadCount);
		ItemView view = new ItemView(1);
		FindItemsResults<Item> findItemsResults = service.findItems(
				folder.getId(), view);
		
		parseMessage(service,findItemsResults);
		
		return null;
	}
	/*
	 * 注意：需要穿邮件参数
	 */
	public static void sendMail(ExchangeService service) throws Exception{
		 EmailMessage msg=new EmailMessage(service);
		 msg.setSubject("From Java");//主题
		 msg.setBody(MessageBody.getMessageBodyFromText("Sent using the EWS Managed API."));//内容
		 msg.getToRecipients().add("yuqiangjia@hengtiansoft.com");//发件人
		 msg.getCcRecipients().add("yuqiangjia@hengtiansoft.com");//抄送人
		 msg.send();
	}
	
	public static List<Resume> parseMessage(ExchangeService service,FindItemsResults<Item> findItemsResults) throws Exception{
		
		for (Item item : findItemsResults.getItems()) {
			EmailMessage message = EmailMessage.bind(service, item.getId());
			System.out.println("Sender -->"+message.getSender());
			System.out.println("Senderregex -->"+regex("(?<=<).+?(?=>)", message.getSender().toString()));
			//System.out.println("Message -->"+message.getBody());
			System.out.println("IsRead -->"+message.getIsRead());
			System.out.println("DateTimeSent -->"+message.getDateTimeSent());
		    //message.delete(DeleteMode.MoveToDeletedItems);//删除到垃圾箱
			System.out.println("Sub -->" + item.getSubject());
			
			//item.load();
			//System.out.println(" -->" + item.getBody());

		}
		return null;
		
	}

	public static String regex(String patternString, String res) {
		Pattern pattern = Pattern.compile(patternString);
		Matcher mr = pattern.matcher(res);
		String result = "";
		while (mr.find()) {
			result = mr.group().trim();
		}
		return result;
	}
}
