package pers.yicong.foodvenger.filter;


import org.hibernate.annotations.Filter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


@Component
@Filter(name = "timeLogFilter")
public class TimeLogFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
// Time an event in a program to nanosecond precision
        long startTime = 0;


        System.out.println("In My Filter");
        if (httpServletRequest.getRequestURL().indexOf("list") != -1) {
            System.out.println("in search");
            startTime = System.nanoTime();
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        if (httpServletRequest.getRequestURL().indexOf("list") != -1) {


            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("out search with " + elapsedTime);


            BufferedWriter writer = new BufferedWriter(new FileWriter("time.log", true));
            writer.append(" ").append(String.valueOf(elapsedTime)).append("\n");

            writer.close();
        }


    }
}
