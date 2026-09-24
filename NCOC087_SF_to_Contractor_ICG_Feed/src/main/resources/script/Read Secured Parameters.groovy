import com.sap.it.api.ITApi
import com.sap.it.api.ITApiFactory
import com.sap.it.api.securestore.*;
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {
    //Body 
      def body = message.getBody();
      
	  def service = ITApiFactory.getApi(SecureStoreService.class, null);

       //Name of secure parameter
       def S_ClientID = service.getUserCredential("ICG_ClientID"); 
        if (S_ClientID == null)
        { throw new IllegalStateException("No value found for alias 'ICG_ClientID'");             
        }
        else{
            CI_password= new String(S_ClientID.getPassword());
            }
			
		def S_ClientSecret = service.getUserCredential("ICG_ClientSecret"); 
        if (S_ClientSecret == null)
        { throw new IllegalStateException("No value found for alias 'ICG_ClientSecret'");             
        }
        else{
            CS_password= new String(S_ClientSecret.getPassword());
            }
			
		def S_UserID = service.getUserCredential("ICG_UserID"); 
        if (S_UserID == null)
        { throw new IllegalStateException("No value found for alias 'ICG_UserID'");             
        }
        else{
            UN_password= new String(S_UserID.getPassword());
            }
			
		def S_Password = service.getUserCredential("ICG_Password"); 
        if (S_Password == null)
        { throw new IllegalStateException("No value found for alias 'ICG_Password'");             
        }
        else{
            P_password= new String(S_Password.getPassword());
            }
            
        def Sc_Password = service.getUserCredential("ICG_Scope"); 
        if (Sc_Password == null)
        { throw new IllegalStateException("No value found for alias 'ICG_Scope'");             
        }
        else{
            Scope_Password= new String(Sc_Password.getPassword());
            }

        //store it in property which can be used in later stage of your integration process.
        message.setProperty("SP_ClientID", CI_password);
		message.setProperty("SP_ClientSecret", CS_password);
		message.setProperty("SP_UserName", UN_password);
		message.setProperty("SP_Password", P_password);
		message.setProperty("SP_Scope",Scope_Password)
       return message;
}