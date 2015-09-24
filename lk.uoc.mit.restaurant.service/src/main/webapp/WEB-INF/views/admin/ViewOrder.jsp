<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>

<%@ include file="../Admin.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
    $('#example').DataTable();

} );
</script>
     <script type="text/javascript">
     $(document).on("click", ".open-Dialog", function () {
          var orderId = $(this).data('id');
          var amount=$(this).data('price');
          $("#orderNo").val( orderId );
          $("#orderAmount").val( amount );
          $('#myModal').modal('show');
     });


          $(document).on("click", ".abc", function () {
          var dateVal = document.getElementById("datepicker").value;
          if(!dateVal){alert("Place Select Date");}
           else{
              window.location = "viewallorder?date="+dateVal;}
          });

          $(document).on("click", ".sales", function () {
            var dateVal = document.getElementById("datepicker").value;
            if(!dateVal){alert("Place Select Date");}
            else{
            window.open("salesreport?date="+dateVal);
            }
           });

     </script>
   <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
   <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
   <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
   <script> $(document).ready(function() { $("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' }).val();; }); </script>


</head>
<body>
<div class="container">
 <div class="col-sm-12">
<div class="jumbotron">
<h4>View Any Order</h4>
 </br>
 </br>
  <form> <input id="datepicker" readonly="true" value="${dateselect}"/> <button type="button" class="abc btn btn-info btn-md" data-toggle="modal" >View Order</button> <button type="button" class="sales btn btn-info btn-md"  data-toggle="_blank" >Sales Report</button></form>
 </br>
 <table class="table table-striped">
                  <thead>
                             <tr>
                               <th>Order No</th>
                               <th>Customer Name</th>
                               <th>Order Amount</th>
                               <th>Order Status</th>
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
                         <td><c:out value="${order.orderStatus}"/></td>
                        <td> <button type="button" class="btn btn-info btn-md" data-toggle="modal" onclick="location.href = 'guestbill?id=${order.orderNo}';" >Print Guest Bill</button></td>
                        <td> <button type="button" class="open-Dialog btn btn-info btn-md" data-toggle="modal"  data-id="${order.orderNo}" data-price="${order.orderAmount}"  data-target="#myModal">Status Change</button></td>

                          </tr>
                          </c:forEach>
             </tbody>
        </div>
        </div>
        </div>
  </div>
</div>

  <!-- Modal -->
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Change Status</h4>
        </div>
<div class="modal-body">
          <form:form method="POST"  enctype="multipart/form-data" action="chnagestatus" class="form-horizontal"  commandName="order">
                                         <form:hidden path="orderNo"  />
                                          <div class="form-group">
                                             <form:label path="orderAmount" class="control-label col-sm-3">Order Amount</form:label>
                                               <div class="col-sm-4">
                                               <form:input path="orderAmount" class="form-control"  readonly="true"/>
                                                </div><div class="col-sm-4">
                                              <form:errors path="orderAmount" cssClass="error"/>
                                            </div>  </div>
                                            <br/>
                                             <br/>

                                               <div class="form-group">
                                                   <form:label path="orderStatus" class="control-label col-sm-3">Order Status</form:label>
                                                   <div class="col-sm-4">
                                                   <form:select path="orderStatus">
                                                   <c:forEach var="enum" items="${enumValues}">
                                                   <option value="${enum}"><c:out value="${enum.value}"/></option>
                                                   </c:forEach>
                                                   </form:select>
                                                   </div></div>
                                                    <br/>


                                           <div class="form-group">
                                                           <div class="col-sm-offset-2 col-sm-10">
                                                   <input type="submit" value="Status Change"  class="btn btn-default" />
                                              </div> </div>
                                              <br/>
                                              <br/>
                   </form:form>

            </div>
            <div class="modal-footer">

              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
          </div>
        </div>
      </div>

<%@ include file="../Footer.jsp" %>





