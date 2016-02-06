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
<h4>Dashboard</h4>

 </br>
 </br>

  <img alt="Google Pie Chart" src=${pieUrl} />&nbsp;&nbsp;&nbsp;
   <img alt="Google Pie Chart" src=${pie2Url} />

   </br>
   </br>
      <img alt="Google Pie Chart" src=${barPastMoveUrl} />&nbsp;&nbsp;&nbsp;
      <img alt="Google Pie Chart" src=${barSlowMoveUrl} />
     </br>
     </br>
    <img alt="Google Pie Chart" src=${lineUrl} />&nbsp;&nbsp;&nbsp;
        <img alt="Google Pie Chart" src=${barUrl} />
        </div>
        </div>
        </div>
  </div>
</div>

<%@ include file="../Footer.jsp" %>





