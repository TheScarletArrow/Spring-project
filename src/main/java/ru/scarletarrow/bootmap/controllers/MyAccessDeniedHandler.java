//package ru.scarletarrow.bootmap.controllers;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class MyAccessDeniedHandler implements AccessDeniedHandler {
//
//    private Logger LOGGER = LoggerFactory.getLogger(MyAccessDeniedHandler.class);
//    @Override
//    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            LOGGER.info("User " + authentication.getName() + "tired to access " + httpServletRequest.getRequestURI());
//        }
//        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");
//    }
//}
