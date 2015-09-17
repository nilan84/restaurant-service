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
<h4>View Food</h4>

 <button type="button" class="btn btn-info btn-md" data-toggle="modal" onclick="location.href = 'fooditemadd';" >Add Food</button>
 </br>
 </br>
 <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0"  >
       <thead>
                  <tr>
                    <th>Food Name</th>
                    <th>Food Price</th>
                    <th></th>
                  </tr>
                </thead>
                 <tbody>

       <c:forEach items="${foodList}" var="food">

            <tr>
              <td><c:out value="${food.foodName}"/></td>
              <td><c:out value="${food.foodPrice}"/></td>
              <td><button type="button" class="btn btn-info btn-md" data-toggle="modal"  onclick="location.href = 'fooditemedit?foodId=${food.foodNo}';" >Edit</button></th>
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


