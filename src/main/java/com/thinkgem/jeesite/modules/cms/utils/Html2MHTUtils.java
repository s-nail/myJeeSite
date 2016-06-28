package com.thinkgem.jeesite.modules.cms.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePartDataSource;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.cms.entity.Resume;
import com.thinkgem.jeesite.modules.cms.test.ParseWordTest;

/**
 * MHT文件解析类
 */
public class Html2MHTUtils {

	public static void main(String[] args) throws FileNotFoundException {

		InputStream fis = new FileInputStream(
				"D:\\word\\51job_陈建泽(345462588).doc");
		//mht2html(fis, Resume.FILE_UPLOAD_RESUME);
		mht2html(fis);
	}

	/**
	 * 方法说明：mht转html 
	 * 输入参数：fis
	 * return html文件内容
	 */
	public static String mht2html(InputStream fis) {
		String strText=null;
		try {
			Session mailSession = Session.getDefaultInstance(
					System.getProperties(), null);
			MimeMessage msg = new MimeMessage(mailSession, fis);
			Object content = msg.getContent();
			if (content instanceof Multipart) {
				MimeMultipart mp = (MimeMultipart) content;
				MimeBodyPart bp1 = (MimeBodyPart) mp.getBodyPart(0);
				String strEncodng = getEncoding(bp1);
				// 修改
				if (strEncodng == null) {
					strEncodng = "UTF-8";
				} else {
					strEncodng = strEncodng.replace("\"", "");
				}

				// 获取html文本内容
			    strText = getHtmlText(bp1, strEncodng);
				
				if (strText == null)
					return null;
				
				File parent = null;
				for (int i = 1; i < mp.getCount(); ++i) {
					MimeBodyPart bp = (MimeBodyPart) mp.getBodyPart(i);
					String strUrl = getResourcesUrl(bp);
					if (strUrl == null)
						continue;
					DataHandler dataHandler = bp.getDataHandler();
					MimePartDataSource source = (MimePartDataSource) dataHandler
							.getDataSource();
					File resources = new File(parent.getAbsolutePath()
							+ File.separator + getName(strUrl, i));
					if (saveResourcesFile(resources, bp.getInputStream()))
						strText = JHtmlClear.replace(strText, strUrl,
								resources.getAbsolutePath());
				}
				/*// 返回简历。html的数据库存储路径
				String dbPath = saveHtml(strText, savePath);
				
				strText=ParseHtmlUtils.parseWord(strText);//strText全部汉字
				System.out.println("strText:" + strText);
				
				System.out.println("dbPath::" + dbPath);*/
			}
		} catch (Exception e) {

			System.out.println("有异常");
			e.printStackTrace();
		}
		return strText;
	}

	/**
	 * 方法说明：得到资源文件的name 输入参数：strName 资源文件链接, ID 资源文件的序号 返回类型：资源文件的本地临时文件名
	 */
	public static String getName(String strName, int ID) {
		char separator = '/';
		System.out.println(strName);
		System.out.println(separator);
		if (strName.lastIndexOf(separator) >= 0)
			return format(strName.substring(strName.lastIndexOf(separator) + 1));
		return "temp" + ID;
	}

