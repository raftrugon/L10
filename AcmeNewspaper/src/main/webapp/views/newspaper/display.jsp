<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-sm-10 col-sm-offset-1 well">
	<display:table pagesize="1" class="displaytag" keepStatus="true" name="newspaper" id="row">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>

		<jstl:set var='model' value='newspaper' scope='request'/>
		<lib:column name='title'/>
		<lib:column name='publicationDate'/>
		<lib:column name='description'/>
		<lib:column name='picture'/>
		<lib:column name='isPrivate'/>
	</display:table>
</div>

<div class="col-sm-10 col-sm-offset-1 well">
	<display:table pagesize="10" class="displaytag" keepStatus="true" name="articles" id="row2">
		<display:setProperty name="paging.banner.onepage" value=""/>
	    <display:setProperty name="paging.banner.placement" value="bottom"/>
	    <display:setProperty name="paging.banner.all_items_found" value=""/>
	    <display:setProperty name="paging.banner.one_item_found" value=""/>
	    <display:setProperty name="paging.banner.no_items_found" value=""/>

	<jstl:set var='model' value='article' scope='request'/>
	<jstl:choose>
		<jstl:when test="${not row2.newspaper.isPrivate or isSubscribed}">
			<lib:column name="title" link="article/display.do?articleId=${row2.id}" linkName="${row2.title}"></lib:column>
		</jstl:when>
		<jstl:otherwise>
			<lib:column name="title"></lib:column>
		</jstl:otherwise>
	</jstl:choose>

	<lib:column name="publicationMoment" format="{0,date,dd/MM/yyyy}"></lib:column>
	<lib:column name="summary" value="${fn:substring(row2.summary,0,30)}..."/>

	<spring:message code="article.writer" var="writer_header"/>
	<display:column title="${writer_header}">
		<a href="user-display.do?userId=${row2.newspaper.user.id}">
		<jstl:out value="${row2.newspaper.user.surnames}, ${row2.newspaper.user.name}"/></a>
	</display:column>


</display:table>
</div>
