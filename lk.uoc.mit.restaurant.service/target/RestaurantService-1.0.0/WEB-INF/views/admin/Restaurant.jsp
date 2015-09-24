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
<h4>View Restaurant</h4>

 </br>
 </br>
 <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0"  >
       <thead>
                  <tr>
                    <th>Resturant Name</th>
                    <th>Latitude</th>
                    <th>Longitude</th>
                    <th></th>
                  </tr>
                </thead>
                 <tbody>

       <c:forEach items="${restaurants}" var="resturant">

            <tr>
              <td><c:out value="${resturant.resturantName}"/></td>
              <td><c:out value="${resturant.latitude}"/></td>
               <td><c:out value="${resturant.longitude}"/></td>
              <td><button type="button" class="btn btn-info btn-md" data-toggle="modal"  onclick="location.href = 'editrestaurantview?restid=${resturant.resturantId}';" >Edit</button></th>
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





