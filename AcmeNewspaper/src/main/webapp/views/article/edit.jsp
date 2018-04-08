<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>

<div class="well col-md-6 col-md-offset-3">
	<form:form action="user/article/save.do" modelAttribute="article">
	<jstl:set var='model' value='article' scope='request'/>
	
		<!-- Hidden Attributes -->
		<lib:input name="publicationMoment" type="hidden" />
		<lib:input name="inappropriate" type="hidden" />
		<lib:input name="followUps" type="hidden" />
		
		
		<!-- Hidden Attributes -->
		<lib:input type="text" name='title'/>		
		<lib:input type="text" name='summary'/>
		<lib:input type="text" name='body'/>
		<spring:message code="article.pictures.placeholder" var="picturesPlaceholder" />
		<lib:input type="text" placeholder="${picturesPlaceholder}" name='picturess'/>
		
		
		<form:select id="article" path="newspaper" multiple="false">
			<form:option value="0" label="----" />
			<form:options items="${nonPublishedNewspapers}" itemValue="id" itemLabel="title" />
		</form:select>
		<form:errors cssClass="error" path="newspaper" />
		
		
		<lib:input type="checkBox" name='finalMode'/>
		
		<lib:button model="article" id="${id}" cancelUri="/AcmeNewspaper" noDelete="false" />
</form:form>
</div>