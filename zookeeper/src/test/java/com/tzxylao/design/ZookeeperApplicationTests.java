package com.tzxylao.design;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperApplicationTests {

	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void testMail() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		//发件人
		helper.setFrom("15068610616@163.com");
		//收件人
		helper.setTo("15068610616@163.com");
		//标题
		helper.setSubject("subject");
		//文本
		helper.setText("message text");
		//附件
		helper.addAttachment("downFile",new File("D:\\cygwin64\\home\\workspace3\\learn-demo\\zookeeper\\src\\test\\java\\com\\tzxylao\\design\\ZookeeperApplicationTests.java"));
		mailSender.send(mimeMessage);
	}

}