	/**
	 * 方法说明：得到网页编码 输入参数：bp MimeBodyPart类型的网页内容 返回类型：MimeBodyPart里的网页内容的编码
	 */
	private static String getEncoding(MimeBodyPart bp) {
		if (bp != null) {
			try {
				Enumeration list = bp.getAllHeaders();
				while (list.hasMoreElements()) {
					javax.mail.Header head = (javax.mail.Header) list
							.nextElement();
					if (head.getName().compareTo("Content-Type") == 0) {
						String strType = head.getValue();
						int pos = strType.indexOf("charset=");
						if (pos != -1) {
							String strEncoding = strType.substring(pos + 8,
									strType.length());
							if (strEncoding.toLowerCase().compareTo("gb2312") == 0) {
								strEncoding = "gbk";
							}
							return strEncoding;
						}
					}
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 方法说明：得到资源文件url 输入参数：bp MimeBodyPart类型的网页内容 返回类型：资源文件url
	 */
	private static String getResourcesUrl(MimeBodyPart bp) {
		if (bp != null) {
			try {
				Enumeration list = bp.getAllHeaders();
				while (list.hasMoreElements()) {
					javax.mail.Header head = (javax.mail.Header) list
							.nextElement();
					if (head.getName().compareTo("Content-Location") == 0) {
						return head.getValue();
					}
				}
			} catch (MessagingException e) {

				e.printStackTrace();
			}

		}
		return null;
	}

	/**
	 * 方法说明：格式化文件名 输入参数：strName 文件名 返回类型：经过处理的符合命名规则的文件名
	 */
	private static String format(String strName) {
		if (strName == null)
			return null;
		strName = strName.replaceAll("     ", " ");
		String strText = "\\/:*?\"<>|^___FCKpd___0quot;";
		for (int i = 0; i < strName.length(); ++i) {
			String ch = String.valueOf(strName.charAt(i));
			if (strText.indexOf(ch) != -1) {
				strName = strName.replace(strName.charAt(i), '-');
			}
		}
		return strName;
	}

	/**
	 * 方法说明：保存资源文件 输入参数：resources 要创建的资源文件; inputStream 要输入文件中的流 返回类型：boolean
	 */
	private static boolean saveResourcesFile(File resources,
			InputStream inputStream) {
		if (resources == null || inputStream == null) {
			return false;
		}
		BufferedInputStream in = null;
		FileOutputStream fio = null;
		BufferedOutputStream osw = null;
		try {
			in = new BufferedInputStream(inputStream);
			fio = new FileOutputStream(resources);
			osw = new BufferedOutputStream(new DataOutputStream(fio));
			int b;
			byte[] a = new byte[1024];
			boolean isEmpty = true;
			while ((b = in.read(a)) != -1) {
				isEmpty = false;
				osw.write(a, 0, b);
				osw.flush();
			}
			osw.close();
			fio.close();
			in.close();
			inputStream.close();
			if (isEmpty)
				resources.delete();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("解析mht失败");
			return false;
		} finally {
			try {
				if (osw != null)
					osw.close();
				if (fio != null)
					fio.close();
				if (in != null)
					in.close();
				if (inputStream != null)
					inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("解析mht失败");
				return false;
			}
		}
	}

	/**
	 * 方法说明：得到mht文件的标题 输入参数：mhtFilename mht文件名 返回类型：mht文件的标题
	 */
	public static String getTitle(String mhtFilename) {
		try {

			InputStream fis = new FileInputStream(mhtFilename);
			Session mailSession = Session.getDefaultInstance(
					System.getProperties(), null);
			MimeMessage msg = new MimeMessage(mailSession, fis);
			Object content = msg.getContent();
			if (content instanceof Multipart) {
				MimeMultipart mp = (MimeMultipart) content;
				MimeBodyPart bp1 = (MimeBodyPart) mp.getBodyPart(0);
				String strEncodng = getEncoding(bp1);
				String strText = getHtmlText(bp1, strEncodng);
				if (strText == null)
					return null;
				strText = strText.toLowerCase();
				int pos1 = strText.indexOf("<title>");
				int pos2 = strText.indexOf("</title>");
				if (pos1 != -1 && pos2 != -1 && pos2 > pos1) {
					return strText.substring(pos1 + 7, pos2).trim();
				}
			}
			return null;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 方法说明：得到html文本 输入参数：bp MimeBodyPart类型的网页内容; strEncoding 内容编码 返回类型：html文本
	 */
	private static String getHtmlText(MimeBodyPart bp, String strEncoding) {
		InputStream textStream = null;
		BufferedInputStream buff = null;
		BufferedReader br = null;
		Reader r = null;
		try {
			textStream = bp.getInputStream();
			buff = new BufferedInputStream(textStream);
			r = new InputStreamReader(buff, strEncoding);
			br = new BufferedReader(r);
			StringBuffer strHtml = new StringBuffer("");
			String strLine = null;
			while ((strLine = br.readLine()) != null) {
				strHtml.append(strLine + "\r\n");
			}
			br.close();
			r.close();
			textStream.close();

			return strHtml.toString().replace("gb2312", "UTF-8");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (buff != null)
					buff.close();
				if (textStream != null)
					textStream.close();
			} catch (Exception e) {
				System.out.println("解析mht失败");
			}
		}
		return null;
	}

	/**
	 * 方法说明：保存html文件 输入参数：strText html内容; strHtml html文件名 返回类型：
	 */
	public static String saveHtml(String strText, String savePath) {
		String dbPath = null;
		try {
			String htmlName = new Date().getTime() + ".html";
			dbPath = savePath + htmlName;
			String fullPath = Global.getConfig("basePath") + dbPath;
			FileWriter fw = new FileWriter(fullPath);
			fw.write(strText);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("解析mht转html失败");
		}
		return dbPath;
	}

}

class JHtmlClear {
	public static String replace(String s, String s1, String s2) {
		return s.replace(s1, s2);
	}
}

class JQuery {
	public static String getHtmlText(String strUrl, String strEncoding) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			DataInputStream in = new DataInputStream(
					connection.getInputStream());
			return new String(JQuery.getBytes(in), strEncoding);
		} catch (Exception e) {
			return "";
		}
	}

	public static byte[] downBinaryFile(String s) {
		try {
			URL url = new URL(s);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			DataInputStream in = new DataInputStream(
					connection.getInputStream());
			return JQuery.getBytes(in);
		} catch (Exception e) {
			return null;
		}
	}

	public static byte[] getBytes(InputStream is) throws Exception {
		byte[] data = null;
		Collection chunks = new ArrayList();
		byte[] buffer = new byte[1024 * 1000];
		int read = -1;
		int size = 0;
		while ((read = is.read(buffer)) != -1) {
			if (read > 0) {
				byte[] chunk = new byte[read];
				System.arraycopy(buffer, 0, chunk, 0, read);
				chunks.add(chunk);
				size += chunk.length;
			}
		}
		if (size > 0) {
			ByteArrayOutputStream bos = null;
			try {
				bos = new ByteArrayOutputStream(size);
				for (Iterator itr = chunks.iterator(); itr.hasNext();) {
					byte[] chunk = (byte[]) itr.next();
					bos.write(chunk);
				}
				data = bos.toByteArray();
			} finally {
				if (bos != null) {
					bos.close();
				}
			}
		}
		return data;
	}
}