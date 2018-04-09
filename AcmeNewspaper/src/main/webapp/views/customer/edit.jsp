<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="lib" tagdir="/WEB-INF/tags/myTagLib"%>

<security:authorize access="isAnonymous()">

	<div class="well col-md-6 col-md-offset-3">

		<form:form action="register/customer.do" modelAttribute="customer">
		
			<!-- Shared Variables -->
			<jstl:set var="model" value="customer" scope="request" />

			<!-- Hidden attributes -->
			<lib:input name="subscriptionss" type="hidden" />			
			
			<!-- Attributes -->
			<!-- ------------- ACCOUNT DATA -----------------  -->
			<h1>
				<spring:message code="accountData" />
			</h1>
			<lib:input name="userAccount.username" type="text" />
			<lib:input name="userAccount.authorities" type="hidden" />
			

			<div class="form-group">
				<form:label class="control-label" path="userAccount.password">
					<spring:message code="customer.userAccount.password" />:
				</form:label>
				<form:input type="password" class="form-control userAccountPassword" path="userAccount.password" id="userAccountPassword" />
				<form:errors cssClass="error" path="userAccount.password" />
			</div>

			<div class="form-group">
				<label class="control-label">
					<spring:message code="customer.repeatpassword" />:
				</label> 
				<input id="customerConfirmPassword" type="password" class="form-control" />
				<p id="nomatch" style="display: none; color: red; font-weight: bold">
					<spring:message code="customer.repeatpassword.nomatch" />
				</p>
			</div>

			<!-- ------------- PERSONAL DATA -----------------  -->
			<h1>
				<spring:message code="personalData" />
			</h1>
			<lib:input name="name" type="text" />
			<lib:input name="surnames" type="text" />
			<spring:message code="customer.addressess.placeholder" var="addressessPlaceholder" />
			<lib:input name="addressess" placeholder="${addressessPlaceholder}" type="text" />
			<spring:message code="customer.phoness.placeholder" var="phonessPlaceholder" />
			<lib:input name="phoness" placeholder="${phonessPlaceholder}" type="text" />
			<spring:message code="customer.emailss.placeholder" var="emailssPlaceholder" />
			<lib:input name="emailss" placeholder="${emailssPlaceholder}" type="text" />
			<div class="form-group">
				<input type="checkbox" id="tyc" name="tyc" value="" required="true" />&emsp;
				<a data-toggle="modal" data-target="#tycModal" style="cursor: pointer" class="form-label"> 
					<spring:message	code="accept.terms" />
				</a>
			</div>


			<lib:button model="customer" id="${id}" cancelUri="/AcmeNewspaper" noDelete="true" />
		</form:form>


	</div>
	
	<script>
	$(function(){
		$('#customer').submit(function(e){
			if($('.userAccountPassword').val() !== $('#customerConfirmPassword').val()){
				$('#nomatch').show();
				return false;
			}else{
				$('#nomatch').hide();
				return true;
			}
		});
	});	
	</script>
	
</security:authorize>