<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../Food.jsp" %>
</head>
<body>
</br>

<button type="button" class="btn btn-info btn-md" data-toggle="modal" onclick="location.href = 'food';" >Add New Order</button>
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
                  <th><c:out value="${order.orderNo}"/></th>
                  <th><c:out value="${order.description}"/></th>
                  <th> <button type="button" class="btn btn-info btn-md" data-toggle="modal" onclick="location.href = 'fooditemadd';" >Edit Order</button></th>
                </tr>


               </c:forEach>
                  </tbody>
            </table>
             </div>
              <div class="col-sm-4">  </div>
              </div>


<%@ include file="../Footer.jsp" %>