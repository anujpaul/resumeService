package com.anuj.reseume.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
	private final ChatClient chatClient;
	
	 public ChatService(ChatClient.Builder chatClientBuilder) {
	        this.chatClient = chatClientBuilder.build();
	    }

	 public String greet(String userInput) {
		return this.chatClient.prompt()
	            .user(userInput)
	            .call()
	            .content();
	 }

	 public String getResume() {
		String resume = "Anuj Paul\r\n"
				+ "Technical Lead / Solution Architect\r\n"
				+ "Contact: 608-616-9597 | anujpaulit@gmail.com\r\n"
				+ "\r\n"
				+ "Location: Madison, WI (Ready to relocate)\r\n"
				+ "\r\n"
				+ "GitHub:github.com/anujpaul | Azure DevOps:dev.azure.com/anujpaul19 | LinkedIn:linkedin.com/in/anuj-paul\r\n"
				+ "\r\n"
				+ "Portfolio:https://anujpaul.github.io/resume/\r\n"
				+ "Professional Summary\r\n"
				+ "Technical Lead Engineer with 15+ years of expertise in Retail, specializing in Java, Spring Boot, Microservices, Azure DevOps, and Angular. Skilled in designing scalable microservices, integrating APIs, and managing deployments on Azure Kubernetes Service (AKS).\r\n"
				+ "\r\n"
				+ "Key Achievements\r\n"
				+ "30% faster POS transactions via performance tuning.\r\n"
				+ "Reduced cloud cost by $5,000/month (33%) using ADF + Synapse and DB optimization.\r\n"
				+ "Resolved gift card activation issues improving customer experience.\r\n"
				+ "Migrated applications to new AKS cluster, enabled auto-scaling and SLB.\r\n"
				+ "Automated real-time sync between Azure DB & PostgreSQL using SymmetricDS.\r\n"
				+ "Technical Skills\r\n"
				+ "Domain	Retail, Finance\r\n"
				+ "Cloud	Azure, AWS, Google\r\n"
				+ "Languages	Java, SQL, JavaScript, HTML, CSS, Python, NodeJS, Angular\r\n"
				+ "Frameworks	Spring Boot, Hibernate, REST, SOAP, Express\r\n"
				+ "DevOps	Git, Azure DevOps CI/CD, Kubernetes, Docker, Jenkins\r\n"
				+ "DB Systems	PostgreSQL, Azure SQL, H2, Cosmos DB\r\n"
				+ "Testing Tools	JUnit, Postman, JMeter, Insomnia\r\n"
				+ "Methodologies	Agile, Scrum\r\n"
				+ "Queueing System	Azure Storage Queue, Service Bus, Event Hub\r\n"
				+ "Security	Azure OAuth, JWT, Spring Security\r\n"
				+ "Monitoring Tools	Dynatrace, SCOM, Azure insight\r\n"
				+ "Experience\r\n"
				+ "Duluth Trading Company - Technical Lead Engineer (Apr 2018 – Present)\r\n"
				+ "Designed and implemented microservices architecture for Retail POS.\r\n"
				+ "Developed REST APIs integrating MuleSoft, Manhattan WMS.\r\n"
				+ "Built Docker images & deployed to AKS.\r\n"
				+ "Improved DB performance for mission-critical operations.\r\n"
				+ "Led team migration from legacy to Java architecture.\r\n"
				+ "Implemented logging & exception handling (SLF4J, Logback).\r\n"
				+ "Performed code reviews ensuring best practices.\r\n"
				+ "Sears Holdings - Lead Engineer (Oct 2014 – Apr 2018)\r\n"
				+ "Migrated financial mainframe app to Java.\r\n"
				+ "Integrated financial systems with real-time APIs.\r\n"
				+ "Optimized legacy code improving performance.\r\n"
				+ "Ascena Retail Group - Java Developer (Jan 2014 – Oct 2014)\r\n"
				+ "POC for re-hosting vs rewriting mainframe apps.\r\n"
				+ "Analyzed and created the design document\r\n"
				+ "Migrated retail merchandising application to Java Spring framework based application.\r\n"
				+ "SUPERVALU - Support Engineer(Jan 2010 – Nov 2013)\r\n"
				+ "Provided application support for mainframes and Microfocus based application.\r\n"
				+ "Promoted to Lead and traveled to Mexico for 2 years for establishing a support team.\r\n"
				+ "Education\r\n"
				+ "Master of Computer Applications(M.C.A.)– Visvesvaraya Technological University, Belgaum, Karnataka, IN(2009)\r\n"
				+ "Bachelor of Science(B.Sc.) in Computer Science – Dr. Bhimrao Ambedkar University, Agra, Uttar Pradesh, IN(2005)\r\n"
				+ "Certifications\r\n"
				+ "AZ-900 Azure Fundamentals – 2023\r\n"
				+ "Power BI Data Analyst PL-300 – 2023\r\n"
				+ "Professional Scrum Master – 2022\r\n"
				+ "Preparing for\r\n"
				+ "AZ-204: Azure Developer Associate\r\n"
				+ "AZ-400: Designing and Implementing Microsoft DevOps Solutions\r\n"
				+ "Personal Details\r\n"
				+ "Visa: H1B valid until Oct-2027 (I-140 Approved)\r\n"
				+ "Moved to US: Jan 2016\r\n"
				+ "Notice Period: 14 days";
		return resume;
	 }
	 
	 

}
