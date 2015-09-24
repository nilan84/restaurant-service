<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../Admin.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
    $('#example').DataTable();
} );
</script>
</head>
<body>
<div class="container">
 <div class="col-sm-12">
<div class="jumbotron">
<h4>View Customer</h4>

 <button type="button" class="btn btn-info btn-md" data-toggle="modal" onclick="location.href = 'addcustomerview';" >Add Customer</button>
 </br>
 </br>
 <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0"  >
       <thead>
                  <tr>
                    <th>Customer Name</th>
                    <th>Customer Email</th>
                    <th></th>
                  </tr>
                </thead>
                 <tbody>

       <c:forEach items="${customers}" var="customer">

            <tr>
              <td><c:out value="${customer.customerName}"/></td>
              <td><c:out value="${customer.customerEmail}"/></td>
              <td><button type="button" class="btn btn-info btn-md" data-toggle="modal"  onclick="location.href = 'editcustomerview?custid=${customer.customerId}';" >Edit</button></th>
            </tr>


           </c:forEach>
           </tbody>

        </table>
        </div>
        </div>
        </div>
  </div>
</div>

<%@ include file="../Footer.jsp" %>





