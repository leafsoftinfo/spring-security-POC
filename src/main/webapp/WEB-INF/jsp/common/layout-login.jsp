<!DOCTYPE html>
<!--Default Block is from here...  -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Weird that i have to declare this since @ModelAttribute is not working well with tiles 3-->
<%@ page import="com.acss.poc.account.Account" %>
<% request.setAttribute("account",new Account()); %>
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body>
<!--to here  -->

<nav class="navbar navbar-default" role="navigation">
</nav>
<div class="container" style="width : 500px">
		<div class="well">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#login" data-toggle="tab">Login</a></li>
				<li><a href="#create" data-toggle="tab">Create Account</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane active in" id="login">
					<tiles:insertAttribute name="body" />
				</div>
				<div class="tab-pane fade" id="create">
 					<tiles:insertAttribute name="signpage" />
				</div>
			</div>
			
		</div><!--well  -->

</div><!-- container  -->
</body>
</html>