<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ include file="../Admin.jsp" %>
</head>
<body>
<div class="container">
 <div class="col-sm-12">
<div class="jumbotron">
<h4>Add Employee</h4>

  <form:form method="POST"  enctype="multipart/form-data" action="edituser" class="form-horizontal"  commandName="user">
 <form:hidden path="userId" class="form-control" />
                               <div class="form-group">
                                  <form:label path="username" class="control-label col-sm-2">User Name</form:label>
                                    <div class="col-sm-4">
                                    <form:input path="username" class="form-control"/>
                                     </div><div class="col-sm-4">
                                   <form:errors path="username" cssClass="error"/>
                                 </div>  </div>
                                <div class="form-group">
                                   <form:label path="password" class="control-label col-sm-2">User Passward</form:label>
                                    <div class="col-sm-4">
                                    <form:password path="password" class="form-control"  />
                                    </div><div class="col-sm-4">
                                    <form:errors path="password" cssClass="error"/>
                                </div> </div>

                                <div class="form-group">
                                    <form:label path="firstName" class="control-label col-sm-2">First Name</form:label>
                                     <div class="col-sm-4">
                                     <form:input path="firstName" class="form-control"   />
                                     </div><div class="col-sm-4">
                                     <form:errors path="firstName" cssClass="error" />
                                </div> </div>
                                 <div class="form-group">
                                     <form:label path="lastName" class="control-label col-sm-2">Last Name</form:label>
                                      <div class="col-sm-4">
                                       <form:input path="lastName" class="form-control"  />
                                       </div><div class="col-sm-4">
                                       <form:errors path="lastName" cssClass="error" />
                                   </div> </div>
                                    <div class="form-group">
                                                                       <form:label path="userType" class="control-label col-sm-2">User Type</form:label>
                                                                       <div class="col-sm-4">
                                                                      <form:select path="userType">
                                                                        <c:forEach var="enum" items="${enumValues}">
                                                                                  <c:out value="${enum}"/>
                                                                        <option value="${enum.value}"><c:out value="${enum.value}"/></option>
                                                                        </c:forEach>
                                                                     </form:select>
                                                                     </div></div>

                                <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                        <input type="submit" value="Submit"  class="btn btn-default" />
                                   </div> </div>
        </form:form>
        </div>
        </div>
        </div>
         </div>
        </div>

<%@ include file="../Footer.jsp" %>


