<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
  <title>RMS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

  <script type="text/javascript" src="resources/js/jquery/jquery-1.6.4.min.js"></script>
  <script type="text/javascript" src="resources/js/jquery/jquery.dataTables.min.js"></script>

<style>
    .error {
        color: red; font-weight: bold;
    }
</style>




<div class="container">
<h2><img src="resources/image/Restaurant-icon.png" class="img-rounded" alt="Cinque Terre" width="60" height="60"> Restaurant Management System</h2>

 <ul class="nav nav-tabs">
    <li><a href="home"><img src="resources/image/home.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Home</a></li>
    <li><a href="payment"><img src="resources/image/payment.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Payment</a></li>
    <li><a  href="activeorder"><img src="resources/image/order.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Order Food</a></li>
    <li><a  href="order"><img src="resources/image/OrderStatus.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">View Order</a></li>
    <li class="active" ><a  href="admin"><img src="resources/image/admin.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Admin</a></li>
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
  <ul class="nav nav-pills">
      <li ><a href="#home">Customer</a></li>
      <li ><a href="user">User</a></li>
      <li><a href="addfood">Food</a></li>
      <li><a href="addfood">Restaurant</a></li>
      <li><a href="employee">Employee</a></li>
    </ul>

<%@ include file="Login.jsp" %>



