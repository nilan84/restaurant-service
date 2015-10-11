<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../Food.jsp" %>

<script type="text/javascript">
$(document).on("click", ".open-Dialog", function () {
     var foodId = $(this).data('id');
     var foodprice=$(this).data('price');
     $("#foodId").val( foodId );
     $("#unitPrice").val( foodprice );
     $("#originalPrice").val( foodprice );
     $("#description").val( "" );
     $("#sel").val( "0" );
     $("#noOfitem").val( "1" );
     $('#myModal').modal('show');
});

function getComboA(sel) {
    var value = sel.value;
    var unitPrice = document.getElementById("originalPrice").value;
    var discount=(unitPrice*value)/100;
    $("#description").val( "Descount "+value+"%" );
    var newPrice=unitPrice-discount;
    $("#unitPrice").val( newPrice );


}
</script>

</head>
<body>
  <div class="tab-content">
     <form:errors path="*" cssClass="error"/>
    <div id="home" class="tab-pane fade in active">
     </br>
      <p><h4>Food Menu</h4></p>
      <div class="row">
        <div class="col-sm-8">
      <table class="table table-striped">
       <thead>
                  <tr>
                    <th>Food Name</th>
                    <th>Food Price</th>
                     <th>Image</th>
                    <th></th>
                  </tr>
                </thead>
                 <tbody>
       <c:forEach items="${foodList}" var="food">

            <tr>

              <th><c:out value="${food.foodName}"/></th>
              <th><c:out value="${food.foodPrice}"/></th>
                <th><img src="resources/food/${food.foodNo}.jpg" width="100" height="100"/></th>
             <th> <button type="button" class="open-Dialog btn btn-info btn-md" data-toggle="modal" data-id="${food.foodNo}" data-price="${food.foodPrice}" data-target="#myModal">Add</button></th>
            </tr>


           </c:forEach>
           </tbody>

        </table>
        </div>
        <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-body"><h4>Selected Food</h4></br>
            <c:out value="${orderNo}"/>
             <table class="table table-striped">
                   <thead>
                              <tr>
                                <th>Food Name</th>
                                <th>Food Price</th>
                                <th>Quantity</th>
                                <th>Remove</th>
                              </tr>
                            </thead>
                   <c:forEach items="${orderList}" var="order">

                                        <tr>

                                          <th><c:out value="${order.food.foodName}"/></th>
                                          <th><c:out value="${order.unitPrice}"/></th>
                                          <th><c:out value="${order.noOfitem}"/></th>
                                         <th> <a href="deletefood?id=${orderNo}&foodid=${order.foodId}" class="btn btn-success btn-sm">Remove</a></th>
                                        </tr>


                   </c:forEach>

            </table>
            <a href="canfirmorder?id=${orderNo}" class="btn btn-success btn-md">Order</a>
            </div>
          </div></div>
        </div>

    </div>

</div>



  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Food Item</h4>
        </div>
        <div class="modal-body">
        <form:form method="POST" action="orderfood" class="form-horizontal" id="contactForm"  commandName="orderFoodItem">

                                           <form:hidden path="foodId" class="form-control" />
                                           <form:hidden path="orderNo" class="form-control" value="${orderNo}"/>
                                            <div class="form-group">
                                             <form:label path="unitPrice" class="control-label col-sm-4">Original Price</form:label>
                                             <div class="col-sm-4">
                                              <input type="text" name="originalPrice" id="originalPrice" readonly="true" >
                                              </div>
                                             </div>
                                           <div class="form-group">
                                              <form:label path="unitPrice" class="control-label col-sm-4">Sale Food Price</form:label>
                                                <div class="col-sm-4">
                                                <form:input path="unitPrice" class="form-control"/>

                                                 </div>
                                             </div>
                                              <div class="form-group">
                                                 <form:label path="unitPrice" class="control-label col-sm-4">Food Discount</form:label>
                                                 <div class="col-sm-4">
                                                 <select id="sel" onchange="getComboA(this)" >
                                                  <option value="0" selected>Select </option>
                                                  <option value="25">25</option>
                                                  <option value="20">20</option>
                                                  <option value="10">10</option>

                                                </select>

                                                 </div>
                                                 </div>

                                            <div class="form-group">
                                               <form:label path="description" class="control-label col-sm-4">Description</form:label>
                                                <div class="col-sm-4">
                                                <form:input path="description" class="form-control"  />
                                                </div> </div>

                                            <div class="form-group">
                                                <form:label path="noOfitem" class="control-label col-sm-4">Select Qty:</form:label>
                                                 <div class="col-sm-4">

                                                <form:select path="noOfitem" items="${countList}" />


                                                 </div></div>

                                            <div class="form-group">
                                            <form:label path="noOfitem" class="control-label col-sm-4"></form:label>
                                                            <div class="col-sm-4">
                                                    <input type="submit" value="Submit"  class="btn btn-default" />
                                               </div> </div>
                    </form:form>

        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
<%@ include file="../Footer.jsp" %>
