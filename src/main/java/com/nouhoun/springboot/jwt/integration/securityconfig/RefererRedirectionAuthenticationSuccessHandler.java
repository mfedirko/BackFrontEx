//package com.nouhoun.springboot.jwt.integration.securityconfig;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import com.nouhoun.springboot.jwt.integration.domain.Role;
//
////@Component
//public class RefererRedirectionAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {
// 
////	private Logger logger = LoggerFactory.getLogger(this.getClass());
////	
//
//	
//	@Autowired 
//	FakeUserDetailsService userDetailsService;
//
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request,
//			HttpServletResponse response, Authentication authentication)
//			throws IOException, ServletException {
//        //set our response to OK status
//        response.setStatus(HttpServletResponse.SC_OK);
//
//        boolean admin=false;
//        
//    
//       
//        
//        if(userDetailsService==null) System.out.println("wat wat wat !#?!?#!?$?");
//       List<Role> roles=userDetailsService.getCurrentUser().getRoles();
//       if(roles==null||roles.isEmpty()) System.out.println("roles null poop poop.");
//       
//         for (Role r:roles){
//        	 System.out.println(r.getRoleName());
//        	 if(r.getRoleName().equals("ADMIN_USER")){
//        		 admin=true;
//        		 break;
//        	 }
//         }
//        if(admin){
//        	response.sendRedirect("/admin");
//        }else{
//        	response.sendRedirect("/login.jsp");
//        }
//	}
//}