<script>
$(document).ready(function () {

   validateForm('#signinForm');

});
</script>

<!-- let spring-security do his thing. -->
<form id="signinForm" class="form-signin" action='../login-check' method="POST">
	
	<fieldset>
		<div class="alert alert-${message.isError=='true'? 'error' : 'success'} alert-dismissable" style="${message==null? 'display: none;' : 'display: block;'}"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span>${message.info}</span></div>

<%-- 		<div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span>${message.info}</span></div>	 --%>

		<div id="legend">
			<legend class="">Please sign in</legend>
		</div>
		<div class="control-group">
			<!-- Username -->
			
			<div class="form-control">
				<input type="text" id="username" name="username" placeholder="Username"
					class="usernameField">
			</div>
		</div>

		<div class="control-group">
			<!-- Password-->
			
			<div class="form-control">
				<input type="password" id="password" name="password" placeholder="Password"
					class="passwordField">
			</div>
		</div>


		<div class="control-group">
			<!-- Button -->
			<div class="form-control">
				<button class="btn btn-lg btn-primary">Login</button>
			</div>
		</div>		
	</fieldset>
</form>
