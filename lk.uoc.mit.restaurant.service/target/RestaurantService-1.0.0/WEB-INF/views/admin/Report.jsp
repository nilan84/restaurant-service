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
<h4>Report</h4>

 </br>
 </br>
<button type="button" class="btn btn-info btn-md" data-toggle="modal" onclick="location.href = 'getreport';" >Customer Report</button></br></br>
<button type="button" class="btn btn-info btn-md" data-toggle="modal" onclick="location.href = 'pastmovereport';" >Past Move Report</button></br></br>
<button type="button" class="btn btn-info btn-md" data-toggle="modal" onclick="location.href = 'slowmovereport';" >Slow Move Report</button></br></br>

        </div>
        </div>
        </div>
  </div>
</div>

<%@ include file="../Footer.jsp" %>





