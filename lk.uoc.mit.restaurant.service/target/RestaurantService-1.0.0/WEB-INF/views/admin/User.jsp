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
<h4>View User</h4>
<div class="container">

 <button type="button" class="btn btn-info btn-md" data-toggle="modal" onclick="location.href = 'adduserview';" >Add User</button>
 </br>
 </br>
 <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0"  >
        <thead>
                   <tr>
                     <th>User Name</th>
                     <th>First Name</th>
                     <th>Last Name</th>
                      <th></th>
                   </tr>
                 </thead>
                  <tbody>

        <c:forEach items="${users}" var="user">

             <tr>
               <td><c:out value="${user.username}"/></td>
               <td><c:out value="${user.firstName}"/></td>
                <th><c:out value="${user.lastName}"/></th>
               <td><button type="button" class="btn btn-info btn-md" data-toggle="modal"  onclick="location.href = 'edituserview?userid=${user.userId}';" >Edit</button></th>
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


