<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.BufferedReader, java.io.InputStreamReader, java.net.HttpURLConnection, java.net.URL" %>

<%
  // API endpointi
  String apiEndpoint = "http://localhost:8081/api/v1/user/login";

  // Formdan gönderilen verileri alın
  String mail = request.getParameter("mail");
  String password = request.getParameter("password");

  // API endpointine isteği gönderin
  URL url = new URL(apiEndpoint);
  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
  conn.setRequestMethod("POST");
  conn.setDoOutput(true);

  // İstek gövdesine verileri yazın
  String data = "mail=" + mail + "&password=" + password;
  conn.getOutputStream().write(data.getBytes("UTF-8"));

  // İstek yanıtını okuyun
  BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
  String inputLine;
  StringBuilder stringBuilder = new StringBuilder();
  while ((inputLine = in.readLine()) != null) {
    stringBuilder.append(inputLine);
  }
  in.close();

  // API yanıtını kullanın
  String apiResponse = response.toString();
  out.println(apiResponse);
%>