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
<h4>Add Food</h4>

  <form:form method="POST"  enctype="multipart/form-data" action="addfooditem" class="form-horizontal"  commandName="food">

                               <div class="form-group">
                                  <form:label path="foodName" class="control-label col-sm-2">Food Name</form:label>
                                    <div class="col-sm-4">
                                    <form:input path="foodName" class="form-control"/>
                                     </div><div class="col-sm-4">
                                   <form:errors path="foodName" cssClass="error"/>
                                 </div>  </div>
                                <div class="form-group">
                                   <form:label path="foodscanCode" class="control-label col-sm-2">Food Scan Code</form:label>
                                    <div class="col-sm-4">
                                    <form:input path="foodscanCode" class="form-control"  />
                                    </div><div class="col-sm-4">
                                    <form:errors path="foodscanCode" cssClass="error"/>
                                </div> </div>

                                <div class="form-group">
                                    <form:label path="foodPrice" class="control-label col-sm-2">Food Price</form:label>
                                     <div class="col-sm-4">
                                     <form:input path="foodPrice" class="form-control"   />
                                     </div><div class="col-sm-4">
                                     <form:errors path="foodPrice" cssClass="error" />
                                </div> </div>
                                 <div class="form-group">
                                     <form:label path="foodDiscription" class="control-label col-sm-2">Food Discription</form:label>
                                      <div class="col-sm-4">
                                       <form:textarea path="foodDiscription" class="form-control"  rows="5" cols="30" />
                                       </div><div class="col-sm-4">
                                       <form:errors path="foodDiscription" cssClass="error" />
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


