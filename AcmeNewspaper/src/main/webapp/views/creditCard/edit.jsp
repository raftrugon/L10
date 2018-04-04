<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="creditCard/edit.do" modelAttribute="creditCard">
	<jstl:set var='model' value='creditCard' scope='request'/>
		<lib:input type="text" name='holderName'/>
		<lib:input type="text" name='brandName'/>
		<lib:input type="text" name='number'/>
		<lib:input type="text" name='expirationMonth'/>
		<lib:input type="text" name='expirationYear'/>
		<lib:input type="text" name='cvvCode'/>
</form:form>
</div>