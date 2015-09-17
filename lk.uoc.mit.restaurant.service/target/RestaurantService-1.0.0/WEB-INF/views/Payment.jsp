<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>RMS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2><img src="resources/image/Restaurant-icon.png" class="img-rounded" alt="Cinque Terre" width="60" height="60"> Restaurant Management System</h2>
  <ul class="nav nav-tabs">
     <li ><a href="home"><img src="resources/image/home.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Home</a></li>
     <li class="active" ><a href="payment"><img src="resources/image/payment.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Payment</a></li>
     <li><a  href="activeorder"><img src="resources/image/order.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Order Food</a></li>
     <li><a  href="order"><img src="resources/image/OrderStatus.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">View Order</a></li>
     <li><a  href="admin"><img src="resources/image/admin.jpg" class="img-rounded" alt="Cinque Terre" width="60" height="60">Admin</a></li>
       <div align="right">
        <a href="#" data-toggle="modal" data-target="#login" ><img src="resources/image/user_login-icon.gif" title="Describe Image Link Destination" width="60" height="60" />Login</a>
      </div>
   </ul>

  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
    </br>
      <h4>Payment</h4>

              <div class="row">
                  <div class="col-sm-8">
          <table class="table table-striped">
                 <thead>
                            <tr>
                              <th>Order No</th>
                              <th>Customer Name</th>
                              <th>Order Amount</th>

                            </tr>
                          </thead>
                           <tbody>
                 <c:forEach items="${orderList}" var="order">

                      <tr>
                        <td><c:out value="${order.orderNo}"/></td>
                        <td></td>
                       <td></td>
                       <td> <button type="button" class="btn btn-info btn-md" data-toggle="modal" data-target="#myModal">Pay</button></td>

                         </tr>
                         </c:forEach>
            </tbody>
                  </table>
                   </div>
                    <div class="col-sm-4">  </div>
                    </div>

      </div>
    </div>

</div>
<%@ include file="Login.jsp" %>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Payment</h4>
        </div>
        <form role="form">
        <div class="modal-body"> <div class="form-group">
            <label for="sel1">Please Select Paymet Methed:</label>
            <select class="form-control" id="sel1">
              <option>Cash</option>
              <option>Credit Card</option>
             </select>
             <label for="sel1">Please Enter Paymet Amount</label>
              <input type="text" class="form-control" id="usr">
          </div>
           </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" >Pay</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

</body>
</html>