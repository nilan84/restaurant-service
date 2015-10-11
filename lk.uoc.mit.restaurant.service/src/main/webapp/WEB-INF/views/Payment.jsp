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
  <script type="text/javascript">
  $(document).on("click", ".open-Dialog", function () {
       var orderId = $(this).data('id');
       var amount=$(this).data('price');
       $("#orderNo").val( orderId );
       $("#amount").val( amount );
       $('#myModal').modal('show');
  });
  </script>



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
      <h4>Payment</h4>


              <div class="row">
                  <div class="col-sm-8">
          <table class="table table-striped">
                 <thead>
                            <tr>
                              <th>Order No</th>
                              <th>Customer Name</th>
                              <th>Order Amount</th>
                              <th></th>
                              <th></th>
                            </tr>
                          </thead>
                           <tbody>
                 <c:forEach items="${orderList}" var="order">

                      <tr>
                        <td><c:out value="${order.orderNo}"/></td>
                        <td><c:out value="${order.customer.customerName}"/></td>
                       <td><c:out value="${order.orderAmount}"/></td>
                       <td> <button type="button" class="btn btn-info btn-md" data-toggle="modal" onclick="location.href = 'guestbill?id=${order.orderNo}';" >Print Guest Bill</button></td>
                       <td> <button type="button" class="open-Dialog btn btn-info btn-md" data-toggle="modal"  data-id="${order.orderNo}" data-price="${order.orderAmount}"  data-target="#myModal">Pay</button></td>

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
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Payment</h4>
        </div>
<div class="modal-body">
           <form:form method="POST"  enctype="multipart/form-data" action="addpayment" class="form-horizontal"  commandName="payment">
            <form:hidden path="orderNo" class="form-control" />
                                          <div class="form-group">
                                             <form:label path="amount" class="control-label col-sm-2">Amount</form:label>
                                               <div class="col-sm-4">
                                               <form:input path="amount" class="form-control"  readonly="true"/>
                                                </div><div class="col-sm-4">
                                              <form:errors path="amount" cssClass="error"/>
                                            </div>  </div>

                                               <div class="form-group">
                                                   <form:label path="paymetType" class="control-label col-sm-2">Payment Type</form:label>
                                                   <div class="col-sm-4">
                                                   <form:select path="paymetType">
                                                   <c:forEach var="enum" items="${enumValues}">
                                                   <c:out value="${enum}"/>
                                                   <option value="${enum}"><c:out value="${enum.value}"/></option>
                                                   </c:forEach>
                                                   </form:select>
                                                   </div></div>

                                           <div class="form-group">
                                                           <div class="col-sm-offset-2 col-sm-10">
                                                   <input type="submit" value="Pay"  class="btn btn-default" />
                                              </div> </div>
                   </form:form>
        </div>
        <div class="modal-footer">

          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

</body>
</html>