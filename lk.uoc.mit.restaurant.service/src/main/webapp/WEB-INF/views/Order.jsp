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

  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
     </br>
     <h4>View Order</h4>
    </div>
         <div class="row">
            <div class="col-sm-8">
    <table class="table table-striped">
           <thead>
                      <tr>
                        <th>Order No</th>
                        <th>Customer Name</th>
                        <th>Customer Email</th>
                        <th>Order Status</th>
                        <th>View Location</th>
                      </tr>
                    </thead>
                    <tbody>
           <c:forEach items="${orderList}" var="order">

                <tr>
                 <td><c:out value="${order.orderNo}"/></td>
                 <td><c:out value="${order.customer.customerName}"/></td>
                 <td><c:out value="${order.customer.customerEmail}"/></td>
                 <td><c:out value="${order.orderStatus}"/></td>

                 <td> <button  type="button" class="open-Dialog btn btn-info btn-md" data-toggle="modal" data-lat="${order.lat}" data-lng="${order.lng}" data-rlat="6.9016" data-rlng="79.8596" data-target="#myModal">View Location</button></td>
               </tr>


               </c:forEach>
                  </tbody>
            </table>
             </div>
              <div class="col-sm-4">  </div>
              </div>

</div>


      <script>

          $(document).on("click", ".open-Dialog", function () {
               var rvarlat = $(this).data('rlat');
               var rvarlng=$(this).data('rlng');
               var varlat = $(this).data('lat');
               var varlng=$(this).data('lng');

                            var resturant = {lat: rvarlat, lng: rvarlng};
                            var waiter = {lat: varlat, lng: varlng};

                            var map = new google.maps.Map(document.getElementById('map-canvas'), {
                              center: resturant,
                              scrollwheel: false,
                              zoom: 7
                            });

                            var directionsDisplay = new google.maps.DirectionsRenderer({
                              map: map
                            });

                            // Set destination, origin and travel mode.
                            var request = {
                              destination: waiter,
                              origin: resturant,
                              travelMode: google.maps.TravelMode.DRIVING
                            };

                            // Pass the directions request to the directions service.
                            var directionsService = new google.maps.DirectionsService();
                            directionsService.route(request, function(response, status) {
                              if (status == google.maps.DirectionsStatus.OK) {
                                // Display the route on the map.
                                directionsDisplay.setDirections(response);
                              }
                            });

          });





      </script>

<%@ include file="Login.jsp" %>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">View Location</h4>
        </div>
        <div class="modal-body">


<div id="map-canvas" style="height:500px; width:565px;"></div>

        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

</body>
</html>