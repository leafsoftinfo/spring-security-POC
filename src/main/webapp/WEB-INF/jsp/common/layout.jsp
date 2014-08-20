<!DOCTYPE html>
<!--Default Block is from here...  -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<jsp:include page="../fragments/headTag.jsp"/>

<body style="width:1350px" role="document">


<!--For fixed navi bar to prevent it from eating the top screen.  -->
<div class="container" style="padding: 50px"></div>

<tiles:insertAttribute name="menu" />
<div class="header">
	<tiles:insertAttribute name="navbar" />
</div>

<div class="container theme-showcase" >
	
	<div class="jumbotron">
			<tiles:insertAttribute name="body" />
	</div>
</div>

<div class="footer">
<div class="container">
	<tiles:insertAttribute name="footer" />
</div>
</div>

</body>
</html>