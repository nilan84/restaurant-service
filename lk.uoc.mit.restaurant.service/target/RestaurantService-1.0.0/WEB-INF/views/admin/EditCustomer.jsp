<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ include file="../Admin.jsp" %>
</head>
<body>
<div class="container">
 <div class="col-sm-12">
<div class="jumbotron">
<h4>Edit Employee</h4>

  <form:form method="POST"  enctype="multipart/form-data" action="editemployee" class="form-horizontal" id="contactForm"  commandName="employee">
                     <div class="form-group">
                                                      <form:label path="empName" class="control-label col-sm-2">Employee Name</form:label>
                                                        <div class="col-sm-4">
                                                        <form:input path="empName" class="form-control"/>
                                                         </div><div class="col-sm-4">
                                                       <form:errors path="empName" cssClass="error"/>
                                                     </div>  </div>
                                                    <div class="form-group">
                                                       <form:label path="email" class="control-label col-sm-2">Employee enail</form:label>
                                                        <div class="col-sm-4">
                                                        <form:input path="email" class="form-control"  />
                                                        </div><div class="col-sm-4">
                                                        <form:errors path="email" cssClass="error"/>
                                                    </div> </div>

                                                    <div class="form-group">
                                                        <form:label path="mobNo" class="control-label col-sm-2">Employee Mob No</form:label>
                                                         <div class="col-sm-4">
                                                         <form:input path="mobNo" class="form-control"   />
                                                         </div><div class="col-sm-4">
                                                         <form:errors path="mobNo" cssClass="error" />
                                                    </div> </div>
                                                     <div class="form-group">
                                                         <form:label path="macAddress" class="control-label col-sm-2">Mac Address</form:label>
                                                          <div class="col-sm-4">
                                                           <form:input path="macAddress" class="form-control"  />
                                                           </div><div class="col-sm-4">
                                                           <form:errors path="macAddress" cssClass="error" />
                                                       </div> </div>

                                                    <div class="form-group">
                                                                    <div class="col-sm-offset-2 col-sm-10">
                                                            <input type="submit" value="Submit"  class="btn btn-default" />
                                                       </div> </div>
        </form:form>
        </div>
        </div>
        </div>

<%@ include file="../Footer.jsp" %>


