<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="resources/css/style.css" rel="stylesheet" type="text/css"  media="all" />
<link href="resources/css/bjqs.css" rel="stylesheet" type="text/css"  media="all" />
<link href="resources/css/demo.css" rel="stylesheet" type="text/css"  media="all" />
  <title>RMS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

  		<script type="text/javascript">
  		  $(window).load(function() {
  				$('.flexslider').flexslider({
  				  animation: "slide"
  				});

  				$('.main-menu').prepend('<div id="menu-icon">Menu</div>');
  				/* toggle nav */
  				$("#menu-icon").on("click", function(){
  					$(".sf-menu").slideToggle();
  					$(this).toggleClass("active");
  				});
  			 });
  	 </script>

  <script src="resources/js/bjqs-1.3.min.js"></script>
  <script src="resources/js/libs/jquery.secret-source.min.js"></script>

      <script type="text/javascript">
      jQuery(function($) {

          $('.secret-source').secretSource({
              includeTag: false
          });

      });
      </script>
      <script class="secret-source">
          jQuery(document).ready(function($) {

            $('#banner-fade').bjqs({
              height      : 430,
              width       :1000,
              responsive  : true
            });

          });
      </script>
</head>
<body>

<div class="container">
  <h2><img src="resources/image/Restaurant-icon.png" class="img-rounded" alt="Cinque Terre" width="60" height="60"> Restaurant Management System</h2>
 <ul class="nav nav-tabs">
    <li class="active"><a href="home"><img src="resources/image/home.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Home</a></li>
    <li><a href="payment"><img src="resources/image/payment.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Payment</a></li>
    <li><a  href="food"><img src="resources/image/order.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Order Food</a></li>
    <li><a  href="order"><img src="resources/image/OrderStatus.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">View Order</a></li>
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
  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <div class="clear">

      		</div>
      			<div class="img-slider">
      			<div id="container">

       	 <!--  Outer wrapper for presentation only, this can be anything you like -->
            <div id="banner-fade">
            	<!-- start Basic Jquery Slider -->
              <ul class="bjqs">
                <li><img src="resources/images/slide1.jpg" title="Restaurant Management System"/></li>
                <li><img src="resources/images/slide2.jpg" title="Restaurant Management System"/></li>
                <li><img src="resources/images/slide3.jpg" title="Restaurant Management System"/></li>
              </ul>
              <!-- end Basic jQuery Slider -->
              </div>
      	</div>
      		<div class="grids">
      			<div class="grid1">
      				<h2>Welcome!</h2>
      				<img src="resources/images/1page_img1.jpg">
      				<p>Restaurant Management Systems is dedicated to providing complete POS systems and services for the restaurant management industry. We make it our business to make sure you can concentrate on doing yours.</p>
      				<a href="">readmore</a>
      			</div>
      			<div style="margin:0px 35px;"class="grid1">
      				<h2>Our History</h2>
      				<img src="resources/images/1page_img5.jpg">
      				<p>Popular innovations have also become a trademark – our hot butter cuttlefish, crab claws and devilled dishes have become synonymous with Chinese food in Sri Lanka – often imitated but never replicated.
</p>
      				<a href="">readmore</a>
      			</div>
      			<div class="grid1">
      				<h2>Our Events</h2>
      				<img src="resources/images/1page_img2.jpg">
      				<p>In working directly with meeting planners they have created a one-stop shopping opportunity for groups that want to experience the Gaslamp Quarter without having to work with each business individually...</p>
      				<a href="">readmore</a>
      			</div>
      		</div>
      		<div class="clear">

      		</div>
      		<div class="main">
      			<div class="pin">
      				<h2>Reservations</h2>
      				<h2>+94 11 2750403</h2>

      			</div>
      			<div class="para">
      				<p>In working directly with meeting planners they have created a one-stop shopping opportunity for groups that want to experience the Gaslamp Quarter without having to work with each business individually...</p>

      			</div>
      		</div>
      		<div class="clear">

      		</div>

    </div>

  </div>
</div>
<%@ include file="Login.jsp" %>
</body>
</html>