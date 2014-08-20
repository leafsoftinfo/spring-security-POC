<script>
$(document).ready(function () {
	validateForm('#tab');
});
</script>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="tab" method="post" modelAttribute="account" action="../account/signup" >
	<div class="control-group">
	<label class="control-label">Username</label> 
	<div class="controls">
		<form:input type="text" id="username" path="username" class="usernameCheckRemotely" />
	</div>
	</div>
	
	<div class="control-group">
	<label class="control-label">Password</label> 
	<div class="controls">
		<form:input type="password" id="password" path="password" class="passwordField" />
	</div>
	</div>
	
	<div class="control-group">
	<label class="control-label">First Name</label> 
	<div class="controls">
		<form:input type="text" id="firstName" path="firstName" class="nameField" /> 
	</div>
	</div>
	
	<div class="control-group">
	<label class="control-label">Last Name</label> 
	<div class="controls">
		<form:input type="text" id="lastName" path="lastName" class="nameField" /> 
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
		<button class="btn btn-lg btn-primary">Create Account</button>
	</div>
</form:form>