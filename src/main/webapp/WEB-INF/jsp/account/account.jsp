<script>
$(document).ready(function () {
	validateForm('#tab');
});
</script>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="tab" method="post" modelAttribute="account" action="save" >
<form:input type="hidden" path="id"/>
	<div class="control-group">
	<label class="control-label">Username</label> 
	<div class="controls">
		<form:input type="text" id="username" path="username" readonly="true"/>
	</div>
	</div>
	
	<div class="control-group">
	<label class="control-label">First Name</label> 
	<div class="controls">
		<form:input type="text" id="firstName" path="firstName" class="nameField"/> 
	</div>
	</div>
	
	<div class="control-group">
	<label class="control-label">Last Name</label> 
	<div class="controls">
		<form:input type="text" id="lastName" path="lastName" class="nameField"/> 
	</div>
	</div>
	
	<div class="control-group">
	<label class="control-label">Email</label> 
	<div class="controls">
		<form:input type="text" id="email" path="email" class="emailField" />
	</div>
	</div>
	
	<div class="control-group">
	<label class="control-label">Address</label> 
	<div class="controls">
		<textarea rows="3" ></textarea>
	</div>
	</div>
	<div>
		<button class="btn btn-lg btn-primary">Edit Account</button>
	</div>
</form:form>
