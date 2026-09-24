import com.sap.gateway.ip.core.customdev.util.Message;


def Message processData(Message message) {
	
	//Read Input Properties
	def properties = message.getProperties();
	def sender = properties.get("sender");
	def recipients = properties.get("recipients");
	
	//Read Runtime & other custom Headers
	def headers = message.getHeaders();
	def correlationId =  headers.get("SAP_MplCorrelationId");
	def messageId = headers.get("SAP_MessageProcessingLogID");
	def XOM_TenantHost = headers.get("XOM_TenantHost");
	def XOM_TenantDomain = headers.get("XOM_TenantDomain");
	
	//Prepare CPI Monitoring URI   
	def linktoMonitoringPage = 'https://'+XOM_TenantHost+'.integrationsuite.'+XOM_TenantDomain+'/shell/monitoring/Messages/{"identifier":'+'"'+messageId+'"'+'}'
	
   
	//Set the Headers 
	message.setHeader("XOM_Sender", sender);
	message.setHeader("XOM_Recipients", recipients);
	message.setHeader("XOM_CorrelationId", correlationId);
	message.setHeader("XOM_MessageId", messageId);
	message.setHeader("XOM_LinktoMonitoringPage", linktoMonitoringPage);

	return message;
}