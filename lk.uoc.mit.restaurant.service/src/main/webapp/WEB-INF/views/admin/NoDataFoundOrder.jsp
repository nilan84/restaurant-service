<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>RMS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?sensor=false&callback=initialize"></script>
  <script src="https://www.google.com/jsapi?.js"></script>
  <script type="text/javascript"
    src="http://maps.googleapis.com/maps/api/js?file=api&v=3&key=AIzaSyD_4-wznjqddKpTiiDhxpUnj0flGAWkkvA&sensor=false">
  </script>


</head>
<body>

<div class="container">
  <h2><img src="resources/image/Restaurant-icon.png" class="img-rounded" alt="Cinque Terre" width="60" height="60"> Restaurant Management System</h2>
 <ul class="nav nav-tabs">
    <li><a href="home"><img src="resources/image/home.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Home</a></li>
    <li><a href="payment"><img src="resources/image/payment.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Payment</a></li>
    <li><a  href="activeorder"><img src="resources/image/order.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Order Food</a></li>
    <li class="active"><a  href="order"><img src="resources/image/OrderStatus.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">View Order</a></li>
    <li><a  href="admin"><img src="resources/image/admin.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Admin</a></li>
         <div align="right">
                      <c:choose>
                      <c:when test="${not empty username}">
                      <a href="logout" ><img src="resources/image/user_login-icon.gif" title="Describe Image Link Destination" width="60" height="60" />
                      Hi <c:out value="${username}"></c:out> </a>
                      </c:when>
                      <c:otherwise>
                      <a href="#" data-toggle="modal" data-target="#login" ><img src="resources/image/user_login-icon.gif" title="Describe Image Link Destination" width="60" height="60" />
                        Login </a>
                      </c:otherwise>
                    </c:choose>
                    </a>
                  </div>
  </ul>


  </div>
<%@ include file="../Login.jsp" %>
</body>
</html>