<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ include file="../Admin.jsp" %>
</head>
<body>
<div class="container">
 <div class="col-sm-12">
<div class="jumbotron">
<h4>Edit Customer</h4>
  <form:form method="POST"  enctype="multipart/form-data" action="editrestaurant" class="form-horizontal"  commandName="restaurant">
                              <form:hidden path="resturantId" class="form-control" />
                               <div class="form-group">
                                  <form:label path="resturantName" class="control-label col-sm-2">Resturant Name</form:label>
                                    <div class="col-sm-4">
                                    <form:input path="resturantName" class="form-control"/>
                                     </div><div class="col-sm-4">
                                   <form:errors path="resturantName" cssClass="error"/>
                                 </div>  </div>
                                <div class="form-group">
                                   <form:label path="latitude" class="control-label col-sm-2">latitude</form:label>
                                    <div class="col-sm-4">
                                    <form:input path="latitude" class="form-control"  />
                                    </div><div class="col-sm-4">
                                    <form:errors path="latitude" cssClass="error"/>
                                </div> </div>

                                <div class="form-group">
                                    <form:label path="longitude" class="control-label col-sm-2">longitude</form:label>
                                     <div class="col-sm-4">
                                     <form:input path="longitude" class="form-control"   />
                                     </div><div class="col-sm-4">
                                     <form:errors path="longitude" cssClass="error" />
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


