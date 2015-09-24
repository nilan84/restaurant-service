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
<h4>Add Customer</h4>

  <form:form method="POST"  enctype="multipart/form-data" action="addemployee" class="form-horizontal"  commandName="customer">

                               <div class="form-group">
                                  <form:label path="customerName" class="control-label col-sm-2">Customer Name</form:label>
                                    <div class="col-sm-4">
                                    <form:input path="customerName" class="form-control"/>
                                     </div><div class="col-sm-4">
                                   <form:errors path="customerName" cssClass="error"/>
                                 </div>  </div>
                                <div class="form-group">
                                   <form:label path="customerEmail" class="control-label col-sm-2">Customer email</form:label>
                                    <div class="col-sm-4">
                                    <form:input path="customerEmail" class="form-control"  />
                                    </div><div class="col-sm-4">
                                    <form:errors path="customerEmail" cssClass="error"/>
                                </div> </div>

                                <div class="form-group">
                                    <form:label path="customerMob" class="control-label col-sm-2">Customer Mob No</form:label>
                                     <div class="col-sm-4">
                                     <form:input path="customerMob" class="form-control"   />
                                     </div><div class="col-sm-4">
                                     <form:errors path="customerMob" cssClass="error" />
                                </div> </div>
                                 <div class="form-group">
                                     <form:label path="macAddress" class="control-label col-sm-2">Customer Mac</form:label>
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
         </div>
        </div>


<%@ include file="../Footer.jsp" %>


